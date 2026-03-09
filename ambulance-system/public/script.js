let map;
let marker;
let ambulanceMarkers = {};
let myRole = 'user';
let myLocation = { lat: 0, lng: 0 };
const socket = io();

// Define custom icons
const blueIcon = L.icon({
    iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-blue.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

const redIcon = L.icon({
    iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-red.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

function initMap() {
    const defaultLoc = [51.505, -0.09];

    map = L.map('map').setView(defaultLoc, 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    marker = L.marker(defaultLoc, { icon: blueIcon }).addTo(map)
        .bindPopup('Your Location')
        .openPopup();

    if (navigator.geolocation) {
        let firstLoad = true;
        navigator.geolocation.watchPosition(
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                myLocation = pos;
                const latlng = [pos.lat, pos.lng];
                marker.setLatLng(latlng);

                if (firstLoad) {
                    map.setView(latlng);
                    firstLoad = false;
                }

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
    if (data.id === socket.id) return;

    const latlng = [data.lat, data.lng];
    if (!ambulanceMarkers[data.id]) {
        ambulanceMarkers[data.id] = L.marker(latlng, { icon: redIcon }).addTo(map)
            .bindPopup('Ambulance');
    } else {
        ambulanceMarkers[data.id].setLatLng(latlng);
    }
});

socket.on('receive-message', (data) => {
    const alertContainer = document.getElementById('alert-container');
    const alertDiv = document.createElement('div');
    alertDiv.className = 'alert';
    alertDiv.innerHTML = `<strong>Ambulance Alert:</strong> ${data.message}`;
    alertContainer.appendChild(alertDiv);

    setTimeout(() => {
        alertDiv.remove();
    }, 10000);
});

socket.on('user-disconnected', (id) => {
    if (ambulanceMarkers[id]) {
        map.removeLayer(ambulanceMarkers[id]);
        delete ambulanceMarkers[id];
    }
});

// Role selection handling
document.getElementById('role').addEventListener('change', (e) => {
    myRole = e.target.value;
    const ambulanceControls = document.getElementById('ambulance-controls');
    if (myRole === 'ambulance') {
        ambulanceControls.style.display = 'block';
        marker.setIcon(redIcon);
        marker.setPopupContent("Ambulance (Me)");
    } else {
        ambulanceControls.style.display = 'none';
        marker.setIcon(blueIcon);
        marker.setPopupContent("Your Location");
    }

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

// Initialize map on load
window.onload = initMap;
