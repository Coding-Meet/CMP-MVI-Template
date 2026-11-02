svg iconDeveloper Tools - Docs
DummyJSON provides free developer tools for testing and prototyping — generate real TOTP verification codes, create custom JSON API endpoints, and inspect webhook requests.

2FA TOTP — Generate (GET)
Pass your base32 secret key as a query parameter:
dummyjson.com/2fa?key=YOUR_SECRET

fetch('https://dummyjson.com/2fa?key=JBSWY3DPEHPK3PXP')
.then(res => res.json())
.then(console.log);
{
  "totp": "123456",
  "expiresIn": 17,
  "period": 30
}
2FA TOTP — Generate (POST)
Prefer POST when sending secrets — it keeps the key out of URLs and server logs.

fetch('https://dummyjson.com/2fa', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ key: 'JBSWY3DPEHPK3PXP' })
})
.then(res => res.json())
.then(console.log);
{
  "totp": "123456",
  "expiresIn": 17,
  "period": 30
}
2FA TOTP — Supported Secret Formats
The endpoint accepts the following secret formats:

Base32 secret — e.g. JBSWY3DPEHPK3PXP (spaces and dashes are stripped automatically)
otpauth URI — e.g. otpauth://totp/Label?secret=JBSWY3DPEHPK3PXP&issuer=Example
Try the interactive tool at dummyjson.com/2fa. The endpoint is also available at /totp.

Custom Response — Generate API
Create a mock REST API by sending your JSON response body and preferred HTTP method. Returns a unique URL you can call from your app.

fetch('https://dummyjson.com/c/generate', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    json: { message: 'Hello from my custom API' },
    method: 'GET'
  })
})
.then(res => res.json())
.then(console.log);
{
  "url": "https://dummyjson.com/c/f4d8-2771-4d04-addf"
}
Custom Response — Use Your API
Call your generated URL with the HTTP method you specified when creating it:

fetch('https://dummyjson.com/c/f4d8-2771-4d04-addf')
.then(res => res.json())
.then(console.log);
{
  "message": "Hello from my custom API"
}
Custom Response — Notes
A few things to keep in mind when using custom responses:

Supported methods: GET, POST, PUT, PATCH, DELETE
Maximum payload size: 300 KB
Responses expire after 90 days — check the x-expires-on response header
Use the interactive builder at dummyjson.com/custom-response.

Webhook — Create Endpoint
Create a temporary webhook URL to capture and inspect incoming HTTP requests:

fetch('https://dummyjson.com/webhook/create', {
  method: 'POST'
})
.then(res => res.json())
.then(console.log);
{
  "identifier": "a3f9k2m1",
  "url": "https://dummyjson.com/webhook/a3f9k2m1",
  "expiresAt": "2026-07-07T12:00:00.000Z"
}
Webhook — Capture Requests
Send any HTTP request to your webhook URL. The request is stored and the endpoint responds with:

fetch('https://dummyjson.com/webhook/a3f9k2m1', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ event: 'order.created' })
})
.then(res => res.json())
.then(console.log);
{
  "received": true,
  "requestId": "f47ac10b-58cc-4372-a567-0e02b2c3d479"
}
Webhook — List Captured Requests
Poll this endpoint to retrieve all captured requests for a webhook:

fetch('https://dummyjson.com/webhook/a3f9k2m1/requests')
.then(res => res.json())
.then(console.log);
{
  "identifier": "a3f9k2m1",
  "expiresAt": "2026-07-07T12:00:00.000Z",
  "requests": [
    {
      "requestId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
      "method": "POST",
      "headers": { "content-type": "application/json" },
      "query": {},
      "body": { "event": "order.created" },
      "receivedAt": "2026-07-06T12:00:00.000Z"
    }
  ]
}
Webhook — Delete a Request
Remove a single captured request by its requestId:

fetch('https://dummyjson.com/webhook/a3f9k2m1/requests/f47ac10b-58cc-4372-a567-0e02b2c3d479', {
  method: 'DELETE'
})
.then(res => res.json())
.then(console.log);
{
  "deleted": true
}
Webhook — Notes
A few things to keep in mind when using webhooks:

All HTTP methods are supported: GET, POST, PUT, PATCH, DELETE, OPTIONS, etc.
Maximum payload size: 300 KB
Maximum 100 requests stored per webhook — oldest requests are dropped when the limit is reached
Webhooks expire after 1 day — check the x-expires-on response header
Sensitive headers (authorization, cookie, x-api-key) are stripped before storage
Use the interactive inspector at dummyjson.com/webhook.