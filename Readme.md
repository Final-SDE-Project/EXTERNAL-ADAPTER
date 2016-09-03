#EXTERNAL-ADAPTER Services     

This service has to external API connection. One is BMI calculator the other one is Random Famous Quotes. This service sends the necessary JSON packages to the external APIs and then turns back the result to the Storage-Service.

###Random Quote API

This external adapter service obtains random famous quotes. The communication between these services are in REST and uses JSON objects.

###BMI Calculator API

This adapter takes some information such as "weight", "height" and "age" and returns BMI value of the person and the weight suggestion that person should have. The communication between this adapter and Storage service is in REST with JSON objects.


##WIKI Page
https://github.com/Final-SDE-Project/EXTERNAL-ADAPTER/wiki

##HEROKU Address
https://sde-external.herokuapp.com/external/
