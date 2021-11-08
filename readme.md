Weather App
============

Whats complete
---------------

* State capitals list populates from an API to local cache
* Current weather conditions pull from an API to local cache

Libraries used
---------------

* Koin
* Retrofit
* Gson
* Room

Notes
------

* Weather condition data is being pulled from an incorrect endpoint. Should instead be pulling
  from the 16-day forecast endpoint which contains proper high/low temperatures for the day.
  Precipitation data is also absent from the endpoint.

Further Steps
--------------

* Clean up UI / presentation
* Build 5-day forecast screen
* Update current weather condition endpoint / response model to the correct source.
* Refactor weather condition API pull to only request whats necessary when needed. Currently the
  forecast data for all 50 cities pull immediately upon app start.
* Clean up project structure / organization
* Unit / UI tests