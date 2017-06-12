# Requests Manager
Simple web-based application for managing _requests_ filed by users.

## Requirements
* When user files new _request_ he shall provide _name_ and _description_
* When user removes/rejects _request_ he shall provide _reason_ 
* User can change _description_ only when _request_ is in _CREATED_ or _VERIFIED_ state
* Each request shall have tracked _history of changes_
* _History of changes_ shall be viewable and support
    * Filtering by _name_ and _state_
    * Pagination (default: 10 _request_)
* When _request_ is _Published_ it shall have unique ID assigned to it
* _State_ of _request_ can be changed according to diagram
    * ![States diagram](request-states-diagram.png)

## Request Manager Core
Web service dedicated for requests management ([more](requests-manager-core))

## Request Manager Acceptance Tests
Cucumber project dedicated to validate Request Manager Core from clients perspective ([more](requests-manager-systemtests))

## Project progress
- [x] Adding new _request_
- [x] Changing _state_ of _request_ as defined in state diagram
- [x] Requiring justification when _deleting_ or _rejecting_ _request_
- [x] Saving _history_ of _request_ state changes
- [x] View details of single _request_
- [ ] Change _request_ _description_ on _CREATED_ and _VERIFIED_ _state_
- [ ] Browse _requests_ with paging and filtering
- [ ] Generate unique number during _publication_ of _request_
- [ ] Store _requests_ in database
- [ ] Create UI service
- [ ] Create Dockerfile for Core service
- [ ] Create Dockerfile for UI service
