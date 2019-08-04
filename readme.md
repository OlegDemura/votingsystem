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