
# Postman Curls

# Create Transaction
----
     curl --location 'http://localhost:8050/transactions' \
     --header 'Content-Type: application/json' \
     --data '{
     "id":"1",
     "customerId":"123",
     "transactionDate":"2023-02-03",
     "transactionAmount":180
     }'
----

# Get All Transaction
----
    curl --location 'http://localhost:8050/transactions'
----

# get reward point for customer id 123 in 4 th month of 2022

----
    curl --location 'http://localhost:8050/rewards/123/2022/4'
----

# get all reward point for customer id 
----
    curl --location 'http://localhost:8050/rewards/123
----
