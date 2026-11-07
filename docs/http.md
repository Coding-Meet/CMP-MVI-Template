Mock HTTP Responses - Docs
The http endpoints allows you to mock and test any HTTP responses, facilitating the development and testing of web applications.

Mock HTTP Response
You can mock and test any HTTP responses. You may test each individual HTTP code by visiting:
dummyjson.com/{code}/{optional-message}

fetch('https://dummyjson.com/http/200')
.then(res => res.json())
.then(console.log);
{
  "status": "200",
  "message": "OK"
}
Custom HTTP Response
fetch('https://dummyjson.com/http/404/Hello_Peter')
.then(res => res.json())
.then(console.log);
{
  "status": "404",
  "message": "Hello_Peter"
}