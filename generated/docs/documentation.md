# Documentation
## Overview
This is a simple user management system implemented using Kotlin and Spring Boot. The system allows you to add users, view the list of all users, and download the contacts as a CSV file.

## Features
- View a list of all users in the system.
- Add a new user through an input field.
- Download all contacts as a CSV file with the default name `contactdatabase.csv`.

## API Endpoints
### GET /
Returns the main page displaying all users and the form to add a new user.

### POST /addUser
Adds a new user to the system. The username should be provided in the request body as a form parameter.

### DELETE /deleteUser/{username}
Deletes a user from the system using their username.

### GET /downloadContacts
Downloads all contacts as a CSV file named `contactdatabase.csv`.