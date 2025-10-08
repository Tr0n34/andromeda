data-extraction
---------------------------------
Requirements 

https://github.com/Tr0n34/skandika-connector : scrap data from a skandika bluetooth machine

Abstract

The scrapper push data from skandika-connector to the andromede springboot contoller : 
  - ConsumeDataController.receiveData : reciever for one line of datas
  - ConsumerDataController.receiveDataBatch : receiver for a list of datas

Install

Download https://github.com/Tr0n34/skandika-connector (python)
Launch springboot andromeda/data-extractor
Execute run-scrap.py

auth
---------------------------------
OAuth2 authentication microservice
Dockerisation in progress

cyb-manager
---------------------------------
cybercoffee manager microservice
Dockerisation done

andromeda-api
---------------------------------
specific transversal spring components + interfaces
