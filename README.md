YouTrackNotifier
================

Checks a specific search on youtrack to see if there are any issues found.

**Notifications currently only works in linux**

### usage
1. Create a new security token on your youtrack profile page
2. Update the YouTrackNotifier.conf file with the appropriate url, filter and token
3. Run with `sbt run` and whenever the filter has a result, a notification is displayed

#### todo
* improve error handling
* parse issue information returned from YouTrack and provide to notification handler instead of using a size threhshold
* support windows notifications: https://technet.microsoft.com/en-us/library/ff730952.aspx
* configurable notification types
* notify to hipchat?
