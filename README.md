# Tax Calculator
A service that will help you calculate your tax.

## How to run service
You must have docker installed in your machine, if you don't, follow this [page](https://docs.docker.com/v17.12/install/)

Assuming you have docker installed, run this docker command in the project root :
```
docker-compose up
```
This will take some time for the first since it will download all the images and dependencies.

## Api Documentation
We are using swagger to document API, to access swagger UI go to this [URL](http://localhost:8080/swagger-ui.html#/) after your service is running.
Right now we only have 2 endpoints :
```
GET /v1/transaction

URL example : http://localhost:8080/v1/transaction

Using terminal : curl -X GET "http://localhost:8080/v1/transaction" -H "accept: */*" 
```
```
POST /v1/transaction

URL example : http://localhost:8080/v1/transaction
Body content example : 
{
  "name": "Big Mac",
  "price": 1000,
  "taxCode": 1
}

Using terminal : curl -X POST "http://localhost:8080/v1/transaction" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"Big Mac\", \"price\": 1000, \"taxCode\": 1}"
```

## Running Tests
You can run all tests by using :
```
./gradlew test
```

## Database

We are using postgres that is running on docker container. Detailed credentials can be found at `src/main/resources/config/application.yml`

We only have 1 table which is a `transaction` table, since it's the only table that is necessary for the requirement, and we don't want to over engineer this.

