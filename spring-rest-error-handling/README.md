# Spring REST Error Handling Example

Article link : https://tuts.heomi.net/spring-rest-error-handling-example/

# 1. How to start
```
$ git clone https://github.com/favtuts/java-spring-boot-tutorials.git
$ cd spring-rest-error-handling
$ mvn spring-boot:run
```

# 2. Test Error Handling

# 2.1 Get all error messages

```
$ curl -v localhost:8080/error

< HTTP/1.1 500
{"timestamp":"2022-06-09T07:49:09.846+0000","status":999,"error":"None","message":"No message available"}
```

# 2.2 Book Not Found

```
$ curl -v localhost:8080/books/5

< HTTP/1.1 404
{
    "timestamp":"2022-06-09T07:52:54.594+0000",
    "status":404,
    "error":"Not Found",
    "message":"Book id not found : 5",
    "path":"/books/5"
}
```

After customizing the entire JSON error response
```
$ curl -v localhost:8080/books/5

< HTTP/1.1 404
{
    "timestamp":"2022-06-09 02:55:29",
    "status":404,
    "error":"Book id not found : 5"
}
```

 
## 2.3 JSR 303 Validation error

```
curl -v -X POST localhost:8080/books \
    -H "Content-type:application/json" \
    -d '{"name":"ABC"}'


< HTTP/1.1 400
{
    "timestamp":"2022-06-09T08:00:32.879+0000",
    "status":400,
    "errors":[
        "Author is not allowed.",
        "Please provide a price",
        "Please provide a author"
    ]
}
```


## 2.4 Path Variables Validation

```
$ curl -v localhost:8080/books/0


< HTTP/1.1 400
{
    "timestamp":"2022-06-09T08:02:33.690+0000",
    "status":400,
    "error":"Bad Request",
    "message":"findOne.id: must be greater than or equal to 1",
    "path":"/books/0"
}
```


## 2.5 Custom Validator for Author field

```
$ curl -v -X POST localhost:8080/books \
	-H "Content-type:application/json" \
	-d "{\"name\":\"Spring REST tutorials\", \"author\":\"abc\",\"price\":\"9.99\"}"


< HTTP/1.1 400
{
    "timestamp":"2022-06-09T08:05:35.848+0000",
    "status":400,
    "errors":[
        "Author is not allowed."
    ]
}
```


## 2.6 Overrides the default JSON error response for all exceptions

Toverride the default JSON error response for all exceptions, create a bean and extends `DefaultErrorAttributes`.

Now, the date time is formatted and a new field – `version` is added to the JSON error response.

```
$ curl -v localhost:8080/books/5

< HTTP/1.1 404
{
    "timestamp":"2022/06/09 15:12:04",
    "status":404,
    "error":"Not Found",
    "message":"Book id not found : 5",
    "path":"/books/5",
    "version":"1.2"
}

------------

$ curl -v localhost:8080/abc


< HTTP/1.1 404
{
    "timestamp":"2022/06/09 15:14:06",
    "status":404,
    "error":"Not Found",
    "message":"No message available",
    "path":"/abc",
    "version":"1.2"
}
```