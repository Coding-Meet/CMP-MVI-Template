Carts - Docs
The carts endpoint offers a dataset of sample shopping cart data, including details like cart items, quantities, prices, and user IDs, useful for testing and prototyping e-commerce functionalities such as cart management and checkout processes.

Get all carts
fetch('https://dummyjson.com/carts')
.then(res => res.json())
.then(console.log);
{
  "carts": [
    {
      "id": 1,
      "products": [
        {
          "id": 144,
          "title": "Cricket Helmet",
          "price": 44.99,
          "quantity": 4,
          "total": 179.96,
          "discountPercentage": 11.47,
          "discountedTotal": 159.32,
          "thumbnail": "https://cdn.dummyjson.com/products/images/sports-accessories/Cricket%20Helmet/thumbnail.png"
        },
        {...}
        // more products
      ],
      "total": 4794.8,
      "discountedTotal": 4288.95,
      "userId": 142,
      "totalProducts": 5,
      "totalQuantity": 20
    },
    {...},
    {...},
    {...}
    // 30 items
  ],
  "total": 50,
  "skip": 0,
  "limit": 30
}
Get a single cart
fetch('https://dummyjson.com/carts/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "products": [
    {
      "id": 144,
      "title": "Cricket Helmet",
      "price": 44.99,
      "quantity": 4,
      "total": 179.96,
      "discountPercentage": 11.47,
      "discountedTotal": 159.32,
      "thumbnail": "https://cdn.dummyjson.com/products/images/sports-accessories/Cricket%20Helmet/thumbnail.png"
    },
    {...}
    // more products
  ],
  "total": 4794.8,
  "discountedTotal": 4288.95,
  "userId": 142,
  "totalProducts": 5,
  "totalQuantity": 20
}
Get carts by a user
// getting carts by user with id 5
fetch('https://dummyjson.com/carts/user/5')
.then(res => res.json())
.then(console.log);
{
  "carts": [
    {
      "id": 19,
      "products": [
        {
          "id": 144,
          "title": "Cricket Helmet",
          "price": 44.99,
          "quantity": 4,
          "total": 179.96,
          "discountPercentage": 11.47,
          "discountedTotal": 159.32,
          "thumbnail": "https://cdn.dummyjson.com/products/images/sports-accessories/Cricket%20Helmet/thumbnail.png"
        },
        {...}
        // more products
      ],
      "total": 2492,
      "discountedTotal": 2140,
      "userId": 5, // user id is 5
      "totalProducts": 5,
      "totalQuantity": 14
    }
  ],
  "total": 1,
  "skip": 0,
  "limit": 1
}
Limit and skip carts
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/carts?limit=2&skip=10&select=total,userId')
.then(res => res.json())
.then(console.log);
{
  "carts": [
    {
      "id": 11, // first 10 items were skipped
      "total": 61831.75,
      "userId": 11
    },
    {
      "id": 12,
      "total": 37767.32,
      "userId": 12
    }
  ],
  "total": 50,
  "skip": 10,
  "limit": 2
}
Sort carts
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/carts?sortBy=total&order=desc')
.then(res => res.json())
.then(console.log);
{
  "carts": [
    {
      "id": 30,
      "total": 186110.85, // sorted by total in descending order
      "userId": 30
      /* rest cart data */
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 50,
  "skip": 0,
  "limit": 30
}
Add a new cart
Adding a new cart will not add it into the server.
It will simulate a POST request and will return the new created cart with a new id

You can provide a userId and array of products as objects, containing productId & quantity

fetch('https://dummyjson.com/carts/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    userId: 1,
    products: [
      {
        id: 144,
        quantity: 4,
      },
      {
        id: 98,
        quantity: 1,
      },
    ]
  })
})
.then(res => res.json())
.then(console.log);
  {
    "id": 51,
    "products": [ // products were added by id
      {
        "id": 98,
        "title": "Rolex Submariner Watch",
        "price": 13999.99,
        "quantity": 4,
        "total": 55999.96,
        "discountPercentage": 0.82,
        "discountedPrice": 55541,
        "thumbnail": "https://cdn.dummyjson.com/products/images/mens-watches/Rolex%20Submariner%20Watch/thumbnail.png"
      },
      {
        "id": 144,
        "title": "Cricket Helmet",
        "price": 44.99,
        "quantity": 1,
        "total": 44.99,
        "discountPercentage": 10.75,
        "discountedPrice": 40,
        "thumbnail": "https://cdn.dummyjson.com/products/images/sports-accessories/Cricket%20Helmet/thumbnail.png"
      }
    ],
    "total": 56044.95, // total was calculated with quantity
    "discountedTotal": 55581,
    "userId": 1, // user id is 1
    "totalProducts": 2,
    "totalQuantity": 5 // total quantity of items
}
Update a cart
Updating a cart will not update it into the server.
It will simulate a PUT/PATCH request and will return updated cart with modified data

Pass "merge: true" to include old products when updating

You can provide a userId and array of products as objects, containing productId & quantity

/* adding products in cart with id 1 */
fetch('https://dummyjson.com/carts/1', {
  method: 'PUT', /* or PATCH */
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    merge: true, // this will include existing products in the cart
    products: [
      {
        id: 1,
        quantity: 1,
      },
    ]
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "products": [
    {
      "id": 1,
      "title": "Essence Mascara Lash Princess",
      "price": 9.99,
      "quantity": 1,
      "total": 9.99,
      "discountPercentage": 7.17,
      "discountedPrice": 9,
      "thumbnail": "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png"
    }
    {...}
    // other old products
  ],
  "total": 103784.84, // total was updated
  "discountedTotal": 89695, // discounted total was updated
  "userId": 33,
  "totalProducts": 5, // total products were updated
  "totalQuantity": 16 // total quantity was updated
}
Delete a cart
Deleting a cart will not delete it into the server.
It will simulate a DELETE request and will return deleted cart with isDeleted & deletedOn keys

fetch('https://dummyjson.com/carts/1', {
  method: 'DELETE',
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "products": [
    {
      "id": 168,
      "title": "Charger SXT RWD",
      "price": 32999.99,
      "quantity": 3,
      "total": 98999.97,
      "discountPercentage": 13.39,
      "discountedTotal": 85743.87,
      "thumbnail": "https://cdn.dummyjson.com/products/images/vehicle/Charger%20SXT%20RWD/thumbnail.png"
    },
    {...},
    // more products
  ],
  "total": 103774.85,
  "discountedTotal": 89686.65,
  "userId": 33,
  "totalProducts": 4,
  "totalQuantity": 15,
  "isDeleted": true,
  "deletedOn": /* ISOTime */
}