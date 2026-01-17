# Care Connect | Full-Stack Healthcare Management System

**Care Connect** is a production-ready scheduling and resource management platform built for healthcare providers. It streamlines the coordination between service offerings (Respite, AFL, Day Support) and client needs through a secure, cloud-native architecture.

## 🚀 Live Demo
**URL:** [https://frontend-244230612831.us-east1.run.app](https://frontend-244230612831.us-east1.run.app)  
*(Note: Application may take a moment to "spin up" due to serverless cold starts.)*

## 🛠 Tech Stack
* **Backend:** Java 17 / Spring Boot 3
* **Data:** PostgreSQL / Hibernate JPA
* **Cloud:** Google Cloud Platform (GCP)
* **Infrastructure:** Cloud Run (Serverless), Cloud SQL (Managed DB), Artifact Registry
* **DevOps:** Docker, Git, Cloud Build

---

## 🏗 System Architecture
This project implements a **Serverless Containerized Architecture** to ensure high availability and cost-efficiency.



* **Persistence Layer:** A managed PostgreSQL instance on Cloud SQL, utilizing custom sequences for data integrity and complex relational mapping.
* **API Layer:** RESTful API designed with Spring Boot, handling business logic for healthcare shifts and client-service associations.
* **Deployment:** Containerized via Docker and deployed to Google Cloud Run, allowing for horizontal scaling and "pay-per-use" infrastructure.

---

## 📋 Key Features
* **Dynamic Scheduling:** Real-time tracking of client shifts and service availability.
* **Relational Data Mapping:** Complex many-to-many relationships between clients and healthcare services.
* **Cloud-Native Security:** Implementation of environment-specific configurations to keep database credentials secure in production.
* **Scalable Infrastructure:** Deployed as a microservice ready for high-traffic environments.

---

## 💻 Local Setup
1.  **Clone & Navigate:**
    ```bash
    git clone [https://github.com/DTSmth/care-connect.git](https://github.com/DTSmth/care-connect.git)
    cd care-connect
    ```
2.  **Database Configuration:** Configure your local PostgreSQL instance in `src/main/resources/application.properties`.
3.  **Build and Run:**
    ```bash
    mvn spring-boot:run
    ```

---

## 📄 License
This project is for portfolio purposes and is licensed under the MIT License.