[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d01ecd9edb7e49a7b16cd0dff20d7e74)](https://www.codacy.com/app/OlegDemura/votingsystem?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=OlegDemura/votingsystem&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/OlegDemura/votingsystem.svg?branch=master)](https://travis-ci.org/OlegDemura/votingsystem)

Votingsystem

System for voting restaurants.

Task:

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote on which restaurant they want to have lunch at
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we asume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

# CURL Commands

---
## User:

- #### Get all restaurants
    `curl -X GET http://localhost:8080/votingsystem/rest/profile/restaurants -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

- #### Get restaurant with ID 100002
    `curl -X GET http://localhost:8080/votingsystem/rest/profile/restaurants/100002 -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`
    
- #### Get all meals for restaurant with ID 100002
    `curl -X GET http://localhost:8080/votingsystem/rest/profile/meals?restaurantId=100002 -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

- #### Vote for the restaurant with ID 100002 on the current date
    `curl -X GET http://localhost:8080/votingsystem/rest/vote/100002 -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`

---
## Admin:

###  OPERATIONS on users

- #### Get all users
    `curl -X GET http://localhost:8080/votingsystem/rest/admin/users -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
    
- #### Get user with ID 100001
    `curl -X GET http://localhost:8080/votingsystem/rest/admin/users/100001 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`    
    
- #### Get user by email: user@yandex.ru
    `curl -X GET 'http://localhost:8080/votingsystem/rest/admin/users/by?email=user@yandex.ru' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'` 
    
- #### Create a new user
    `curl -X POST http://localhost:8080/votingsystem/rest/admin/users -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name": "new-user","email": "new-email@new1.com","password": "newPassword","roles": ["ROLE_USER"]}'`   
    
- #### Update user with ID 100000
    `curl -X PUT http://localhost:8080/votingsystem/rest/admin/users/100000 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name": "userUpdate","email": "user@yandex.ru","password": "password","roles": ["ROLE_USER"]}'`    
    
- #### Delete user with ID 100000
    `curl -X DELETE http://localhost:8080/votingsystem/rest/admin/users/100000 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'` 
    
    
### OPERATIONS on restaurants    
 
- #### Get all restaurants
    `curl -X GET http://localhost:8080/votingsystem/rest/admin/restaurants -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
    
- #### Get all restaurants
    `curl -X GET http://localhost:8080/votingsystem/rest/admin/restaurants/filter?dateVoting=2019-07-06 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
    
- #### Get restaurant with ID 100002
    `curl -X GET http://localhost:8080/votingsystem/rest/admin/restaurants/100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

- #### Create new restaurant
    `curl -X POST http://localhost:8080/votingsystem/rest/admin/restaurants -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name": "New restaurant","address":"New Address"}'`

- #### Update restaurant with ID 100002
    `curl -X PUT http://localhost:8080/votingsystem/rest/admin/restaurants/100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"name": "restaurant update", "address":"address update"}'`

- #### Delete restaurant with ID of 100002
    `curl -X DELETE http://localhost:8080/votingsystem/rest/admin/restaurants/100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

### OPERATIONS on meals

- #### Get the all meals in restaurant with ID 100002
    `curl -X GET 'http://localhost:8080/votingsystem/rest/admin/meals?restaurantId=100002' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

- #### Get the meal with ID 100007 in restaurant with ID 100002
    `curl -X GET 'http://localhost:8080/votingsystem/rest/admin/meals/100007?restaurantId=100002' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`  

- #### Create new meal for a the restaurant with ID 100002
    `curl -X POST http://localhost:8080/votingsystem/rest/admin/meals?restaurantId=100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"description":"New Meal", "price":"800", "date":"2019-09-05"}'`

- #### Update meal with ID 100007
    `curl -X PUT http://localhost:8080/votingsystem/rest/admin/meals/100007?restaurantId=100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' -H 'Content-Type: application/json' -d '{"description":"New Meal", "price":"800", "date":"2019-08-05"}'`

- #### Delete meal with ID 100007
    `curl -X DELETE http://localhost:8080/votingsystem/rest/admin/meals/100007?restaurantId=100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`


###Operations with votes###

- #### Vote for the restaurant with ID 100002
    `curl -X GET http://localhost:8080/votingsystem/rest/vote?restaurantId=100002 -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

- #### Get a vote-counting for the restaurant with ID 100002 on the 2019/07/06
    `curl -X GET 'http://localhost:8080/votingsystem/rest/vote/count?restaurantId=100002&localDate=2019-07-06' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

- #### Get a vote-counting for a restaurant with ID 100002 from 2019/07/06 to 2019/07/11
    `curl -X GET 'http://localhost:8080/votingsystem/rest/vote/countwithfilter?restaurantId=100002&startDate=2019-07-06&endDate=2019-07-11' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`


###String for Maven run
    `clean package -DskipTests=true org.codehaus.cargo:cargo-maven2-plugin:1.7.5:run`