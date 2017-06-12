# Requests Manager Core

Web service dedicated for requests management

## REST endpoints
* GET /resources/ping
* GET /resource/request/{requestId}
* POST /resource/request?title={title}&content={content}
* POST /resource/request?requestId={requestId}&action={action}&actionJustification={actionJustification}
* GET /resource/request/{requestId}/history

## Running application
* Build project
    * `` mvn clean package ``
* Build docker image
    * `` docker build --tag=jrogalsk/requests-manager-core .``
* Run docker container 
    * ``docker run -dit --name requests-manager-core -p 8080:8080 jrogalsk/requests-manager-core``
* Application should be up and running
    * Linux - ``curl -XGET "http://localhost:8080/requests-manager-core/resources/ping``
    * Windows - ``http://192.168.99.100:8080/requests-manager-core/resources/ping``