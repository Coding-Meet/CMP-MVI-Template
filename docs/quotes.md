Quotes - Docs
The quotes endpoint provides a dataset of sample quotes, including details like the quote text and author information, ideal for testing and prototyping features in applications that require motivational or inspirational content.

Get all quotes
By default you will get 30 items, use Limit and skip to paginate through all items.

fetch('https://dummyjson.com/quotes')
.then(res => res.json())
.then(console.log);
{
  "quotes": [
    {
        "id": 1,
        "quote": "Life isn't about getting and having, it's about giving and being.",
        "author": "Kevin Kruse"
    },
    {
        "id": 2,
        "quote": "Whatever the mind of man can conceive and believe, it can achieve.",
        "author": "Napoleon Hill"
    },
    {
        "id": 3,
        "quote": "Strive not to be a success, but rather to be of value.",
        "author": "Albert Einstein"
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 100,
  "skip": 0,
  "limit": 30
}
Get a single quote
fetch('https://dummyjson.com/quotes/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "quote": "Life isn't about getting and having, it's about giving and being.",
  "author": "Kevin Kruse"
}
Get a random quote
The random data will change on every call to /random.
You can optionally pass a length of max 10 as /random/10.

fetch('https://dummyjson.com/quotes/random')
.then(res => res.json())
.then(console.log);
{
  // random result, will change on every call to /random
  "id": 62,
  "quote": "If you want to lift yourself up, lift up someone else.",
  "author": "Booker T. Washington"
}
Limit and skip quotes
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/quotes?limit=3&skip=10&select=quote')
.then(res => res.json())
.then(console.log);
{
  "quotes": [
    {
      "id": 11, // first 10 items were skipped
      "quote": "We must balance conspicuous consumption with conscious capitalism."
    },
    {
      "id": 12,
      "quote": "Life is what happens to you while you're busy making other plans."
    },
    {
      "id": 13,
      "quote": "We become what we think about."
    }
  ],
  "total": 100,
  "skip": 10,
  "limit": 3
}
Sort quotes
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/quotes?sortBy=author&order=asc')
.then(res => res.json())
.then(console.log);
{
  "quotes": [
    {
      "id": 2,
      "quote": "The Bay of Bengal is hit frequently by cyclones. The months of November and May, in particular, are dangerous in this regard.",
      "author": "Abdul Kalam" // sorted by author in ascending order
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 100,
  "skip": 0,
  "limit": 30
}