# sb-psb2
Code to the book Pro Spring Boot 2

## Curl Commands

+ curl -i http://localhost:8080/api/todo  (read all todos)
+ curl -i -X POST -H "Content-Type: application/json" -d '{
"description":"Read the Pro Spring Boot 2nd Edition Book"}' http://localhost:8080/api/todo (create Todo)
+ curl -s http://localhost:8080/api/todo/{id}  (set ToDo completed)
+ curl -i -X POST -H "Content-Type: application/json" -d '{
"description":""}' http://localhost:8080/api/todo (test validation)
