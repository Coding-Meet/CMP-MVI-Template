Recipes - Docs
The recipes endpoint offers a dataset of sample recipe data, including details like recipe names, ingredients, instructions, and images, useful for testing and prototyping cooking and food-related applications.

Get all recipes
By default you will get 30 items, use Limit and skip to paginate through all items.

fetch('https://dummyjson.com/recipes')
.then(res => res.json())
.then(console.log);
{
  "recipes": [
    {
      "id": 1,
      "name": "Classic Margherita Pizza",
      "ingredients": [
        "Pizza dough",
        "Tomato sauce",
        "Fresh mozzarella cheese",
        "Fresh basil leaves",
        "Olive oil",
        "Salt and pepper to taste"
      ],
      "instructions": [
        "Preheat the oven to 475°F (245°C).",
        "Roll out the pizza dough and spread tomato sauce evenly.",
        "Top with slices of fresh mozzarella and fresh basil leaves.",
        "Drizzle with olive oil and season with salt and pepper.",
        "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
        "Slice and serve hot."
      ],
      "prepTimeMinutes": 20,
      "cookTimeMinutes": 15,
      "servings": 4,
      "difficulty": "Easy",
      "cuisine": "Italian",
      "caloriesPerServing": 300,
      "tags": [
        "Pizza",
        "Italian"
      ],
      "userId": 45,
      "image": "https://cdn.dummyjson.com/recipe-images/1.webp",
      "rating": 4.6,
      "reviewCount": 3,
      "mealType": [
        "Dinner"
      ]
    },
    {...},
    {...},
    {...}
    // 30 items
  ],

  "total": 100,
  "skip": 0,
  "limit": 30
}
Get a single recipe
fetch('https://dummyjson.com/recipes/1')
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "name": "Classic Margherita Pizza",
  "ingredients": [
    "Pizza dough",
    "Tomato sauce",
    "Fresh mozzarella cheese",
    "Fresh basil leaves",
    "Olive oil",
    "Salt and pepper to taste"
  ],
  "instructions": [
    "Preheat the oven to 475°F (245°C).",
    "Roll out the pizza dough and spread tomato sauce evenly.",
    "Top with slices of fresh mozzarella and fresh basil leaves.",
    "Drizzle with olive oil and season with salt and pepper.",
    "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
    "Slice and serve hot."
  ],
  "prepTimeMinutes": 20,
  "cookTimeMinutes": 15,
  "servings": 4,
  "difficulty": "Easy",
  "cuisine": "Italian",
  "caloriesPerServing": 300,
  "tags": [
    "Pizza",
    "Italian"
  ],
  "userId": 45,
  "image": "https://cdn.dummyjson.com/recipe-images/1.webp",
  "rating": 4.6,
  "reviewCount": 3,
  "mealType": [
    "Dinner"
  ]
}
Search recipes
fetch('https://dummyjson.com/recipes/search?q=Margherita')
.then(res => res.json())
.then(console.log);
{
  "recipes": [
    {
      "id": 1,
      "name": "Classic Margherita Pizza",
      "ingredients": [
        "Pizza dough",
        "Tomato sauce",
        "Fresh mozzarella cheese",
        "Fresh basil leaves",
        "Olive oil",
        "Salt and pepper to taste"
      ],
      "instructions": [
        "Preheat the oven to 475°F (245°C).",
        "Roll out the pizza dough and spread tomato sauce evenly.",
        "Top with slices of fresh mozzarella and fresh basil leaves.",
        "Drizzle with olive oil and season with salt and pepper.",
        "Bake in the preheated oven for 12-15 minutes or until the crust is golden brown.",
        "Slice and serve hot."
      ],
      "prepTimeMinutes": 20,
      "cookTimeMinutes": 15,
      "servings": 4,
      "difficulty": "Easy",
      "cuisine": "Italian",
      "caloriesPerServing": 300,
      "tags": [
        "Pizza",
        "Italian"
      ],
      "userId": 45,
      "image": "https://cdn.dummyjson.com/recipe-images/1.webp",
      "rating": 4.6,
      "reviewCount": 3,
      "mealType": [
        "Dinner"
      ]
    },
    {...},
    {...},
    {...}
    // 1 result
  ],
  "total": 1,
  "skip": 0,
  "limit": 1
}
Limit and skip recipes
You can pass limit and skip params to limit and skip the results for pagination, and use limit=0 to get all items.

