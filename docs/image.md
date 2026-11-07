svg iconImage - Docs
The image endpoint provides customizable placeholder images by specifying size in the URL, with options for background color, text color, and display text, ideal for use in websites and wireframes.

The base URL is: dummyjson.com/image

Generate square image
// https://dummyjson.com/image/SIZE
fetch('https://dummyjson.com/image/150')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}
Output:
150x150
Generate custom size image
// https://dummyjson.com/image/WIDTHxHEIGHT
fetch('https://dummyjson.com/image/200x100')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}
Output:
200x100
Generate image with custom text
// https://dummyjson.com/image/SIZE/?text=TEXT
fetch('https://dummyjson.com/image/400x200/008080/ffffff?text=Hello+Peter')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}
Output:
400x200
Generate image with custom colors
// https://dummyjson.com/image/SIZE/BACKGROUND/COLOR
fetch('https://dummyjson.com/image/400x200/282828')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}
Output:
400x200
Generate image with different formats
Supported Formats: png, jpeg, webp

// https://dummyjson.com/image/SIZE/BACKGROUND/COLOR
fetch('https://dummyjson.com/image/400x200?type=webp&text=I+am+a+webp+image')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/webp'}
Output:
400x200
Generate image with custom font family
Supported Fonts:
bitter, cairo, comfortaa, cookie, dosis, gotham, lobster, marhey, pacifico, poppins, quicksand, qwigley, satisfy, ubuntu

// https://dummyjson.com/image/SIZE/BACKGROUND/COLOR
fetch('https://dummyjson.com/image/400x200/282828?fontFamily=pacifico&text=I+am+a+pacifico+font')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}
Output:
400x200
Generate image with custom font size
// https://dummyjson.com/image/SIZE/?text=TEXT&fontSize=FONT_SIZE
fetch('https://dummyjson.com/image/400x200/008080/ffffff?text=Hello+Peter!&fontSize=16')
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}
Output:
400x200
Generate identicon
// https://dummyjson.com/icon/HASH/SIZE/?type=png (or svg)
fetch('https://dummyjson.com/icon/abc123/150') // png is default
.then(response => response.blob()) // Convert response to blob
.then(blob => {
  console.log('Fetched image blob:', blob);
})
// Blob {size: SIZE, type: 'image/png'}