# Legal-Sight Speech API: 
The Speech API provides endpoints for managing speeches.
### Setting up local environment
#### Prerequisite:
* Docker
* Maven

Steps:
 * Navigate to project > setup > database on terminal
 * Execute "sh start-database.sh"
 * This will start your postrgre database in docker
 * Navigate to project root directory
 * Execute "mvn spring-boot:run"
 * This will start the application and you can access the API endpoints.

Notes:
To setup sample data on startup, set legal-speech.initial-data.enabled to true on application.yml

### Documentation:
Swagger is use api documentation. Access http://localhost:8080/swagger-ui/index.html after application is running


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