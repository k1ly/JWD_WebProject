## RESTAURANT final project
# Java Web Development. Group 34

###### Website visitors are provided with the following services:
***
### *Guest:*
- sign in by the login and password or auto sign in by remember me command next time
  if checkbox had being activated (client cookie)
- register
- recover password if it has been forgotten (in process)
- view dish menu
- add dishes to the cart (user cart stores in cookie)
- manage cart items
- switch language (with saving chosen lang into the cookies) 
- overlook reviews
***
### *User:*
- add dishes to the cart (user cart stores in database)
- manage cart items
- confirm the order
- cancel not accepted orders
- view account profile
- track order history
- edit account settings
- write reviews
- log out
***
### *Manager:*
- __all user's allowed services__
- view all orders
- accept orders
- manage orders
***
### *Admin:*
- __all manager's allowed services__
- view all dishes, dish categories and users 
- add and edit dishes
- add and edit dish categories
- change user role or status

## Project features:
* User friendly URL
* List browsing with pagination
* Localization: en_EN, ru_RU
* 3 roles
* Custom tag
* Connection pool
* SQL database

### Database schema:
![text](https://github.com/k1ly/JWD_WebProject/blob/main/sql/schema.png?raw=true)

### Components used for the project:
- Java 17
- Maven
- Git
- JavaEE: Servlet, JSP, JSTL
- Server / Servlet container: Tomcat 10
- Database: MySQL
- JDBC
- Logger: Log4J2
- Tests: JUnit 4
- JavaDoc

***
### CREATED BY KIRILL LYSKOV 2022