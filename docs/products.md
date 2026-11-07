Products - Docs
The products endpoint provides a comprehensive dataset of sample product information, including details like names, prices, descriptions, images, and categories, ideal for testing and prototyping e-commerce applications.

Get all products
By default you will get 30 items, use Limit and skip to paginate through all items.

fetch('https://dummyjson.com/products')
.then(res => res.json())
.then(console.log);
{
  "products": [
    {
      "id": 1,
      "title": "Essence Mascara Lash Princess",
      "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
      "category": "beauty",
      "price": 9.99,
      "discountPercentage": 7.17,
      "rating": 4.94,
      "stock": 5,
      "tags": [
        "beauty",
        "mascara"
      ],
      "brand": "Essence",
      "sku": "RCH45Q1A",
      "weight": 2,
      "dimensions": {
        "width": 23.17,
        "height": 14.43,
        "depth": 28.01
      },
      "warrantyInformation": "1 month warranty",
      "shippingInformation": "Ships in 1 month",
      "availabilityStatus": "Low Stock",
      "reviews": [
        {
          "rating": 2,
          "comment": "Very unhappy with my purchase!",
          "date": "2024-05-23T08:56:21.618Z",
          "reviewerName": "John Doe",
          "reviewerEmail": "john.doe@x.dummyjson.com"
        },
        {
          "rating": 2,
          "comment": "Not as described!",
          "date": "2024-05-23T08:56:21.618Z",
          "reviewerName": "Nolan Gonzalez",
          "reviewerEmail": "nolan.gonzalez@x.dummyjson.com"
        },
        {
          "rating": 5,
          "comment": "Very satisfied!",
          "date": "2024-05-23T08:56:21.618Z",
          "reviewerName": "Scarlett Wright",
          "reviewerEmail": "scarlett.wright@x.dummyjson.com"
        }
      ],
      "returnPolicy": "30 days return policy",
      "minimumOrderQuantity": 24,
      "meta": {
        "createdAt": "2024-05-23T08:56:21.618Z",
        "updatedAt": "2024-05-23T08:56:21.618Z",
        "barcode": "9164035109868",
        "qrCode": "..."
      },
      "thumbnail": "...",
      "images": ["...", "...", "..."]
    },
    {...},
    {...},
    {...}
    // 30 items
  ],
  "total": 194,
  "skip": 0,
  "limit": 30
}
Get a single product
fetch('https://dummyjson.com/products/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "title": "Essence Mascara Lash Princess",
  "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
  "category": "beauty",
  "price": 9.99,
  "discountPercentage": 7.17,
  "rating": 4.94,
  "stock": 5,
  "tags": [
    "beauty",
    "mascara"
  ],
  "brand": "Essence",
  "sku": "RCH45Q1A",
  "weight": 2,
  "dimensions": {
    "width": 23.17,
    "height": 14.43,
    "depth": 28.01
  },
  "warrantyInformation": "1 month warranty",
  "shippingInformation": "Ships in 1 month",
  "availabilityStatus": "Low Stock",
  "reviews": [
    {
      "rating": 2,
      "comment": "Very unhappy with my purchase!",
      "date": "2024-05-23T08:56:21.618Z",
      "reviewerName": "John Doe",
      "reviewerEmail": "john.doe@x.dummyjson.com"
    },
    {
      "rating": 2,
      "comment": "Not as described!",
      "date": "2024-05-23T08:56:21.618Z",
      "reviewerName": "Nolan Gonzalez",
      "reviewerEmail": "nolan.gonzalez@x.dummyjson.com"
    },
    {
      "rating": 5,
      "comment": "Very satisfied!",
      "date": "2024-05-23T08:56:21.618Z",
      "reviewerName": "Scarlett Wright",
      "reviewerEmail": "scarlett.wright@x.dummyjson.com"
    }
  ],
  "returnPolicy": "30 days return policy",
  "minimumOrderQuantity": 24,
  "meta": {
    "createdAt": "2024-05-23T08:56:21.618Z",
    "updatedAt": "2024-05-23T08:56:21.618Z",
    "barcode": "9164035109868",
    "qrCode": "..."
  },
  "thumbnail": "...",
  "images": ["...", "...", "..."]
}
Search products
fetch('https://dummyjson.com/products/search?q=phone')
.then(res => res.json())
.then(console.log);
{
  "products": [
    {
      "id": 101,
      "title": "Apple AirPods Max Silver",
      "category": "mobile-accessories"
      ...
    },
    {...},
    {...},
    {...}
    // 23 results
  ],
  "total": 23,
  "skip": 0,
  "limit": 23
}
Limit and skip products
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/products?limit=10&skip=10&select=title,price')
.then(res => res.json())
.then(console.log);
{
  "products": [
    {
      "id": 11, // first 10 items are skipped
      "title": "Annibale Colombo Bed",
      "price": 1899.99
    },
    {...},
    {...},
    {...}
    // 10 items
  ],
  "total": 194,
  "skip": 10,
  "limit": 10
}
Sort products
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/products?sortBy=title&order=asc')
.then(res => res.json())
.then(console.log);
{
  "products": [
    {
      "id": 167,
      "title": "300 Touring", // sorted by title in ascending order
      "price": 28999.99
      /* rest product data */
    },
    {
      "id": 99,
      "title": "Amazon Echo Plus", // sorted by title in ascending order
      "price": 99.99
      /* rest product data */
    },
    {...}
    // 30 items
  ],
  "total": 194,
  "skip": 0,
  "limit": 30
}
Filter products by last modified date
You can pass modifiedAfter and/or modifiedBefore (ISO 8601 dates) to only get products whose meta.updatedAt falls in that range. Works with all products list endpoints, including search and category, and can be combined with sortBy, limit, skip and select.

