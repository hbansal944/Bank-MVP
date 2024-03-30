 
Run the applications in the order given below to work everything fine:-
 
1.ServiceRegistry (Eureka Server)
 
2.Config Server(Centralised configuration)
 
3.Api Gateway
 
4.Customer Service
 
5.Account Service
 
Go to http://localhost:8761/ and checks if Account Management Service, Api Gateway and Customer Management 
Service are registered on it or not.
 

 
Services API's (can check by hitting postman)
 
**CustomerMangementService**
 
1. Add Customer
 
Post Method
```URL : http://127.0.0.1:8084/customers
 
```
2. Get All Customers
 
Get Method 
```URL : http://127.0.0.1:8084/customers`
 
3. Get Single Customer
 
Get Method
```URL : http://127.0.0.1:8084/customers/{customerId}
 
4. Update Customer Details
 
Put Method 
```URL : http://127.0.0.1:8084/customers

```
5. Delete Customer : Associated account will also get deleted in Account Service
 
Delete Method 
```URL : http://127.0.0.1:8084/customers/{customerId}
 
  Account Service
 
1. Add Account
Post Method
```URL : http://127.0.0.1:8084/accounts

 
2. Add Money To Account : Check is done if customer is present
 
Post Method 
```URL : http://127.0.0.1:8084/accounts/add-money/{accountNumber}?amount=150 
 
3. Withdraw Money From Account : Check is done if customer is present
Post Method
```URL : http://127.0.0.1:8084/accounts/withdraw-money/{accountNumber}?amount=90
 
 
4. Get Account Details : It gives account details as well as customer details.
Get Method
```URL : http://127.0.0.1:8084/accounts/{accountNumber}
 
 
5. Delete Account 
Delete Method
```URL : http://127.0.0.1:8084/accounts/{accountNumber}