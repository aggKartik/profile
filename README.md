# Profile Microservice for Picturesque Platform

The profile micro-service handles the back-end architecture to make a profile sytem possible. 

## Run on Your Local System

### Requirements:
- Docker

### Steps
1. Git clone master branch
2. Build application and required containers with following command:
    ````
    docker-compose up --build
    ````
3. Test functionality by hitting the following endpoint on your browser
    ```
    localhost:8080/v1/health
    ```
