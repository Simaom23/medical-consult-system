# Korber Challenge

## Overview

This README provides instructions for setting up and running the Korber Challenge application using Docker Compose on your local machine.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/get-started) (Docker Desktop or Docker Engine)
- [Docker Compose](https://docs.docker.com/compose/)

## Getting Started

### Running the Application with Docker Compose

Ensure you have a `docker-compose.yml` file in the `docker` directory. Then, run the following command to build the images and start the containers:

```bash
docker-compose up
```

### Accessing the Application

Once the application is running, you can access it at:

```
http://localhost:8081
```

### Swagger UI

You can interact with the API using the Swagger UI. Open your browser and navigate to:

```
http://localhost:8081/swagger-ui.html
```

This interface allows you to make requests to the various endpoints without using `curl` directly.

### OpenAPI Documentation

The OpenAPI documentation can be accessed at:

```
http://localhost:8081/v3/api-docs
```

This endpoint provides a detailed specification of the available API endpoints.

### Curl Requests

Here are some example `curl` requests to interact with the API:

#### 1. Create a New Consult

**Endpoint:** `POST /api/consults`

```bash
curl -X POST http://localhost:8081/api/consults \
-H "Content-Type: application/json" \
-d '{
    "doctorId": 1,
    "patientId": 1,
    "specialtyId": 1,
    "symptomIds": [1, 2, 3]
}'
```

#### 2. Get Patient Consults and Symptoms

**Endpoint:** `GET /api/consults/patient/{patientId}`

Replace `{patientId}` with the actual ID of the patient you want to query.

```bash
curl -X GET http://localhost:8081/api/consults/patient/1
```

#### 3. Get All Patients (Paginated)

**Endpoint:** `GET /api/patients`

You can add optional query parameters for filtering.

```bash
curl -X GET "http://localhost:8081/api/patients?page=0&size=10&age=30&name=John"
```

#### 4. Get Top Specialties

**Endpoint:** `GET /api/specialties/top`

```bash
curl -X GET http://localhost:8081/api/specialties/top
```

### Accessing Grafana and Viewing Logs

Once the application is running, you can access Grafana at:

```
http://localhost:3100
```

Grafana is integrated with Loki for log management. To explore the logs of the application:

1. Navigate to **Grafana** at the above URL.
2. In the **Data Sources** section, click on **Loki**.
3. In the top-right corner, click on **Explore** to start viewing the logs.
4. You can filter and search through the logs for specific application events or errors, providing you with real-time insights into the application's behavior.

### Stopping the Application

To stop the running application, use:

```bash
docker-compose down
```

### Removing Stopped Containers

To remove the stopped containers and networks, run:

```bash
docker-compose down --volumes
```

## Troubleshooting

- If you encounter issues, check the logs of the containers:

```bash
docker-compose logs
```

- Ensure your Docker daemon is running and you have sufficient permissions to run Docker commands.

## Conclusion

You have successfully set up and run the Korber Challenge application locally using Docker Compose. If you have any questions or need further assistance, feel free to reach out!
