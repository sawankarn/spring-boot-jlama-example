# Payment Support Chat API Examples

## API Endpoints

### 1. Basic Chat - `/chat`
**Method:** POST  
**Content-Type:** application/json

```json
{
  "prompt": "What is the capital of France?"
}
```

**Response:** AI-generated response using Jlama model directly

### 2. LangChain Chat - `/langchain-chat`
**Method:** POST  
**Content-Type:** application/json

```json
{
  "prompt": "Explain Java polymorphism with examples"
}
```

**Response:** Java expert AI response using LangChain4j

### 3. Payment Support Agent - `/langchain-ai-function`
**Method:** POST  
**Content-Type:** application/json

## Real-World Payment Support Examples

### Example 1: Transaction Status Inquiry
**Request:**
```json
{
  "prompt": "What's the status of my transaction TXN1001?"
}
```

**Expected Response:**
```
The payment status for transaction TXN1001 is 'Paid'. The amount was $125.50 and it was processed on 2024-01-15.
```

### Example 2: Customer Transaction History
**Request:**
```json
{
  "prompt": "Show me all transactions for customer CUST001"
}
```

**Expected Response:**
```
Here are all transactions for customer CUST001 (John Smith):
- Transaction TXN1001: $125.50 on 2024-01-15 (Paid)
- Transaction TXN1004: $54.30 on 2024-01-18 (Paid)
- Transaction TXN1010: $67.89 on 2024-01-24 (Unpaid)
```

### Example 3: Failed Payment Investigation
**Request:**
```json
{
  "prompt": "Why did my transaction TXN1008 fail?"
}
```

**Expected Response:**
```
Transaction TXN1008 failed on 2024-01-22. The payment amount was $156.78 and was attempted using PayPal. You may want to try a different payment method or contact your bank.
```

### Example 4: Merchant Information
**Request:**
```json
{
  "prompt": "Where did I make purchase TXN1005?"
}
```

**Expected Response:**
```
You made purchase TXN1005 at Starbucks (Food & Beverage category) for $210.20 on 2024-01-19. The payment status is currently 'Pending'.
```

### Example 5: Payment Method Details
**Request:**
```json
{
  "prompt": "How did I pay for transaction TXN1012?"
}
```

**Expected Response:**
```
You used Digital Wallet to pay $289.00 for transaction TXN1012 on 2024-01-26. The payment status is 'Pending'.
```

### Example 6: Customer Information Lookup
**Request:**
```json
{
  "prompt": "Who is the customer for transaction TXN1003?"
}
```

**Expected Response:**
```
Transaction TXN1003 belongs to customer Michael Brown (m.brown@email.com). The payment was $120.00 on 2024-01-17 and is marked as 'Paid'.
```

## Sample Data Overview

The system contains 15 sample transactions with:

- **8 Unique Customers:** John Smith, Emily Johnson, Michael Brown, Sarah Davis, Robert Wilson, Lisa Anderson, James Taylor, Maria Garcia
- **8 Merchants:** Amazon, Walmart, Target, Best Buy, Starbucks, Netflix, Uber, Spotify
- **Payment Methods:** Credit Card, Debit Card, PayPal, Bank Transfer, Digital Wallet
- **Status Types:** Paid, Unpaid, Pending, Failed
- **Date Range:** January 15-29, 2024
- **Amount Range:** $54.30 - $456.78

## Testing with curl

```bash
# Test payment support agent
curl -X POST http://localhost:8080/langchain-ai-function \
  -H "Content-Type: application/json" \
  -d '{"prompt": "What is the status of transaction TXN1001?"}'

# Test basic chat
curl -X POST http://localhost:8080/langchain-chat \
  -H "Content-Type: application/json" \
  -d '{"prompt": "Explain Spring Boot in simple terms"}'
```
