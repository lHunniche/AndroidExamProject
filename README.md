# Android Exam Project
An app that fetches information about amiibos from https://www.amiiboapi.com/ . Get a quick overview of all amiibos ever released per game series, and see information such as the amiibo series they released in and the date they were released in the world.

## For compiling:
Make sure you have Java Development Kit 8 installed on your machine, https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

## The following requirements are met:
* **Web service calls** to https://www.amiiboapi.com/
* **Adapter** and RecyclerView for lists of different amiibos based on game series 
* **Fragments**, using one fragment for the list, and another fragment for detailed view, such that large screens are easily supported
* **External libraries**: Retrofit 2 for web-service calls, Glide for image processing
* (Implicit **threading** by using Retrofit’s enqueue method, for doing web service calls asynchronously)

## Group consists of:
* Frederik Ralskov Holm, fholm16@student.sdu.dk 
* Phillip Jacob Olsen, phols16@student.sdu.dk 
* Lasse Hunniche Klevang, lklev16@student.sdu.dk 
