# hyperTrackSanFranciscoMovieMap
This is the android app which i made for a Hyper Track coding challenge for android developer job. With this app you can search for movies which had been shot in san francisco and display those locations on google maps. This app fetches the data from https://data.sfgov.org/resource/wwmu-gmzc.json open source api. This supports Autocomplete for movie name. The base activity returns the info about the movie and then the compass shape floating action button with display the locations as markers on google map activity. The app uses open source library gson for deserializing json response to java object. An API service for http connection using apache default http library.And also this app uses the google maps android api &amp; google maps geocoding api to lookup for addresses. This app isn't production ready as due to limited time, some app crashes incase of exceptions aren't covered. Overall the architecture is explained in the doc https://docs.google.com/document/d/1aUIMsdjvLjd6lmknHhPfx0vPa_dqZmS7dn6R0hAqEs8/edit?usp=sharing
