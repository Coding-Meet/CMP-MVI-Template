Posts - Docs
The posts endpoint offers a dataset of sample blog post data, including details like titles, body content, user IDs, and tags, useful for testing and prototyping content management and social media features in web applications.

Get all posts
By default you will get 30 items, use Limit and skip to paginate through all items.

fetch('https://dummyjson.com/posts')
.then(res => res.json())
.then(console.log);
{
  "posts": [
    {
      "id": 1,
      "title": "His mother had always taught him",
      "body": "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
      "tags": [
        "history",
        "american",
        "crime"
      ],
      "reactions": {
        "likes": 192,
        "dislikes": 25
      },
      "views": 305,
      "userId": 121
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 251,
  "skip": 0,
  "limit": 30
}
Get a single post
fetch('https://dummyjson.com/posts/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "title": "His mother had always taught him",
  "body": "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
  "tags": [
    "history",
    "american",
    "crime"
  ],
  "reactions": {
    "likes": 192,
    "dislikes": 25
  },
  "views": 305,
  "userId": 121
}
Search posts
fetch('https://dummyjson.com/posts/search?q=love')
.then(res => res.json())
.then(console.log);
{
  "posts": [
    {
      "id": 7,
      "title": "This is important to remember.",
      "body": "This is important to remember. Love isn't like pie. You don't need to divide it among all your friends and loved ones. No matter how much love you give, you can always give more. It doesn't run out, so don't try to hold back giving it as if it may one day run out. Give it freely and as much as you want.",
      "tags": [
        "magical",
        "crime"
      ],
      "reactions": {
        "likes": 127,
        "dislikes": 26
      },
      "views": 168,
      "userId": 70
    },
    {...},
    {...},
    {...}
    // 17 results
  ],
  "total": 17,
  "skip": 0,
  "limit": 17
}
Limit and skip posts
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/posts?limit=10&skip=10&select=title,reactions,userId')
.then(res => res.json())
.then(console.log);
{
  "posts": [
    {
      "id": 11, // first 10 items are skipped
      "title": "It wasn't quite yet time to panic.",
      "reactions": {
        "likes": 453,
        "dislikes": 8
      },
      "userId": 43
    },
    {...},
    {...},
    {...}
    // 10 items
  ],
  "total": 251,
  "skip": 10,
  "limit": 10
}
Sort posts
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/posts?sortBy=title&order=asc')
.then(res => res.json())
.then(console.log);
{
  "posts": [
    {
      "id": 141,
      "title": "A judgment that is necessarily hampered", // sorted by title in ascending order
      "userId": 102
      /* rest post data */
    },
    {
      "id": 44,
      "title": "A long black shadow slid across the pavement", // sorted by title in ascending order
      "userId": 124
      /* rest post data */
    },
    {...}
    // 30 items
  ],
  "total": 194,
  "skip": 0,
  "limit": 30
}
Get all posts tags
fetch('https://dummyjson.com/posts/tags')
.then(res => res.json())
.then(console.log);
[
  {
    "slug": "history",
    "name": "History",
    "url": "https://dummyjson.com/posts/tag/history"
  },
  {
    "slug": "american",
    "name": "American",
    "url": "https://dummyjson.com/posts/tag/american"
  },
  {
    "slug": "crime",
    "name": "Crime",
    "url": "https://dummyjson.com/posts/tag/crime"
  },
  {
    "slug": "french",
    "name": "French",
    "url": "https://dummyjson.com/posts/tag/french"
  },
  {
    "slug": "fiction",
    "name": "Fiction",
    "url": "https://dummyjson.com/posts/tag/fiction"
  },
  {
    "slug": "english",
    "name": "English",
    "url": "https://dummyjson.com/posts/tag/english"
  },
  {...},
  {...},
  {...}
  // more items
]
Get posts tag list
fetch('https://dummyjson.com/posts/tag-list')
.then(res => res.json())
.then(console.log);
[
  "history",
  "american",
  "crime",
  "french",
  "fiction",
  "english",
  "magical",
  "mystery",
  "love",
  "classic",
  "memory",
  "nostalgia",
  "nature",
  "tranquility",
  "life",
  "books",
  // ... more items
]
Get posts by a tag
fetch('https://dummyjson.com/posts/tag/life')
.then(res => res.json())
.then(console.log);
{
  "posts": [
    {
      "id": 153,
      "title": "The forest was alive with the sounds of nature",
      "body": "Birds sang, leaves rustled, and a gentle stream trickled nearby. It was a symphony of life, a reminder of the world's beauty. The dense canopy overhead filtered the sunlight, creating a mosaic of light and shadow on the forest floor, a tranquil haven far from the chaos of modern life.",
      "tags": [
        "nature",
        "tranquility",
        "life"
      ],
      "reactions": {
        "likes": 366,
        "dislikes": 28
      },
      "views": 1868,
      "userId": 24
    },
    {
      "id": 167,
      "title": "The market was a bustling maze of sights and sounds",
      "body": "Stalls filled with colorful produce, the air rich with the scent of spices and fresh flowers. Vendors called out their wares, and the crowd moved in a vibrant dance. It was a place of energy and life, where every visit promised something new.",
      "tags": [
        "market",
        "vibrant",
        "life"
      ],
      "reactions": {
        "likes": 1165,
        "dislikes": 5
      },
      "views": 3654,
      "userId": 118
    },
    {...}
  ],
  "total": 3,
  "skip": 0,
  "limit": 3
}
Get all posts by user id
/* getting posts by user with id 5 */
fetch('https://dummyjson.com/posts/user/5')
.then(res => res.json())
.then(console.log);
{
  "posts": [
    {
      "id": 61,
      "title": "I'm going to hire professional help tomorrow.",
      "body": "I'm going to hire professional help tomorrow. I can't handle this anymore. She fell over the coffee table and now there is blood in her catheter. This is much more than I ever signed up to do.",
      "tags": [
        "fiction",
        "classic",
        "american"
      ],
      "reactions": {
        "likes": 1127,
        "dislikes": 40
      },
      "views": 4419,
      "userId": 5 // user id is 5
    },
    {...}
  ],
  "total": 2,
  "skip": 0,
  "limit": 2
}
Get post's comments
/* getting posts of comments with id 1 */
fetch('https://dummyjson.com/posts/1/comments')
.then(res => res.json())
.then(console.log);
{
  "comments": [
    {
      "id": 93,
      "body": "These are fabulous ideas!",
      "postId": 1, // post id is 1
      "likes": 7,
      "user": {
        "id": 190,
        "username": "leahw",
        "fullName": "Leah Gutierrez"
      }
    },
    {...},
    {...}
  ],
  "total": 3,
  "skip": 0,
  "limit": 3
}
Add a new post
Adding a new post will not add it into the server.
It will simulate a POST request and will return the new created post with a new id

fetch('https://dummyjson.com/posts/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    title: 'I am in love with someone.',
    userId: 5,
    /* other post data */
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 252,
  "title": "I am in love with someone.",
  "userId": 5
  /* other post data */
}
Update a post
Updating a post will not update it into the server.
It will simulate a PUT/PATCH request and will return updated post with modified data

/* updating title of post with id 1 */
fetch('https://dummyjson.com/posts/1', {
  method: 'PUT', /* or PATCH */
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    title: 'I think I should shift to the moon',
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "title": "I think I should shift to the moon", // only title was updated
  "body": "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
  "userId": 121,
  "tags": [
    "history",
    "american",
    "crime"
  ],
  "reactions": {
    "likes": 192,
    "dislikes": 25
  }
}
Delete a post
Deleting a post will not delete it into the server.
It will simulate a DELETE request and will return deleted post with isDeleted & deletedOn keys

fetch('https://dummyjson.com/posts/1', {
  method: 'DELETE',
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "title": "His mother had always taught him",
  "body": "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
  "tags": [
    "history",
    "american",
    "crime"
  ],
  "reactions": {
    "likes": 192,
    "dislikes": 25
  },
  "views": 305,
  "userId": 121,
  "isDeleted": true,
  "deletedOn": /* ISOTime */
}