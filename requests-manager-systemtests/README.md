# # Requests Manager - System Tests


## Set up
Make sure Requests Manager is up and running and accessible ex. on
[http://localhost:8080/requests-manager-core](http://localhost:8080/requests-manager-core)

## Run tests

```
mvn clean verify
    -DrequestsManagerSystem.baseUrl=http://localhost:8080/requests-manager-core
```
