# Spring REST + Spring Security Example
* https://tuts.heomi.net/spring-rest-spring-security-example/

# 1. How to start

Start application
```sh
$ git clone https://github.com/favtuts/java-spring-boot-tutorials.git
$ cd spring-rest-security
$ mvn spring-boot:run
```

# 2. How to test

A normal `GET` and `POST` will return a `401`, all endpoints are protected, need authentication.
```
> curl -v "localhost:8080/books'

{	
	"timestamp":"2022-06-08T14:52:47.011+0000",
	"status":401,
	"error":"Unauthorized",
	"message":"Unauthorized",
	"path":"/books"
}

> curl -X POST "localhost:8080/books" -H "Content-type:application/json" -d '{"name":"ABC","author":"favtuts","price":"8.88"}'
```

Send a GET request along with user login.
```
> curl localhost:8080/books -u user:password
[
	{"id":1,"name":"A Guide to the Bodhisattva Way of Life","author":"Santideva","price":15.41},
	{"id":2,"name":"The Life-Changing Magic of Tidying Up","author":"Marie Kondo","price":9.69},
	{"id":3,"name":"Refactoring: Improving the Design of Existing Code","author":"Martin Fowler","price":47.99}
]

> curl localhost:8080/books/1 -u user:password

> curl localhost:8080/books/1 -u admin:password

{
	"id":1,
	"name":"A Guide to the Bodhisattva Way of Life",
	"author":"Santideva",
	"price":15.41
}
```

Try to send a POST request with ‘user’ login, it will return 403, Forbidden error. This is because the user has no right to send a POST request.

```
curl -X POST "localhost:8080/books" -H "Content-type:application/json" \
	-d '{"name":"ABC","author":"favtuts","price":"8.88"}' -u user:password


{
	"timestamp":"2019-02-25T04:16:58.702+0000",
	"status":403,
	"error":"Forbidden",
	"message":"Forbidden",
	"path":"/books"
}
```

To send POST,PUT,PATCH or DELETE request, we need admin

Try to send a POST request with admin login

```
> curl -X POST localhost:8080/books -H "Content-type:application/json" \
	-d '{"name":"ABC","author":"favtuts","price":"8.88"}' -u admin:password
	
{
	"id":4,
	"name":"ABC",
	"author":"favtuts",
	"price":8.88
}


> curl localhost:8080/books -u user:password

[
	{"id":1,"name":"A Guide to the Bodhisattva Way of Life","author":"Santideva","price":15.41},
	{"id":2,"name":"The Life-Changing Magic of Tidying Up","author":"Marie Kondo","price":9.69},
	{"id":3,"name":"Refactoring: Improving the Design of Existing Code","author":"Martin Fowler","price":47.99},
	{"id":4,"name":"ABC","author":"favtuts","price":8.88}
]
```


# 3. Integration test

Run all tests
```
mvn test
```