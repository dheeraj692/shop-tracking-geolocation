# Demo spring boot project for Retailer Application

Retailer application which stores shop names, address and location by calling google api. Get nearby shops near the user.

# Prerequisites

  - Java 8
  - Gradle or Maven


### Services
#### getAllShops
  Location -  http://localhost:8080/shop/
  Method - post
    Get Service to get all the shops in database.
#### Input - Json 
     {
       "shopName":"shopOne",
       "address":"Bavdhan,Pune",
       "postCode":"411021"
      }
   Checks whether already shop is there with this name.If not add shop to database.
 If it is there updates the address. Calls google api to get latitude and longitude by passong the input address.
 
##### Output
    If shop is added gives the details of the shop. If shop is updated gives the object of current address and previous address.
