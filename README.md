# geo-api

## Description
Api to handle geographical locations. The Api is build with Java and the Spring Boot framework and uses MySQL database.

## Endpoints

All endpoints start with /api

### Categories
GET
/categories
- get all categories

GET
/categories/{id}
- Get one category

POST
/categories
- Create new category (for admin only)
- example:
```JSON
  {
        "name": "Mountains",
        "symbol": "U+26F0",
        "description": "This is the mountain category"
    }
  ```

GET
/places
- get all public places
- with optional query params to get all locations within a distance
?lat={value}&lng={value}&distance={value}

GET
/places/{id}
- get one place

GET
/categories/{id}/places
- get all places in one category

GET
/users/places
- get all places (for logged in user)

POST
/places
- create new place (for logged in user)
- example:
```JSON
{
    "name": "Arlanda Airport",
    "category": 2,
    "createdBy": 3,
    "description": "This is Arlanda Airport",
    "lat": 59.651363743711926,
    "lon": 17.934394242875474
}
  ```

PUT
/places/{id}
- replace one place (for logged in user)
- example:
```JSON
{
    "name": "Arlanda Airport",
    "category": 2,
    "createdBy": 3,
    "description": "This is Arlanda Airport",
    "lat": 59.651363743711926,
    "lon": 17.934394242875474
}
  ```

PATCH
/places/{id}
- update one place (for logged in user)
```JSON
{
    "name": "Arlanda Airport",
    "category": 2,
    "description": "This is Arlanda Airport",
    "lat": 59.651363743711926,
    "lon": 17.934394242875474
}
  ```
DELETE
/place/{id}
- delete one place (for logged in user)

GET
/geo?lat={value}&lon={value}
- get address for location
