
# SPAT Analytics – Backend

A scalable Spring Boot–based analytics backend for ticket booking platforms that aggregates bus ticket bookings data across multiple operators into one clear, real-time view.

This project was built for a real travel organization, Amaravathi Travels, my father’s business—to analyze and manage bus ticket bookings through a modern analytics dashboard.

Link to the org: ```https://www.sriamaravathitravels.com/index.html```

The system ingests booking data from external sources, persists it in MySQL, and exposes REST APIs for analytics dashboards.


## Features

- REST APIs for booking, company, and dashboard analytics
- Booking data ingestion from external providers (CSV / mock API)
- MySQL database with JPA entity relationships
- Business logic layer for aggregation & analytics
- Docker & Docker Compose support
- JWT-based authentication (implemented, not yet wired to frontend)
- Stateless backend architecture


## Architecture Overview

High-level Flow
- External data provider sends booking data
- Backend ingests, normalizes, and stores data
- APIs expose analytics to frontend dashboards
- Stateless request handling ensures scalability


## Backend Workflow

<img width="807" height="496" alt="Image" src="https://github.com/user-attachments/assets/a47f0f3e-fab4-408c-879e-01e893ec37bf" />

## Data Ingestion Strategy

#### Primary Source
- Mock API provider
- Simulates real-world booking platforms
#### Secondary Source
- CSV-based ingestion
- Useful for demos, backfills, and testing

The ingestion layer is pluggable and can be replaced with:
- Real bus operators
- OTA booking systems
- Streaming platforms (Kafka, future)
## REST Analytics APIs

Examples:

```http
GET /bookings
GET /companies
GET /dashboard/stats
POST /upload/csv
```
<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/1511651c-aa2e-46ee-9658-c40ea05cb831" width="450" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/5806889e-d10d-4230-b228-8fe47c0e2a79" width="450" />
    </td>
  </tr>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/3c67088d-d1bc-4107-ba39-7f7aad61d34b" width="450" />
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/919d1f2d-ac12-4e1e-9328-10ce06467393" width="450" />
    </td>
  </tr>
</table>



#### These APIs power dashboard KPIs such as:
- Total tickets
- Total revenue
- Total commission
- Company-wise performance
- Time-based trends
## Database Design

- MySQL
- JPA & Hibernate ORM
- Entity relationships:
- One Company → Many Bookings
- Auto schema generation via Hibernate
## Authentication (Implemented)
- JWT token generation & validation
- Stateless request authentication
- Token verification filter
- Ready for frontend integration

JWT logic exists in backend but is not enabled in the main branch to keep the demo frictionless.
## Docker Support

#### Components Containerized
- Spring Boot backend
- MySQL database
  <img width="1590" height="642" alt="Image" src="https://github.com/user-attachments/assets/d9a323ae-e797-4715-9760-d88558997e41" />

## Deployment

Frontend deployed on Vercel

🔗 Live Frontend: ``` https://spat-analytics-frontend-7onr.vercel.app/```

Backend deployed on Render

🔗 Backend API Base URL: ```https://spat-analytics-backend-1.onrender.com/```
  
## Run Locally

Clone the project

```bash
  git clone https://github.com/viharpeddagopu/spat-analytics-backend.git
```

Run using Docker Compose

```
docker compose up --build
```

Backend runs at:
```
http://localhost:8080
```




## Future Enhancements
- Role-based access control (Admin / User)
- Kafka for high-volume booking streams
- Real-time analytics processing
- JWT integration with frontend login
- Caching layer (Redis)
## Contributing

Contributions are always welcome!

Email: viharpeddagopu@gmail.com

LinkedIn: www.linkedin.com/in/viharpeddagopu

