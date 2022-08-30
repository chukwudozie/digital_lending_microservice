# Digital Lending Microservice

## A simple microservice to mimic a digital lending platform.

### A customer can request a loan offer and be presented with one or more loan offers based on the customer loan maximum qualification. From the offers presented, a customer can choose and accept  one of the offers.

### When a customer accepts an offer, then we will credit his mobile wallet.

### Make a Loan Request with postman on

url: http://localhost:8080/request

with request body

Sample

```json
    {
  "accountNumber": "07036771035",
  "amount": 1000,
  "type": "A"
}
```

### Create a Customer with postman on

url: http://localhost:8080/customer/request

+ with request body:
+ Sample

```json
    {
  "firstName": "first name",
  "lastName": "last name",
  "phoneNumber": "07036771035",
  "email": "xxx@gmail.com",
  "password": "xxxx"
}
```

