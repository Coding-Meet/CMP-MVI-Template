Comments - Docs
The comments endpoint provides a dataset of sample user comments, including details like usernames, post IDs, and comment texts, ideal for testing and prototyping social interactions and feedback features in web applications.

Get all comments
By default you will get 30 items, use Limit and skip to paginate through all items.

fetch('https://dummyjson.com/comments')
.then(res => res.json())
.then(console.log);
{
  "comments": [
    {
      "id": 1,
      "body": "This is some awesome thinking!",
      "postId": 242,
      "likes": 3,
      "user": {
        "id": 105,
        "username": "emmac",
        "fullName": "Emma Wilson"
      }
    },
    {
      "id": 2,
      "body": "What terrific math skills you're showing!",
      "postId": 46,
      "likes": 4,
      "user": {
        "id": 183,
        "username": "cameronp",
        "fullName": "Cameron Perez"
      }
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 340,
  "skip": 0,
  "limit": 30
}
Get a single comment
fetch('https://dummyjson.com/comments/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "body": "This is some awesome thinking!",
  "postId": 242,
  "likes": 3,
  "user": {
    "id": 105,
    "username": "emmac",
    "fullName": "Emma Wilson"
  }
}
Limit and skip comments
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/comments?limit=10&skip=10&select=body,postId')
.then(res => res.json())
.then(console.log);
{
  "comments": [
    {
      "id": 11,
      "body": "It was a pleasure to grade this!",
      "postId": 156,
      "likes": 8,
      "user": {
        "id": 162,
        "username": "mateob",
        "fullName": "Mateo Bennett"
      }
    },
    {...},
    {...},
    {...}
    // 10 items
  ],
  "total": 340,
  "skip": 10,
  "limit": 10
}
Sort comments
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/comments?sortBy=likes&order=desc')
.then(res => res.json())
.then(console.log);
{
  "comments": [
    {
      "id": 12,
      "body": "Keep up the incredible work!",
      "likes": 10, // sorted by likes in descending order
      /* rest comment data */
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 340,
  "skip": 0,
  "limit": 30
}
Get all comments by post id
fetch('https://dummyjson.com/comments/post/6')
.then(res => res.json())
.then(console.log);
{
  "comments": [
    {
      "id": 15,
      "body": "You've shown so much growth!",
      "postId": 6, // post id is 6
      "likes": 2,
      "user": {
        "id": 17,
        "username": "evelyns",
        "fullName": "Evelyn Sanchez"
      }
    }
  ],
  "total": 1,
  "skip": 0,
  "limit": 1
}
Add a new comment
Adding a new comment will not add it into the server.
It will simulate a POST request and will return the new created comment with a new id

fetch('https://dummyjson.com/comments/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    body: 'This makes all sense to me!',
    postId: 3,
    userId: 5,
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 341,
  "body": "This makes all sense to me!",
  "postId": 3,
  "user": {
    "id": 5,
    "username": "emmaj",
    fullName: "Emma Miller"
  }
}
Update a comment
Updating a comment will not update it into the server.
It will simulate a PUT/PATCH request and will return updated comment with modified data

/* updating body of comment with id 1 */
fetch('https://dummyjson.com/comments/1', {
  method: 'PUT', /* or PATCH */
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    body: 'I think I should shift to the moon',
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "body": "I think I should shift to the moon", // only body was updated
  "postId": 242,
  "likes": 3,
  "user": {
    "id": 105,
    "username": "emmac",
    "fullName": "Emma Wilson"
  }
}
Delete a comment
Deleting a comment will not delete it into the server.
It will simulate a DELETE request and will return deleted comment with isDeleted & deletedOn keys

fetch('https://dummyjson.com/comments/1', {
  method: 'DELETE',
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "body": "This is some awesome thinking!",
  "postId": 242,
  "likes": 3,
  "user": {
    "id": 105,
    "username": "emmac",
    "fullName": "Emma Wilson"
  },
  "isDeleted": true,
  "deletedOn": /* ISOTime */
}