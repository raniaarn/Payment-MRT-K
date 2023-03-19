## Microservice 4 of MyMRT: Payment and Top Up

### Description
- Subtract user's balance based on ticket's price
- Top up some amount of money to user's balance
  - Rp50.000
  - Rp100.000
  - Rp150.000
  - Rp200.000
  - Rp250.000
  - Rp300.000
  - Rp350.000
  - Rp400.000
  - Rp450.000
  - Rp500.000
- Allowing user to pay ticket with coupon
  - coupon can only be used one time
  - each coupon have minimum price (can only be used if reach certain minimum prices)
- Allowing user to pay ticket with mrtPoin
  - User will get Poin for every payment
  - poin gained = 10% x total price (tentative)
- Design pattern in use: Adapter Pattern

### API Specification
#### /payment
![Diagram UML payment](Payment.png.png)
``` 
Given input:
{
    price: 12000,
    user_id: 1
}

 ```
Get balance from user_id first, then check whether user’s balance is enough (optional to use coupon to pay):
<br>- If not enough, returns status 403 (caught by ticket/buy-ticket from Service 3).
<br>- If enough, subtract user balance (if not using coupon), (use account/edit-account from Service 1).
<br> Note: Needs Service 1 (Authentication) and 3 (Ticket Purchase and History)

#### /TopUp
![Diagram UML topup](TopUp.png.png)
``` 
Given input:
{
    deposit: 12000,
    user_id: 1
}
 ```
Calls account/edit-account, edit balance from Service 1 
<br> Note: Needs Service 1 (Authentication)

