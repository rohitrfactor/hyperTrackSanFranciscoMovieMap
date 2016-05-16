# hyperTrackSanFranciscoMovieMap
This is the android app code which i made for a Hyper Track coding challenge for android developer job. With this app you can search for movies which had been shot in san francisco and display those locations on google maps. This app fetches the data from https://data.sfgov.org/resource/wwmu-gmzc.json open source api. This supports Autocomplete for movie name. The base activity returns the info about the movie and then the compass shape floating action button with display the locations as markers on google map activity. The app uses open source library gson for deserializing json response to java object. An API service for http connection using apache default http library.And also this app uses the google maps android api &amp; google maps geocoding api to lookup for addresses. This app isn't production ready as due to limited time, some app crashes incase of exceptions aren't covered. Overall the architecture is explained in the doc

1.Min Sdk version 15
2.arget Sdk version 23
compiledSdk vesion 23

Libraries Used

1. android support appcompat v7 
2. google json
3. apache http client
4. android support design
5. android support card view
6. gms play services
7. Other services used
8. google maps android api
9. google geocoding api
10. sf open source api

Features worked on are:
1. Autocomplete search bar for movie name
2. Card based modern UI for movie info response
3. Floating button
4. Snack bar & toast uses for showing appropriate response to user.
5. Async Task
6. The app is customisable to support the android material design.

And the further architecture of the app can be reviewed here with this google doc.
https://docs.google.com/document/d/1aUIMsdjvLjd6lmknHhPfx0vPa_dqZmS7dn6R0hAqEs8/edit?usp=sharing
