YouTrackNotifier
================

Checks a specific search on youtrack to see if there are any issues found.
Notification currently only works in linux

### usage
1. Create a new security token on your youtrack profile page
2. Update the YouTrackNotifier.conf file with the appropriate url, filter and token
3. Run with `sbt run` and whenever the filter results in a result a notification is displaed.
