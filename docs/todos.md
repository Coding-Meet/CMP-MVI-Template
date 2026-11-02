Todos - Docs
The todos endpoint provides a dataset of sample to-do items, including details like task descriptions, statuses, and user IDs, ideal for testing and prototyping task management and productivity applications.

Get all todos
By default you will get 30 items, use Limit and skip to paginate through all items.

fetch('https://dummyjson.com/todos')
.then(res => res.json())
.then(console.log);
{
  "todos": [
    {
      "id": 1,
      "todo": "Do something nice for someone I care about",
      "completed": true,
      "userId": 26
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 150,
  "skip": 0,
  "limit": 30
}
Get a single todo
fetch('https://dummyjson.com/todos/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "todo": "Do something nice for someone I care about",
  "completed": true,
  "userId": 26
}
Get a random todo
The random data will change on every call to /random.
You can optionally pass a length of max 10 as /random/10.

fetch('https://dummyjson.com/todos/random')
.then(res => res.json())
.then(console.log);
{
  // random result, will be changed on every call to /random
  "id": 127,
  "todo": "Prepare a dish from a foreign culture",
  "completed": false,
  "userId": 7
}
Limit and skip todos
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/todos?limit=3&skip=10&select=todo,completed')
.then(res => res.json())
.then(console.log);
{
  "todos": [
    {
      "id": 11, // first 10 items were skipped
      "todo": "Text a friend I haven't talked to in a long time",
      "completed": false
    },
    {
      "id": 12,
      "todo": "Organize pantry",
      "completed": true
    },
    {
      "id": 13,
      "todo": "Buy a new house decoration",
      "completed": false
    }
  ],
  "total": 150,
  "skip": 10,
  "limit": 3
}
Sort todos
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/todos?sortBy=todo&order=asc')
.then(res => res.json())
.then(console.log);
{
  "todos": [
    {
      "id": 181,
      "todo": "Attend a comedy show", // sorted by todo in ascending order
      "completed": false,
      "userId": 157
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 150,
  "skip": 0,
  "limit": 30
}
Get all todos by user id
/* getting todos of user with id 5 */
fetch('https://dummyjson.com/todos/user/5')
.then(res => res.json())
.then(console.log);
{
  "todos": [
    {
      "id": 19,
      "todo": "Create a compost pile",
      "completed": true,
      "userId": 5 // user id is 5
    },
    {...},
    {...}
  ],
  "total": 3,
  "skip": 0,
  "limit": 3
}
Add a new todo
Adding a new todo will not add it into the server.
It will simulate a POST request and will return the new created todo with a new id

fetch('https://dummyjson.com/todos/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    todo: 'Use DummyJSON in the project',
    completed: false,
    userId: 5,
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 151,
  "todo": "Use DummyJSON in the project",
  "completed": false,
  "userId": 5
}
Update a todo
Updating a todo will not update it into the server.
It will simulate a PUT/PATCH request and will return updated todo with modified data

/* updating completed status of todo with id 1 */
fetch('https://dummyjson.com/todos/1', {
  method: 'PUT', /* or PATCH */
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    completed: false,
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": "1",
  "todo": "Do something nice for someone I care about",
  "completed": false, // complete status was changed
  "userId": 26
}
Delete a todo
Deleting a todo will not delete it into the server.
It will simulate a DELETE request and will return deleted todo with isDeleted & deletedOn keys

fetch('https://dummyjson.com/todos/1', {
  method: 'DELETE',
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "todo": "Do something nice for someone I care about",
  "completed": true,
  "userId": 26,
  "isDeleted": true,
  "deletedOn": /* ISOTime */
}