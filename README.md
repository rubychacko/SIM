# SIM - Store Inventory Management
## About the Project
A portal which helps to manage the resources involved in the process managing inventory for a retailer. This provides visibility to the available stores, products, supply of items present in the system.

## Components of the project
Portal is supported by the SIM microservice which is built on Spring Boot using JPA, Hibernate, HTML, Thyme leaf etc..

Service has components classified into the four layers such as security, controller, service and Repository.
Data to support the portal is being saved in MySQL database

SIM (Store Inventory Management) portal has the below views.

	- Login
	- Sign Up
	- Store Locations
	- Products
	- Supply
	- Report Issues

## How to Run the project

Clone the project from GitHub to the local machine.
Go to the root of the project and run teh command after starting MySQL server,

> mvn spring-boot:run

Open the browser and go to the below address to access the appplication,

> http://localhost:8080/store_inventory_management/