let map;
let marker;
let ambulanceMarkers = {};
let myRole = 'user';
let myLocation = { lat: 0, lng: 0 };
const socket = io();

function initMap() {
    const defaultLoc = { lat: 51.5074, lng: -0.1278 };

    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 15,
        center: defaultLoc,
    });

    marker = new google.maps.Marker({
        position: defaultLoc,
        map: map,
        title: "Your Location",
        icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
    });

    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                myLocation = pos;
                marker.setPosition(pos);
                map.setCenter(pos);

                socket.emit('update-location', {
                    role: myRole,
                    lat: pos.lat,
                    lng: pos.lng
                });
            },
            () => {
                console.error("Error: The Geolocation service failed.");
            }
        );
    }
}

// Socket.io Events
socket.on('ambulance-location-update', (data) => {
    if (data.id === socket.id) return; // Skip if it's me

    if (!ambulanceMarkers[data.id]) {
        ambulanceMarkers[data.id] = new google.maps.Marker({
            position: { lat: data.lat, lng: data.lng },
            map: map,
            title: "Ambulance",
            icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png'
        });
    } else {
        ambulanceMarkers[data.id].setPosition({ lat: data.lat, lng: data.lng });
    }
});

socket.on('receive-message', (data) => {
    const alertContainer = document.getElementById('alert-container');
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert';
    alertDiv.innerHTML = `<strong>Ambulance Alert:</strong> ${data.message}`;
    alertContainer.appendChild(alertDiv);

    // Auto-remove alert after 10 seconds
    setTimeout(() => {
        alertDiv.remove();
    }, 10000);
});

socket.on('user-disconnected', (id) => {
    if (ambulanceMarkers[id]) {
        ambulanceMarkers[id].setMap(null);
        delete ambulanceMarkers[id];
    }
});

// Role selection handling
document.getElementById('role').addEventListener('change', (e) => {
    myRole = e.target.value;
    const ambulanceControls = document.getElementById('ambulance-controls');
    if (myRole === 'ambulance') {
        ambulanceControls.style.display = 'block';
        marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png');
        marker.setTitle("Ambulance (Me)");
    } else {
        ambulanceControls.style.display = 'none';
        marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png');
        marker.setTitle("Your Location");
    }

    // Update role on server
    socket.emit('update-location', {
        role: myRole,
        lat: myLocation.lat,
        lng: myLocation.lng
    });
});

// Send message handling
document.getElementById('send-btn').addEventListener('click', () => {
    const message = document.getElementById('message-input').value;
    if (message) {
        socket.emit('send-message', {
            message: message,
            lat: myLocation.lat,
            lng: myLocation.lng
        });
        document.getElementById('message-input').value = '';
    }
});
