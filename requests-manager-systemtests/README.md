# # Requests Manager - System Tests
Cucumber project dedicated to validate Request Manager Core from clients perspective

## Set up
Make sure Requests Manager Core is up and running and accessible ex. on
[http://localhost:8080/requests-manager-core](http://localhost:8080/requests-manager-core)

## Tests execution

```
mvn clean verify
    -DrequestsManagerSystem.baseUrl=http://localhost:8080/requests-manager-core
```

## View tests report
After tests execution open target/cucumber/index.html file in your browser
