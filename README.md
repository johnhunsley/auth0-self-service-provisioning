# auth0-self-service-provisioning
[![Circle CI](https://circleci.com/gh/johnhunsley/auth0-self-service-provisioning.svg?style=svg)](https://circleci.com/gh/johnhunsley/auth0-self-service-provisioning)

A management API which enables Auth0 users to be invited and provision their own passwords

1. User with Admin role logs into the vue.js app.
2. Adds email addresses
3. Hits Invite
4. Call to REST API to invite array of addresses
5. For each email address
  - API sends create user request to auth0 management API
  - Sends Change Password email to the addressee vi auth0 authenticate API
  - returns array with addresses and success/fail code
6. address receive email
7. click change password link
8. redirected to change password hosted page and submits new password
9. new users can login to the app defined in scope

Using auth0 Java API for Managment and Auth API calls - https://github.com/auth0/auth0-java