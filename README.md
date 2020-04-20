# weather-logger

This is simply two pages, the first page showing a list of weather data for the current location, this data is coming from remote server and saved locally into database. Every time you click on the menu icon above, the app gets the current location coordinates and send a request to the remote server to get the new weather data. The second page views more details such as wind speed, pressure and other data.

This Weather Logger sample is writtin in Kotlin. The code architecture used is MVVM with the clean code.

This sample includes unit testing for the business layer, room database testing and UI testing.

I used the following technologies in implementation:

Retrofit: For making server requests.

Room Database: To save the data locally.

Coroutines: For handling the background tasks.

KOIN: For dependency injection.

Mockito: For unit testing.

Espresseo: For UI testing.



