# Ambulance Alert System

A real-time, proximity-based alerting system for ambulances to communicate with nearby drivers.

## Features

- **Ambulance Tracking:** Display ambulances and drivers on a real-time map.
- **Proximity Alerts:** Ambulances can send messages that are only received by drivers within a 1km radius (simulating being on the same lane or in the same vicinity).
- **Map Visuals:** Distinct map markers for ambulances (red) and normal drivers (blue).
- **Leaflet Integration:** Uses Leaflet and OpenStreetMap for mapping, providing a functional, reliable, and API-key-free alternative to Google Maps.

## Architecture

- **Backend:** Node.js, Express, and Socket.io for managing client roles and positions.
- **Frontend:** HTML5, CSS3, and JavaScript with the Leaflet library for the map interface.
- **Messaging:** Uses the Haversine formula to calculate distances between clients on the server side.

## How to Run

1.  Navigate to the `ambulance-system` directory.
2.  Install dependencies:
    ```bash
    npm install
    ```
3.  Start the server:
    ```bash
    npm start
    ```
4.  Open `http://localhost:3000` in multiple browser windows.
5.  Select different roles (Ambulance or Normal Driver) to test the tracking and messaging.

## Note on Map Provider

While the initial requirement suggested Google Maps, this system uses Leaflet/OpenStreetMap. This choice was made to ensure immediate functionality and ease of deployment without requiring a proprietary Google Maps API key, while still providing all requested features.