fetch('https://dummyjson.com/products?modifiedAfter=2026-06-01T00:00:00Z&select=title,meta')
.then(res => res.json())
.then(console.log);
{
  "products": [
    {
      "id": 10,
      "title": "Gucci Bloom Eau de",
      "meta": {
        "createdAt": "2026-07-19T20:10:42.419Z",
        "updatedAt": "2026-07-27T15:21:17.071Z", // modified after 2026-06-01
        "barcode": "3170832177880",
        "qrCode": "https://cdn.dummyjson.com/public/qr-code.png"
      }
    },
    {...},
    {...}
    // 30 items
  ],
  "total": 88,
  "skip": 0,
  "limit": 30
}
Get all products categories
fetch('https://dummyjson.com/products/categories')
.then(res => res.json())
.then(console.log);
[
  {
    "slug": "beauty",
    "name": "Beauty",
    "url": "https://dummyjson.com/products/category/beauty"
  },
  {
    "slug": "fragrances",
    "name": "Fragrances",
    "url": "https://dummyjson.com/products/category/fragrances"
  },
  {
    "slug": "furniture",
    "name": "Furniture",
    "url": "https://dummyjson.com/products/category/furniture"
  },
  {...},
  {...},
  {...}
  // more items
]
Get products category list
fetch('https://dummyjson.com/products/category-list')
.then(res => res.json())
.then(console.log);
[
  "beauty",
  "fragrances",
  "furniture",
  "groceries",
  "home-decoration",
  "kitchen-accessories",
  "laptops",
  "mens-shirts",
  "mens-shoes",
  "mens-watches",
  "mobile-accessories",
  "motorcycle",
  "skin-care",
  "smartphones",
  "sports-accessories",
  "sunglasses",
  "tablets",
  "tops",
  "vehicle",
  "womens-bags",
  "womens-dresses",
  "womens-jewellery",
  "womens-shoes",
  "womens-watches"
]
Get products by a category
fetch('https://dummyjson.com/products/category/smartphones')
.then(res => res.json())
.then(console.log);
{
  "products": [
    {
      "id": 122,
      "title": "iPhone 6",
      "category": "smartphones",
      ...
    },
    {...}
    // 16 items
  ],
  "total": 16,
  "skip": 0,
  "limit": 16
}
Add a new product
Adding a new product will not add it into the server.
It will simulate a POST request and will return the new created product with a new id

fetch('https://dummyjson.com/products/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    title: 'BMW Pencil',
    /* other product data */
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 195,
  "title": "BMW Pencil",
  /* other product data */
}
Update a product
Updating a product will not update it into the server.
It will simulate a PUT/PATCH request and will return updated product with modified data

/* updating title of product with id 1 */
fetch('https://dummyjson.com/products/1', {
  method: 'PUT', /* or PATCH */
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    title: 'iPhone Galaxy +1'
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "title": "iPhone Galaxy +1", // only title was updated
  /* other product data */
}
Delete a product
Deleting a product will not delete it into the server.
It will simulate a DELETE request and will return deleted product with isDeleted & deletedOn keys

fetch('https://dummyjson.com/products/1', {
  method: 'DELETE',
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "title": "Essence Mascara Lash Princess",
  /* other product data */
  "isDeleted": true,
  "deletedOn": /* ISOTime */
}