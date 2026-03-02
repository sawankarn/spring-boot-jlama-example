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
