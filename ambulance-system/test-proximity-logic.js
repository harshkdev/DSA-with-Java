const { io } = require('socket.io-client');
const http = require('http');
const express = require('express');
const { Server } = require('socket.io');

// We'll use the logic from server.js to verify it
const app = express();
const server = http.createServer(app);
const serverIo = new Server(server);

// Mock server logic (same as server.js)
const users = {};
function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371;
    const dLat = (lat2 - lat1) * Math.PI / 180;
    const dLon = (lon2 - lon1) * Math.PI / 180;
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return R * c;
}

serverIo.on('connection', (socket) => {
    socket.on('update-location', (data) => {
        users[socket.id] = { id: socket.id, role: data.role, lat: data.lat, lng: data.lng };
    });

    socket.on('send-message', (data) => {
        Object.keys(users).forEach(userId => {
            if (userId !== socket.id) {
                const user = users[userId];
                const distance = calculateDistance(data.lat, data.lng, user.lat, user.lng);
                if (distance <= 1) {
                    serverIo.to(userId).emit('receive-message', { message: data.message });
                }
            }
        });
    });
});

server.listen(3001, () => {
    const ambulance = io('http://localhost:3001');
    const userNear = io('http://localhost:3001');
    const userFar = io('http://localhost:3001');

    let messagesReceived = 0;
    let testFinished = false;

    userNear.on('receive-message', (data) => {
        console.log('UserNear received message:', data.message);
        messagesReceived++;
    });

    userFar.on('receive-message', (data) => {
        console.error('UserFar received message! This should not happen.');
        process.exit(1);
    });

    setTimeout(() => {
        // Step 1: Update locations
        // Ambulance at (0, 0)
        ambulance.emit('update-location', { role: 'ambulance', lat: 0, lng: 0 });
        // User near at (0.005, 0.005) ~ 0.78 km
        userNear.emit('update-location', { role: 'user', lat: 0.005, lng: 0.005 });
        // User far at (0.1, 0.1) ~ 15.7 km
        userFar.emit('update-location', { role: 'user', lat: 0.1, lng: 0.1 });

        setTimeout(() => {
            // Step 2: Ambulance sends alert
            ambulance.emit('send-message', { message: 'Emergency!', lat: 0, lng: 0 });

            setTimeout(() => {
                // Step 3: Verify results
                if (messagesReceived === 1) {
                    console.log('Test Passed: Only near user received the message.');
                    process.exit(0);
                } else {
                    console.error(`Test Failed: Expected 1 message, but got ${messagesReceived}`);
                    process.exit(1);
                }
            }, 500);
        }, 500);
    }, 500);
});
