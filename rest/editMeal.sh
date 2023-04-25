#!/bin/bash


echo -n Type the id of the meal:
read -s id
echo

curl -X PUT localhost:8080/restrpc/meals/edit/$id -H 'Content-type:application/json' -d '{ "name": "Mexican Carnitas Tacos","kcal":"1280","price":"13.50","description":"Much better than barbacoa","mealType":"MEAT"}'


