# EXAM ENTERPRICE 2 #

I think i missunderstood the assignemnet at first, when i made this trainwreck of a server i just did it so the rabbitmq calls directly the services and processes the information it got. It shouldt be coupled together but yeah, it works i guess. When you send an http post request for a new order, it will automaticly go like this:

MAKE NEW ORDER in /order/api/new -----rabbit------> paymentservice changing order to paid -------rabbit-----> orderservice(confirming order change) ------rabbit-------> shipping(will get messsage and ship product) ----rabbit----> orderservice (confirming shipping with orderservice)

This exam i had big problems with testing, i could not get a single test to run. I have some tests in the exam. Unforntaly i could not get the envierment to run them. First i had problems with flyway then mockk would not mock my services. And i couldt figure out how to mockk rabbitMQ since, my servies are heavily reliant on then. 
But even though the tests won't run, the services should work fine. Some hours berfore the due on exam i managed to get my intellij to cooperate. I do not know what i have done with my intellij but i think i have screwed up .idea or something. Anyway i now managed to get some tests on orders, but i will not have time to do it for everything. I beliieve the problem was in the pom file, where i have tried to fix many problems but only making it worse.
everything have worked fine before i push it to github, so i will deliver an project localy without cutting out all the files from gitignore. Will delete /target/.
Another problem i was having was when making the docker files, i feel like they are correct, however i cannot find any jar files when my apps are built. so maybe that is why my profiles "docker compose --profile dev up" will not work.

Postman link:
- https://api.postman.com/collections/20586062-7a1ab592-6ac5-47b5-b967-5e6d29cda5ce?access_key=PMAT-01GJMQNY28GF0D07K1PPETEWJR

- [x] Minimum 3 servers
- [x] Once an order is created communicate with payment with rabbitmq
- [x] once an order is paid for it needs to update order in orderservice that it is paid for. (rabbitmq)
- [x] all services use postgres and multiple databases created with init.sql
- [x] Integration test using MockMVC and TestContainers, wiremock where needed
- [x] Docker compose
- [x] Docker profiles for all the services "docker compose with -p"
- [x] Order service and Shipping are communicating via RabbitMQ 
- [x] Test for RabbitMQ
- [x] gateway porting all traffic to 8080
- [x] Caching on some places
- [x] Pagination on orders
- [x] custom exceptions for things like “Order not found”, and “User not found”,  handled globally using @ControllerAdvice.
- [x] Docker files for all services