You can pass select as query params with comma-separated values to select specific data

fetch('https://dummyjson.com/recipes?limit=10&skip=10&select=name,image')
.then(res => res.json())
.then(console.log);
{
  "recipes": [
    {
      "id": 11, // first 10 items are skipped
      "name": "Chicken Biryani",
      "image": "https://cdn.dummyjson.com/recipe-images/11.webp",
    },
    {...},
    {...},
    {...}
    // 10 items
  ],

  "total": 50,
  "skip": 10,
  "limit": 10
}
Sort recipes
You can pass sortBy and order params to sort the results, sortBy should be field name and order should be "asc" or "desc"

fetch('https://dummyjson.com/recipes?sortBy=name&order=asc')
.then(res => res.json())
.then(console.log);
{
  "recipes": [
    {
      "id": 13,
      "name": "Aloo Keema", // sorted by name in ascending order
      "image": "https://cdn.dummyjson.com/recipe-images/13.webp",
      "difficulty": "Medium",
      "cuisine": "Pakistani"
      /* rest recipe data */
    },
    {
      "id": 8,
      "name": "Beef and Broccoli Stir-Fry", // sorted by name in ascending order
      "image": "https://cdn.dummyjson.com/recipe-images/13.webp",
      "difficulty": "Medium",
      "cuisine": "Asian"
      /* rest recipe data */
    },
    {...}
    // 30 items
  ],
  "total": 50,
  "skip": 0,
  "limit": 30
}
Get all recipes tags
fetch('https://dummyjson.com/recipes/tags')
.then(res => res.json())
.then(console.log);
[
  "Pizza",
  "Italian",
  "Vegetarian",
  "Stir-fry",
  "Asian",
  "Cookies",
  "Dessert",
  "Baking",
  "Pasta",
  "Chicken",
  "Salsa",
  "Salad",
  "Quinoa",
  "Bruschetta",
  "Beef",
  "Caprese",
  "Shrimp",
  "Biryani",
  "Main course",
  "Indian",
  "Pakistani",
  "Karahi",
  "Keema",
  ...
  ...
  ...
]
Get recipes by a tag
fetch('https://dummyjson.com/recipes/tag/Pakistani')
.then(res => res.json())
.then(console.log);
{
  "recipes": [
    {
      "id": 11,
      "name": "Chicken Biryani",
      "tags": [
        "Biryani",
        "Chicken",
        "Main course",
        "Indian",
        "Pakistani",
        "Asian"
      ],
      ...
      ...
    }
  ],
  "total": 7,
  "skip": 0,
  "limit": 7
}
Get recipes by a meal
fetch('https://dummyjson.com/recipes/meal-type/snack')
.then(res => res.json())
.then(console.log);
{
  "recipes": [
    {
      "id": 3,
      "name": "Chocolate Chip Cookies",
      "mealType": [
      "Snack",
      "Dessert"
      ]
      ...
      ...
    },
    {...},
    {...},
  ],
  "total": 3,
  "skip": 0,
  "limit": 3
}
Add Recipe
Adding a recipe will not add it into the server.
It will simulate a POST request and will return a new created recipe with a new id

fetch('https://dummyjson.com/recipes/add', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Tasty Pizza'
    /* other recipe data */
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 51,
  "name": "Tasty Pizza",
  {...}
}
Update Recipe
Updating a recipe will not update it into the server.
It will simulate a PUT/PATCH request and will return updated recipe with modified data

/* updating name of recipe with id 1 */
fetch('https://dummyjson.com/recipes/1', {
  method: 'PUT', /* or PATCH */
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    name: 'Tasty Pizza'
  })
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  "name": "Tasty Pizza",
  {...}
}
Delete Recipe
Deleting a recipe will not delete it into the server.
It will simulate a DELETE request and will return deleted recipe with isDeleted & deletedOn keys

/* deleting recipe with id 1 */
fetch('https://dummyjson.com/recipes/1', {
  method: 'DELETE'
})
.then(res => res.json())
.then(console.log);
{
  "id": 1,
  {...}
  "isDeleted": true,
  "deletedOn": /* ISOTime */
}