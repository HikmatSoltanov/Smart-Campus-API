# Smart Campus API

## Overview

This is a RESTful API for the "Smart Campus" initiative. It allows management of rooms and sensors, including creating, reading, and deleting resources. The API is built using JAX-RS (Jersey) and runs on Apache Tomcat.

## Technology Stack

- Java 11
- JAX-RS (Jersey 2.32)
- Apache Tomcat 9
- Maven
- Jackson for JSON processing

## Setup Instructions

### Prerequisites

- Java 11 installed
- Apache Tomcat 9 installed
- Maven installed

### Build and Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SmartCampusAPI.git
   ```

2. Build the project:
   Clean and Build using your IDE or Maven

3. Deploy to Tomcat:
   Copy target/SmartCampusAPI.war to tomcat/webapps/

4. Start Tomcat

5. API base URL:
   http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/

## API Endpoints

### Rooms

- GET /rooms — Get all rooms
- GET /rooms/{id} — Get room by ID
- POST /rooms — Create a new room
- DELETE /rooms/{id} — Delete a room

### Sensors

- GET /sensors — Get all sensors
- GET /sensors/{id} — Get sensor by ID
- POST /sensors — Create a new sensor
- GET /sensors/{id}/readings — Get reading history
- POST /sensors/{id}/readings — Add a new reading

## Sample curl Commands

```bash
# Get all rooms
curl -X GET http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/rooms

# Get room by ID
curl -X GET http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/rooms/1

# Create a room
curl -X POST http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/rooms \
  -H "Content-Type: application/json" \
  -d '{"name":"Lab 303","building":"Science","floor":2}'

# Get all sensors
curl -X GET http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/sensors

# Filter sensors by type
curl -X GET http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/sensors?type=CO2

# Create a sensor
curl -X POST http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/sensors \
  -H "Content-Type: application/json" \
  -d '{"type":"TEMP","roomId":1,"currentValue":0,"status":"ACTIVE"}'

# Get sensor readings
curl -X GET http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/sensors/1/readings

# Add a reading
curl -X POST http://localhost:8080/SmartCampusAPI-1.0-SNAPSHOT/api/v1/sensors/1/readings \
  -H "Content-Type: application/json" \
  -d '{"value":23.5}'
```

## Answers to Questions

### Part 1

**Q: Is a new resource instance created for every request?**  
A: Yes, JAX-RS creates a new instance per request by default. That's why singleton repositories are used to persist data.

**Q: Why HATEOAS?**  
A: HATEOAS makes APIs self-documenting by providing links in responses, helping clients discover available actions.

### Part 2

**Q: Is DELETE idempotent?**  
A: Yes. First DELETE returns 204, subsequent DELETE calls return 404. The final state is the same.

### Part 3

**Q: What if client sends wrong Content-Type?**  
A: Server returns HTTP 415 Unsupported Media Type.

**Q: Query parameter vs path for filtering?**  
A: Query parameters are standard for optional filters. Path parameters identify specific resources.

### Part 4

**Q: Benefits of Sub-Resource Locator?**  
A: It keeps code organised by delegating nested resource logic to separate classes.

### Part 5

**Q: Why 422 instead of 404 for missing roomId?**  
A: 404 means the URL doesn't exist. 422 means the request is valid but semantically incorrect.

**Q: Why hide stack traces?**  
A: Stack traces expose internal details. ExceptionMapper returns safe error messages.

**Q: Why use filters for logging?**  
A: Filters apply logging globally and follow the DRY principle.

## Author

Hikmat Soltanov
w2094016
