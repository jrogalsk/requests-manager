# System Tests Template

This project is dedicated for System Testing of web application with use of Cucumber.
It is designed to cooperate with Jira

- clone the project
- go to directory of the project after clone
- execute below command (change values for -D parameters, what you see below is dummy data)
- WARNING: in order to download/upload Jira stories/results user need to have 'Resolve Issues' permission on project

```
mvn clean verify
    -Djira.hostUrl=https://jira.johndoeinc.com
    -Djira.user=johndoe
    -Djira.password=secret
    -Djira.issues=ABC-123;ABC-456
    -Djira.uploadResults=true
    -Djira.downloadFeaturesFiles=true
    -Dproject.specific.setting.ex.service.url=dummy:1234/yourApp
```

WARNING: Currently works only when _multiple_ xx.feature files are to be downloaded.
This is because for _multiple_ a .zip file is downloaded and for _single_ .feature file is downloaded

# Manual download of .feature files from Jira

When there is only single feature to download:
``` curl -u <JIRA_USER>:<PASS> "<JIRA_HOST>/rest/raven/1.0/export/test?keys=<JIRA_ISSUSES_KEYS>" -o f1.feature ```

Whane there are multiple features being testsd
``` curl -u <JIRA_USER>:<PASS> "<JIRA_HOST>/rest/raven/1.0/export/test?keys=<JIRA_ISSUSES_KEYS>" -o features.zip ```

<JIRA_ISSES_KEYS> ex. ABC-123;ABC-456

# Manual upload of results

After 'mvn test' there should be file cucumber.json generated in main folder
Curl -H “Content-Type: application/json” -X POST -u user:pass –data @cucumber.json "yourJiraServerUrl/rest/raven/1.0/import/execution/cucumber"

NOTE: If invalid issuetype when uploading results than in JIRA administration change Isses schema for project to XRAY n
