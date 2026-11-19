# Library Management REST API

A Maven + Spring Boot project for CRUD operations on book records.

## Requirements

- JDK 17 or later
- Eclipse IDE with Spring Tools (recommended) or Maven support
- Internet connection for Maven to download dependencies

## Import into Eclipse

1. Extract `LibraryManagementAPI.zip`.
2. Open Eclipse.
3. Select **File → Import → Maven → Existing Maven Projects**.
4. Browse to the extracted `LibraryManagementAPI` folder.
5. Select the project and click **Finish**.
6. Wait for Maven dependencies to download.
7. Open `LibraryManagementApiApplication.java`.
8. Right-click the file → **Run As → Java Application** (or **Spring Boot App**, if available).

The server starts at `http://localhost:8080`.

## API endpoints

Base URL: `http://localhost:8080/api/books`

| Method | Endpoint | Purpose | Success response |
|---|---|---|---|
| POST | `/api/books` | Add a book | `201 Created` |
| GET | `/api/books` | Get all books | `200 OK` |
| GET | `/api/books/{id}` | Get one book | `200 OK`, or `404 Not Found` |
| PUT | `/api/books/{id}` | Update a book | `200 OK`, or `404 Not Found` |
| DELETE | `/api/books/{id}` | Delete a book | `204 No Content`, or `404 Not Found` |

## Test using Postman

### 1. Add a book

- Method: `POST`
- URL: `http://localhost:8080/api/books`
- Select **Body → raw → JSON**, then send:

```json
{
  "id": 1,
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "category": "Fiction",
  "availableCopies": 5
}
```

Expected: `201 Created` and the created book in the response body.

### 2. Get all books

- Method: `GET`
- URL: `http://localhost:8080/api/books`

Expected: `200 OK` and a JSON array.

### 3. Get a book by ID

- Method: `GET`
- URL: `http://localhost:8080/api/books/1`

Expected: `200 OK` with the book, or `404 Not Found` if the ID does not exist.

### 4. Update a book

- Method: `PUT`
- URL: `http://localhost:8080/api/books/1`
- Body → raw → JSON:

```json
{
  "title": "The Alchemist - Revised Edition",
  "author": "Paulo Coelho",
  "category": "Inspirational Fiction",
  "availableCopies": 8
}
```

The ID comes from the URL. All four book details are replaced.

Expected: `200 OK` with the updated book, or `404 Not Found`.

### 5. Delete a book

- Method: `DELETE`
- URL: `http://localhost:8080/api/books/1`

Expected: `204 No Content`. If the ID does not exist, the API returns `404 Not Found`.

## Important note

This beginner-friendly version stores books in an in-memory `ArrayList`; it does not use a database. Books are cleared when the application is stopped or restarted. A database such as MySQL can be added later.
