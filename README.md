# Rewards Calculator

     ## Description
   
     Calculates the Rewards Points for the Transactions of a customer
     * A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
     dollar spent between $50 and $100 in each transaction.

      # Note
      I used H2 InMemory Database for this Application

      ## Installation

        1. Clone the repository: `git clone https://github.com/yourusername/project.git`
        2. Navigate to the project directory: `cd project`
        3. Install dependencies: `./mvnw clean package`
        4. Run the Application: `./mvnw spring-boot:run`

----
      # POST a Transaction

     curl --location 'http://localhost:8050/transactions' \
     --header 'Content-Type: application/json' \
     --data '{
     "id":"1",
     "customerId":"123",
     "transactionDate":"2023-02-04",
     "transactionAmount":180
     }'
----

     # GET All Transactions

    curl --location 'http://localhost:8050/transactions'
----

    # GET reward point for customer id 123 in 4 th month of 2022

    curl --location 'http://localhost:8050/rewards/123/2022/4'
----

    # GET all reward points for customer id 123

    curl --location 'http://localhost:8050/rewards/123
----
