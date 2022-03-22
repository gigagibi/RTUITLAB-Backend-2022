# RTUITLAB-Backend-2022
This project is a pizzeria automation backend service.

This project allows managing orders, supplies and products information, also making an remote delivery orders through website.
## Tech stack
- Java
- Spring Boot (including Spring Security, Spring Data)
- Spring Cloud
- PostgreSQL
- MongoDB
- JWT (com.auth0 library)
- RabbitMQ
- Docker

**Additional**
- Lombok
- Swagger UI
- Maven
## Run with docker-compose
In root directory:
```bash
docker-compose up -d
```
## Project structure
### Services
Project is service-oriented, includes 6 services:
1. Eureka service (spring discovery service)
2. Gateway service
3. Products service (used for managing information about products from menu)
4. Orders service (used for managing orders, including delivery orders)
5. Supplies service (used for managing supplies)
6. Deliveries service (backend of website that can be used by clients for making remote delivery orders)

Project uses maven build system, and each service is a child maven module of parent project

![пиццерия](https://user-images.githubusercontent.com/70891118/159545499-aea53d60-808d-406e-a152-e7037f9a11d2.png)

**Backend interaction scenarios**

1. Visitor can make order through the website, self-service machine or cashier. Cashier uses **cash register** which interacts with **products** service to get info about products, and **orders** service to insert order's details. Visitor can also make an order by himself using **self-service machine**, which is also interacts with **orders** and **products** services. Also, he can remotely make an order throgh **client application** (web or mobile) which interacts with **deliveries** service
2. Supplies manager can insert, edit, delete and get information about supplies through **client application** which interacts with **supplies** service
3. Also, there are two **monitor sets** - one of them displays cooked orders numbers (placed in the hall), and the other one displays information about received orders (placed in the kitchen), both interact with **orders** service
4. All interactions are performed through the **gateway** service

## Default users in deliveries service
### Admin
Login: ```admin```
Password: ```admin```
### User
Login: ```user```
Password: ```user```


## API
Gateway routes:
- products: http://{gateway_host}:{gateway_port}/products
- orders: http://{gateway_host}:{gateway_port}/orders
- supplies: http://{gateway_host}:{gateway_port}/supplies
- deliveries: http://{gateway_host}:{gateway_port}/deliveries

Swagger UI is available at the url: http://{service_host}:{service_port}/swagger-ui/#/

Default admin's jwt token for swagger: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl19.ffAE6hrHk7gJrVG7otX07gVSjHI50UxxIokNVW84ba0

Default user's jwt token for swagger: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfQURNSU4iXX0.dRzCW0k1oCxoqzeT_G5FzItA2FJuKLZIdySqkXyFjGM
### Orders Service
#### 1. GET ALL DELIVERY ORDERS (GET)
URL: ​/api​/v1​/delivery_order​/

Response body:
```json
[
  {
    "address": "string",
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:15:22.651Z",
    "phone": "string",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 2. CREATE DELIVERY ORDER (POST)
URL: ​/api​/v1​/delivery_order​/

Request body:
```json
{
  "address": "string",
  "phone": "string",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```
Response body:
```json
[
  {
    "address": "string",
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:14:51.952Z",
    "phone": "string",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 3. DELETE ALL DELIVERY ORDERS (DELETE)
URL: ​/api​/v1​/delivery_order​/

Response body:
```json
[
  {
    "address": "string",
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:14:51.952Z",
    "phone": "string",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 4. GET DELIVERY ORDER (GET)
URL: ​/api​/v1​/delivery_order​/{id}

Parametes: 
- id:string

Response body:
```json
{
  "address": "string",
  "cost": 0,
  "id": "string",
  "number": 0,
  "orderDate": "2022-03-17T11:59:20.655Z",
  "phone": "string",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```
#### 5. UPDATE DELIVERY ORDER (PUT)
URL: ​/api​/v1​/delivery_order​/{id}
Parametes: 
- id:string

Request body:
```json
{
  "address": "string",
  "orderDate": "2022-03-17T11:17:46.942Z",
  "phone": "string",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```

Response body:
```json
{
  "address": "string",
  "cost": 0,
  "id": "string",
  "number": 0,
  "orderDate": "2022-03-17T11:59:35.307Z",
  "phone": "string",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```
#### 6. DELETE DELIVERY ORDER (DELETE)
URL: ​/api​/v1​/delivery_order​/{id}

Parametes: 
- id:string

Response body:
```json
[
  {
    "address": "string",
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:20:26.184Z",
    "phone": "string",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 7. GET ALL ORDERS (GET)
URL: ​/api​/v1​/order​/

Response body:
```json
[
  {
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:21:53.420Z",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 8. CREATE ORDER (POST)
URL: ​/api​/v1​/order​/

Request body:
```json
{
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```
Response body:
```json
[
  {
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:22:05.896Z",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 9. DELETE ALL ORDERS (DELETE)
URL: ​/api​/v1​/order​/

Response body:
```json
[
  {
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:22:55.181Z",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
#### 10. GET ORDER (GET)
URL: ​/api​/v1​/order​/{id}

Parametes: 
- id:string

Response body:
```json
 {
  "cost": 0,
  "id": "string",
  "number": 0,
  "orderDate": "2022-03-17T11:23:06.732Z",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```
#### 11. UPDATE ORDER (PUT)
URL: ​/api​/v1​/order​/{id}
Parametes: 
- id:string

Request body:
```json
{
  "orderDate": "2022-03-17T10:33:41.982Z",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```

Response body:
```json
{
  "cost": 0,
  "id": "string",
  "number": 0,
  "orderDate": "2022-03-17T11:23:57.136Z",
  "products": [
    {
      "amount": 0,
      "cost": 0,
      "id": 0
    }
  ]
}
```
#### 12. DELETE ORDER (DELETE)
URL: ​/api​/v1​/order​/{id}

Parametes: 
- id:string

Response body:
```json
[
  {
    "cost": 0,
    "id": "string",
    "number": 0,
    "orderDate": "2022-03-17T11:24:21.834Z",
    "products": [
      {
        "amount": 0,
        "cost": 0,
        "id": 0
      }
    ]
  }
]
```
### Products Service
#### 1. GET ALL CATEGORIES (GET)
URL: ​/api​/v1​/categories​/

Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#### 2. CREATE CATEGORY (POST)
URL: ​/api​/v1​/categories​/

Request body:
```json
{
  "name": "pizza"
}
```
Response body:
```json
[
  {
    "id": 1,
    "name": "pizza"
  }
]
```
#### 3. DELETE CATEGORIES (DELETE)
URL: ​/api​/v1​/categoriesr​/

Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#### 4. GET CATEGORY (GET)
URL: ​/api​/v1​/categories​/{id}

Parametes: 
- id:int

Response body:
```json
{
  "id": 0,
  "name": "string"
}
```
#### 5. UPDATE CATEGORY (PUT)
URL: ​/api​/v1​/categories​/{id}
Parametes: 
- id:int

Request body:
```json
{
  "name": "pizza"
}
```

Response body:
```json
{
  "id": 1,
  "name": "pizza"
}
```
#### 6. DELETE CATEGORY (DELETE)
URL: ​/api​/v1​/categories​/{id}

Parametes: 
- id:int

Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#### 7. GET PRODUCTS (GET)
URL: ​/api​/v1​/products​/

Response body:
```json
[
  {
    "category": {
      "id": 0,
      "name": "string"
    },
    "cost": 0,
    "description": "string",
    "id": 0,
    "name": "string"
  }
]
```
#### 8. CREATE PRODUCT (POST)
URL: ​/api​/v1​/products​/

Request body:
```json
{
  "categoryId": 0,
  "cost": 0,
  "description": "string",
  "imagePath": "string",
  "name": "string"
}
```
Response body:
```json
[
  {
    "category": {
      "id": 0,
      "name": "string"
    },
    "cost": 0,
    "description": "string",
    "id": 0,
    "imagePath": "string",
    "name": "string"
  }
]
```
#### 9. DELETE ALL PRODUCTS (DELETE)
URL: ​/api​/v1​/products​/

Response body:
```json
[
  {
    "category": {
      "id": 0,
      "name": "string"
    },
    "cost": 0,
    "description": "string",
    "id": 0,
    "name": "string"
  }
]
```
#### 10. GET PRODUCT (GET)
URL: ​/api​/v1​/products​/{id}

Parametes: 
- id:int

Response body:
```json
 {
  "category": {
    "id": 0,
    "name": "string"
  },
  "cost": 0,
  "description": "string",
  "id": 0,
  "name": "string"
}
```
#### 11. UPDATE PRODUCT (PUT)
URL: ​/api​/v1​/products​/{id}
Parametes: 
- id:int

Request body:
```json
{
  "categoryId": 0,
  "cost": 0,
  "description": "string",
  "imagePath": "string",
  "name": "string"
}
```

Response body:
```json
{
  "category": {
    "id": 0,
    "name": "string"
  },
  "cost": 0,
  "description": "string",
  "id": 0,
  "imagePath": "string",
  "name": "string"
}
```
#### 12. DELETE PRODUCT (DELETE)
URL: ​/api​/v1​/products​/{id}

Parametes: 
- id:int

Response body:
```json
[
  {
    "category": {
      "id": 0,
      "name": "string"
    },
    "cost": 0,
    "description": "string",
    "id": 0,
    "name": "string"
  }
]
```
#### 13. GET PRODUCT'S COST
URL: ​/api​/v1​/products​/{id}/cost

Parametes: 
- id:int

Response body
  cost:int
#### 14. GET PRODUCT'S IMAGE
URL: ​/api​/v1​/products​/{id}/image

Parametes: 
- id:int

Response body
  imageBytes:String
#### 15. GET PRODUCT BY CATEGORY'S ID
URL: /api/v1/products/withCategory/{id}

Parametes: 
- id:int

Response body:
```json
[
  {
    "category": {
      "id": 0,
      "name": "string"
    },
    "cost": 0,
    "description": "string",
    "id": 0,
    "name": "string"
  }
]
```
#### 16. GET PRODUCT BY NAME
URL: /api/v1/products/withName/{name}

Parametes: 
- name:string

Response body:
```json
{
  "category": {
    "id": 0,
    "name": "string"
  },
  "cost": 0,
  "description": "string",
  "id": 0,
  "name": "string"
}
```
### Supplies Service
#### 1. GET ALL SUPPLIERS (GET)
URL: ​/api​/v1​/suppliers​/

Response body:
```json
[
  {
    "address": "string",
    "id": "string",
    "name": "string",
    "phone": "string"
  }
]
```
#### 2. CREATE SUPPLIERS (POST)
URL: ​/api​/v1​/suppliers​/

Request body:
```json
{
  "address": "string",
  "name": "string",
  "phone": "string"
}
```
Response body:
```json
[
  {
    "address": "string",
    "id": "string",
    "name": "string",
    "phone": "string"
  }
]

```
#### 3. DELETE ALL SUPPLIERS (DELETE)
URL: ​/api​/v1​/suppliers​/

Response body:
```json
[
  {
    "address": "string",
    "id": "string",
    "name": "string",
    "phone": "string"
  }
]
```
#### 4. GET SUPPLIER (GET)
URL: ​/api​/v1​/suppliers​/{id}

Parametes: 
- id:string

Response body:
```json
{
  "address": "string",
  "id": "string",
  "name": "string",
  "phone": "string"
}
```
#### 5. UPDATE SUPPLIER (PUT)
URL: ​/api​/v1​/suppliers​/{id}
Parametes: 
- id:string

Request body:
```json
{
  "address": "string",
  "name": "string",
  "phone": "string"
}
```

Response body:
```json
{
  "address": "string",
  "id": "string",
  "name": "string",
  "phone": "string"
}
```
#### 6. DELETE SUPPLIER (DELETE)
URL: ​/api​/v1​/suppliers​/{id}

Parametes: 
- id:string

Response body:
```json
[
  {
    "address": "string",
    "id": "string",
    "name": "string",
    "phone": "string"
  }
]
```
#### 7. GET ALL SUPPLIES (GET)
URL: ​/api​/v1​/supplies​/

Response body:
```json
[
  {
    "id": "string",
    "summaryCost": 0,
    "supplier_id": "string",
    "supplyDate": "2022-03-17T11:56:47.018Z",
    "supplyProductInfos": [
      {
        "amount": 0,
        "cost": 0,
        "description": "string",
        "name": "string"
      }
    ]
  }
]
```
#### 8. CREATE SUPPLIES (POST)
URL: ​/api​/v1​/supplies​/

Request body:
```json
{
  "supplier_id": "string",
  "supplyDate": "2022-03-17T11:57:00.538Z",
  "supplyProductInfos": [
    {
      "amount": 0,
      "cost": 0,
      "description": "string",
      "name": "string"
    }
  ]
}
```
Response body:
```json
[
  {
    "id": "string",
    "supplier_id": "string",
    "supplyDate": "2022-03-17T11:57:00.558Z",
    "supplyProductInfos": [
      {
        "amount": 0,
        "cost": 0,
        "description": "string",
        "name": "string"
      }
    ]
  }
]
```
#### 9. DELETE ALL SUPPLIES (DELETE)
URL: ​/api​/v1​/supplies​/

Response body:
```json
[
  {
    "id": "string",
    "summaryCost": 0,
    "supplier_id": "string",
    "supplyDate": "2022-03-17T11:57:10.311Z",
    "supplyProductInfos": [
      {
        "amount": 0,
        "cost": 0,
        "description": "string",
        "name": "string"
      }
    ]
  }
]
```
#### 10. GET SUPPLY (GET)
URL: ​/api​/v1​/supplies​/{id}

Parametes: 
- id:string

Response body:
```json
{
  "id": "string",
  "summaryCost": 0,
  "supplier_id": "string",
  "supplyDate": "2022-03-17T11:57:17.207Z",
  "supplyProductInfos": [
    {
      "amount": 0,
      "cost": 0,
      "description": "string",
      "name": "string"
    }
  ]
}
```
#### 11. UPDATE SUPPLY (PUT)
URL: ​/api​/v1​/supplies​/{id}
Parametes: 
- id:string

Request body:
```json
{
  "supplier_id": "string",
  "supplyDate": "2022-03-17T11:57:28.622Z",
  "supplyProductInfos": [
    {
      "amount": 0,
      "cost": 0,
      "description": "string",
      "name": "string"
    }
  ]
}
```

Response body:
```json
{
  "id": "string",
  "supplier_id": "string",
  "supplyDate": "2022-03-17T11:57:28.654Z",
  "supplyProductInfos": [
    {
      "amount": 0,
      "cost": 0,
      "description": "string",
      "name": "string"
    }
  ]
}
```
#### 12. DELETE SUPPLY (DELETE)
URL: ​/api​/v1​/supplies​/{id}

Parametes: 
- id:string

Response body:
```json
[
  {
    "id": "string",
    "summaryCost": 0,
    "supplier_id": "string",
    "supplyDate": "2022-03-17T11:57:50.991Z",
    "supplyProductInfos": [
      {
        "amount": 0,
        "cost": 0,
        "description": "string",
        "name": "string"
      }
    ]
  }
]
```
### Deliveries Service
#### 1. LOGIN (POST)
URL: /api/v1/auth/login

Request body:
```json
{
  "password": "admin",
  "username": "admin"
}
```

Response body:
```json
{
  "role": "string",
  "token": "string"
}
```
#### 2. REGISTER (POST)
URL: /api/v1/auth/register

Request body:
```json
{
  "address": "string",
  "name": "string",
  "password": "string",
  "phone": "string",
  "surname": "string",
  "username": "string"
}
```

Response body:
```json
{
  "address": "string",
  "name": "string",
  "password": "string",
  "phone": "string",
  "surname": "string",
  "username": "string"
}
```
#### 3. SEND ORDER (POST) (AUTHORIZED ONLY)
URL: /api/v1/deliveries/order/

Request body:
```json
{
  "address": "my",
  "phone": "my",
  "products": [
    {
      "amount": 1,
      "cost": 0,
      "id": 2
    }
  ]
}
```
#### 4. GET ALL PRODUCTS FROM PRODUCTS SERVICE (GET) (AUTHORIZED ONLY)
URL: /api/v1/deliveries/products

Response body:
```json
[
  {
    "category": {
      "id": 0,
      "name": "string"
    },
    "cost": 0,
    "description": "string",
    "id": 0,
    "name": "string"
  }
]
```
#### 5. GET PRODUCT FROM PRODUCTS SERVICE (GET) (AUTHORIZED ONLY)
URL: /api/v1/deliveries/products/{id}

Parameters:
- id:int

Response body:
```json
{
  "category": {
    "id": 0,
    "name": "string"
  },
  "cost": 0,
  "description": "string",
  "id": 0,
  "name": "string"
}
```
#### 6. GET ALL ROLES (GET) (ADMIN ONLY)
URL: ​/api​/v1​/roles​/

Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#### 7. CREATE ROLE (POST) (ADMIN ONLY)
URL: ​/api​/v1​/roles​/

Request body:
```json
{
  "id": 0,
  "name": "string"
}
```
Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]

```
#### 8. DELETE ALL ROLES (DELETE) (ADMIN ONLY)
URL: ​/api​/v1​/roles​/

Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#### 9. UPDATE ROLE (PUT) (ADMIN ONLY)
URL: ​/api​/v1​/roles​/{id}
Parametes: 
- id:int

Request body:
```json
{
  "id": 0,
  "name": "string"
}
```

Response body:
```json
{
  "id": 0,
  "name": "string"
}
```
#### 10. DELETE ROLE (DELETE) (ADMIN ONLY)
URL: ​/api​/v1​/roles​/{id}

Parametes: 
- id:int

Response body:
```json
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#### 11. GET ALL USERS (GET) (ADMIN ONLY)
URL: ​/api​/v1​/users​/

Response body:
```json
[
  {
    "address": "string",
    "id": 0,
    "name": "string",
    "password": "string",
    "phone": "string",
    "role": {
      "id": 0,
      "name": "string"
    },
    "surname": "string",
    "username": "string"
  }
]
```
#### 12. CREATE USER (POST) (ADMIN ONLY)
URL: ​/api​/v1​/users​/

Request body:
```json
{
  "address": "string",
  "id": 0,
  "name": "string",
  "password": "string",
  "phone": "string",
  "role": {
    "id": 0,
    "name": "string"
  },
  "surname": "string",
  "username": "string"
}
```

Response body:
```json
[
  {
    "address": "string",
    "id": 0,
    "name": "string",
    "password": "string",
    "phone": "string",
    "role": {
      "id": 0,
      "name": "string"
    },
    "surname": "string",
    "username": "string"
  }
]
```
#### 13. DELETE ALL USERS (DELETE) (ADMIN ONLY)
URL: ​/api​/v1​/users​/

Response body:
```json
[
  {
    "address": "string",
    "id": 0,
    "name": "string",
    "password": "string",
    "phone": "string",
    "role": {
      "id": 0,
      "name": "string"
    },
    "surname": "string",
    "username": "string"
  }
]
```
#### 14. DELETE USER (DELETE) (ADMIN ONLY)
URL: ​/api​/v1​/users​/{id}

Parametes: 
- id:string

Response body:
```json
[
  {
    "address": "string",
    "id": 0,
    "name": "string",
    "password": "string",
    "phone": "string",
    "role": {
      "id": 0,
      "name": "string"
    },
    "surname": "string",
    "username": "string"
  }
]
```
