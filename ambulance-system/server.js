const express = require('express');
const http = require('http');
const { Server } = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = new Server(server);

const PORT = process.env.PORT || 3000;

app.use(express.static(path.join(__dirname, 'public')));

// Store connected users and their locations
const users = {};

// Haversine formula to calculate distance between two points in km
function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371; // Radius of the earth in km
    const dLat = deg2rad(lat2 - lat1);
    const dLon = deg2rad(lon2 - lon1);
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const d = R * c; // Distance in km
    return d;
}

function deg2rad(deg) {
    return deg * (Math.PI / 180);
}

io.on('connection', (socket) => {
    console.log('A user connected:', socket.id);

    socket.on('update-location', (data) => {
        users[socket.id] = {
            id: socket.id,
            role: data.role,
            lat: data.lat,
            lng: data.lng,
        };

        if (data.role === 'ambulance') {
            io.emit('ambulance-location-update', users[socket.id]);
        }
    });

    socket.on('send-message', (data) => {
        // Broadcast message only to users within 1km radius
        Object.keys(users).forEach(userId => {
            if (userId !== socket.id) {
                const user = users[userId];
                const distance = calculateDistance(data.lat, data.lng, user.lat, user.lng);

                if (distance <= 1) { // 1km radius
                    io.to(userId).emit('receive-message', {
                        message: data.message,
                        lat: data.lat,
                        lng: data.lng,
                        senderId: socket.id
                    });
                }
            }
        });
    });

    socket.on('disconnect', () => {
        console.log('User disconnected:', socket.id);
        delete users[socket.id];
        io.emit('user-disconnected', socket.id);
    });
});

server.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
