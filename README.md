### Run it

    Make sure you have Java and Gradle installed on your machine.

    This is the backend of our web application, which is built using Java and the Spring Framework.

Installation and Setup

Clone the repository from GitHub.
    
    cd backend/LocationService/src/main/java/com/architect/locationservice

Run the gradle.build file to install all the required dependencies.
	
	gradle build


Create a local instance of a MySQL database.
In the application.properties file, update the database URL, username, and password to match your local instance.
Run the application.

	gradle bootrun



Endpoints

The backend provides the following REST endpoints:

Users
POST /users - Register a new user.
POST /users/login - Authenticate a user and retrieve a token.

Locations
GET /locations/nearby: Fetches a list of locations near a specified latitude and longitude.
Request Parameters:

latitude (required): latitude of the center of the search area.
longitude (required): longitude of the center of the search area.

Visited Locations

/visitedlocations/add
Adds a visited location for a specified user.

URL: /visitedlocations/add

Method: POST

Request Parameters:

userId (required): ID of the user who visited the location.
locationId (required): ID of the visited location.
dateVisited (required): date and time when the user visited the location in the format of yyyy-MM-ddTHH:mm:ss.
Architecture

The backend follows a traditional Model-View-Controller (MVC) architecture. The User and Location classes serve as models. The UserDAO and LocationDAO classes handle interactions with the database. The controllers, such as UserController and LocationController, handle incoming requests and send responses back to the client.

Security

The backend uses token-based authentication to secure the endpoints. When a user logs in, they receive a token that must be included in subsequent requests to protected endpoints. The token is validated on the server to ensure that only authenticated users can access these endpoints. This does not work  as of yet but the classes are set up around this idea

Error Handling

The backend returns appropriate HTTP status codes and error messages for different types of errors. For example, if a user tries to register with a username that is already taken, a 400 Bad Request status code and an error message are returned.


PS:
Implementation of visited location along with associated methods (VisitedLocation, VisitedLocationDAO, VisitedLocationService, VisitedLocationController, VisitedLocationRepository) has not been  completed and running the dependencies may result in an error.Although we have included these to  demonstrate what the app might look like when finalised we would recommend removing it (and commenting out Bean in AppConfig) when running endpoints.