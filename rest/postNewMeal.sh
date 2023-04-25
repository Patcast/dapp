#!/bin/bash

curl -X POST localhost:8080/restrpc/meals/add -H 'Content-type:application/json' -d '{ "name": "Mexican Barbacoa Tacos","kcal":"980","price":"11.30","description":"Fresh made delicious tacos","mealType":"MEAT"}'

