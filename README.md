# hahnsoftwareassessment

1. Develop a RestApi based on a DDD Pattern in Java and Spring Boot.
2. Develop a Angular CRUD Application to maintain the data with validation in front and backend (use a Fluent Syntax in Both).
3. The Angular Application also should have a overview with an grid.
4. The Application should be startable with a docker-compose and checkable.
5. The Repository should have a git history.
6. Record a video declaring your application (in english)

INSTRUCTIONS READ THE APPLICATION

Create the database and execute the dump file located in the root directory of the project
Run the docker file and dockercompose.yml to get the environment ready.
Access the application api and endpoints with the postman with the below endpoints 
a. POST - localhost:8080/api/customers (Create the customer) 
b. GET - localhost:8080/api/customers (Get all created Customers) 
c. GET -  localhost:8080/api/customers/{id} (Find Customer By ID) 
d. PUT - localhost:8080/api/customers/{id} (Update Customer By ID) 
e. DELETE localhost:8080/api/customers/{ID} (Delete Customer By ID)


IMPORTANT CHOICES MADE DURING DEVELOPMENT

Comments were made to the blocks for codes to follow the clean coding practice and make the review clear.
Lombok dependency was used to prevent boilerplate codes for the getter and setter.
Notation for the constructor was also introduced.
Validations Constraints are done on the fields in the customer model/entity.
Validations Constraints are added on the frontend also.



