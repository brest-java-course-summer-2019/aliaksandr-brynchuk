[![Build Status](https://travis-ci.org/brest-java-course-summer-2019/aliaksandr-brynchuk.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/aliaksandr-brynchuk)
[![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/aliaksandr-brynchuk/badge.svg?branch=master)](https://coveralls.io/github/brest-java-course-summer-2019/aliaksandr-brynchuk?branch=master)

## Music Shop Application
Application for work with orders - create, read, update(add or delete some items from order), delete and the same operation for items.
## Prerequisites
- jdk8
- maven 3+
***
**Installing**  
 - Download project from github
 URL: `https://github.com/brest-java-course-summer-2019/aliaksandr-brynchuk.git`
 - Project build:
 `mvn install`
*** 
**Running app with Jetty**
   - /music-shop/rest-app dir run: `mvn jetty:run`, then open in browser: `http://localhost:8082/inner/order/orders` for REST APP
   - /music-shop/web-app dir run: `mvn jetty:run`, then open in browser: `http://localhost:8083/outer/order/orders` for WEB APP
*** 
**Running the tests**  
 `mvn test`
***

