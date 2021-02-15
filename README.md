## Order Microservice

### Installation

- Java, PostgreSQL, SpringBoot, JPA, Hibernate, Docker, Postman, Swagger
- Install Java (SDK 14) (https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
- Download PostgreSQL (https://www.postgresql.org/download/)
- Download Docker (for Containerization using Docker) (https://www.docker.com/products/docker-desktop)


### Setup 
- Open this repository in local IDE.
- Install maven dependencies. To do this, right click on the root `OrderProcessing` and click on `maven` then click on `download sources & documentation`.
- Database name: `orderprocessingdb`
- Setup the database in your up and running PostgreSQL instance. To do this:
    - Open terminal
	- Start postgres server: `sudo -u postgres psql`
    - Create a new database `create database orderprocessingdb;`
    - Use database: `\c orderprocessingdb;`
- To run in a docker container:
    - Add `docker-compose` and `Dockerfile` in the root directory of the SpringBoot Application.
	- Run the application in containarized mode: `docker-compose up`
	- Docker provides an interface between PostgreSQL instance and SpringBoot application.
	
	
### Flow for Creating Order
 - Create a customer and you get a cart id.
 - Add items to that cart id, api's like delete item, get cart items, get items by id and get all items can be used.
 - With this cart id place an order, add payment details, shipping address and billing address.
 - You can perform operations like get orders, delete payment, cancel order and get all orders.
 
 
### Database Schema
- Given legacy schema contains all the data in a single table. I have added necessary entities and split the data into 6 different tables (Address, Cart, Customer, Items, Order, Payments). By doing so the I could achieve normalized database schema.

#### Address
- Contains fields `address_id` `address_line_1`, `address_line_2`, `state`, `city`, `zip`.
#### Cart 
- Contains fields `cart-id`, `customer_id`.
- This table has one-to-one relationship with `Customer` and one-to-many relationship with `Items`.
#### Customer
- Contains fields `customer_id`, `customer_name` and `email_id`
#### Items 
- Contains fields `item_id`, `item_name`, `quantity`, `price`, `shipping_charge`, `delivery_mode`, `description`.
- It has many-to-one relationship with Cart.
#### Orders
- Contains fields `order_id`, `order_status`, `create_date`, `modified_date`, `order_total`, `tax`, `total`, `shipping_charges`, `subtotal`
- It has one-to-one relationship with `Cart`, many-to-one relationship with `Customer`, one-to-many relationship with `Payments`, one-to-one relationship with `Address` (both `billing_address` and `shipping_address`)
#### Payments 
- Contains fields `payment_id`, `payment_type`, `confirmation_number`.
- It has many-to-one relationship with Order. 

### Microservice & API Design
- The layers in the application are as follows Controller uses Service layer, which connects to Repository layer that extends JPARepository
- For simplicity purposes, I have implemented three controllers BatchOrderController, OrdersController and CartController and their respective service and repository classes. I've added the functionality for items to give the user the ability to add/delete items from an order.
- Dependency Injections by means of using @Autowired annotation in SpringBoot is done to pull in service and repository layers in the controllers.

- OrderController endpoints:

1. POST: **createCustomer** ("/orderController/createCustomer"):
    - This takes in the request body from the POST request and creates a customer.
	
2. POST: **createOrder** ("/orderController/createOrder/{cart-id"}):
    - This route takes in an `cartID` and creates an order for the items in the cart.
	
3. POST: **addShipping** ("/orderController/addShipping/{order_id}"):
    - This takes in the request body from the POST request and updates the shipping_address and saves the order.  
	
4. POST: **addBilling** ("/orderController/addBilling/{order_id}")
    - This takes in the request body from the POST request and updates the billing_address and saves the order.
	
5. POST: **addPaymentById** ("/orderController/addPaymentById/{order_id}")
    - This takes in the request body from the POST request and updates the payment for a particular order and saves the order.
	
6. GET: **getOrders** ("/orderController/getOrders/all")
    - This returns all orders.
	
7. GET: **getOrderById** ("/orderController/getOrderById/{order_id}")
    - This get request returns orders by order_id.
	
8. GET: **deletePaymentById** ("/orderController/deletePaymentById/{payment_id}")
    - This get request deletes payment by payment_id
	
9. GET: **deleteOrderById** ("/orderController/deleteOrderById/{order_id}")
    - This get request deletes order by order_id

- CartController endpoints:

1. POST: **addItem** ("/cartController/addItem/{cart_id}"):
    - This takes in cart_id and adds the items to the cart. A customer is associated with a cart. 
	
2. GET: **getItem** ("/cartController/getItem/cart/{cart_id}"):
    - This returns items in any given cart using cart_id.  
	
3. GET: **getItemsAll** ("/cartController/getItems/all"):
    - This is set up in case we wish to search all items.    
	
4. GET: **getItemById** ("/cartController/getItemById/{item_id}"):
    - This returns an item using item_id   
	
5. GET: **deleteItemById** ("/cartController/deleteItemById/{item_id}")
    - This endpoint receives the itemId, and deletes that item using the item_id
	
- BatchOrderController endpoints:

1. POST: **createBatchOrder** ("/kafka/createBatchOrder/"):
	- This uses kafka. It creates a batch order.
	
2. POST: **updateBatchOrder** ("/kafka/updateBatchOrder/"):
    - This updates a BatchOrder updates.

	
### Logging
- Tool used: Lombok's Slf4j package dependency. 
- Can be used on both stage and prod servers. 
- SLF4J in your open-source library or internal library, will make it independent of any particular logging implementation.
- We can also set up AWS cloudwatch logs for monitoring on production srevers.


### API Documentation using Swagger UI
- I've used Swagger to document all HTTP endpoints, routes, their HTTP definitions, and the data schema.
- The package used is `springfox-boot-starter` which includes both the UI and the local route for API documentations.



