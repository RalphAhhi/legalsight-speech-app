# Legal-Sight Speech API: 
The Speech API provides endpoints for managing speech records. Speech records can be save,update,retrieve and delete
### Setting up local environment
#### Prerequisite:
* Latest Docker
* Maven
* JDK 17

Steps for local database setup:
 * Should first execute the database setup
 * Navigate to project > setup > database on terminal
 * Execute "sh start-database.sh"
 * This will start your Postrgre database in docker

Steps for running application locally:
 * Navigate to project root directory
 * Execute "mvn spring-boot:run"
 * This will start the application and you can access the API endpoints.

Steps for running application on docker
 * Should first execute the database setup
 * Navigate to project root directory
 * Execute "sh start-docker.sh"

Notes:
To setup sample data on startup, set legal-speech.initial-data.enabled to true on application.yml

### API Documentation:
Swagger is used for api documentation. Access http://localhost:8080/swagger-ui/index.html after application is running


# SAMPLES
Create Speech Curl
-------------------------------------------------
    curl --location 'localhost:8080/speech' \
    --header 'Content-Type: application/json' \
    --data '{
    "author": "Alice Johnson",
    "content": "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    "speechDate": "2024-03-26",
    "subjectArea": "Science"
    }'
-------------------------------------------------
Update Speech Curl
-------------------------------------------------
    curl -X 'PUT' \
    'http://localhost:8080/speech/{id}?id=1' \
    -H 'accept: */*' \
    -H 'Content-Type: application/json' \
    -d '  {
    "author": "John Doe 2",
    "content": "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
    "speechDate": "2024-03-20",
    "subjectArea": "Politics"
    }'
-------------------------------------------------
Get By Id Speech Curl
-------------------------------------------------
    curl --location 'localhost:8080/speech/1'
-------------------------------------------------

List Speech by filter
-------------------------------------------------
    curl -X 'GET' \
    'http://localhost:8080/speech?author=John%20Doe&subjectArea=Politics&speechContentSnippet=Lorem&speechDateFrom=2024-03-25&speechDateTo=2024-03-26&perPage=10&page=1&sortBy=author&sortOrder=desc' \
    -H 'accept: */*'
-------------------------------------------------

Delete Speech by Id
-------------------------------------------------
    curl -X 'DELETE' \
    'http://localhost:8080/speech/1' \
    -H 'accept: */*'
-------------------------------------------------