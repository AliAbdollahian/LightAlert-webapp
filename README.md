<h1 align="center">Light Alert - Traffic Light Detection Web App</h1>

<p align="center">
  <img src="https://github.com/AliAbdollahian/LightAlert-webapp/actions/workflows/main_light-alert.yml/badge.svg" alt="Build and Deploy">
</p>

<p align="center">
<a href="https://light-alert-g9ajcnaqb6dweqeh.canadacentral-01.azurewebsites.net/">💻 Web App</a> 
</p>

Light Alert is a web application built using **Spring MVC** and **Java**, designed to streamline traffic monitoring by providing users with a platform to view videos recorded by traffic light detection cameras. The app is part of the [**Light Alert**](https://github.com/9yazz/Capstone) system, leveraging **Azure** for deployment and data management.

---

## Features

1. **User Authentication**
    - Secure login with email and password.
    - Cookies are used to store the user’s email for session management.

2. **Video Management**
    - Fetches videos from **Azure Blob Storage** based on the user’s `system_id`.
    - Displays all videos linked to the authenticated user.

3. **Cloud Integration**
    - User and video metadata are stored in **Azure SQL Database**.
    - Videos are efficiently retrieved from **Azure Blob Storage**.

4. **CI/CD Pipeline**
    - Configured with **GitHub Actions** for automated testing and deployment.
    - All tests pass to ensure reliability and consistency.

---

## Technologies

- **Framework**: Spring MVC
- **Language**: Java
- **Cloud Services**: Azure App Services, Azure SQL Database, Azure Blob Storage
- **Password Hashing**: Argon2 Password Encoding algorithm
- **CI/CD**: GitHub Actions

---

## How It Works

1. **Authentication**
    - Users log in with their credentials.
    - A cookie stores the user’s email to manage their session.

2. **Video Retrieval**
    - The application retrieves videos from Azure Blob Storage by matching the user’s `system_id` in the Azure SQL Database.

3. **Display**
    - Authenticated users are presented with all videos associated with their `system_id` on the home page.

---

## Deployment

The app is deployed on **Azure App Services** and includes:

- **Azure SQL Database** for managing user data and video metadata.
- **Azure Blob Storage** for storing video files.
- CI/CD integration using **GitHub Actions** to automate testing and deployment.

---
## Future Enhancements

- **Enhanced Security**: Add Spring built-in authentication for a more secure login process.
- **Improved Video Management**: Include advanced search and filtering options (e.g., by upload date).
- **Role-Based Access Control**: Support multiple user roles (e.g., admin, viewer).


## Installation and Setup

### Prerequisites
- Java Development Kit (JDK 21+)
- Maven
- Azure account with Blob Storage and SQL Database configured

### Steps to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/AliAbdollahian/LightAlert-webapp.git
   cd LightAlert-webapp
