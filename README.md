# Insurance System Demo - Java Spring Boot

This is a demo Spring Boot project implementing a simple insurance management system. The application manages insured individuals and their insurance contracts using an SQL database (H2 for testing).

## 🛠 Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **H2 Embedded Database**

## 📬 Sample Request

You can test `POST /insured` with this sample body:

```json
{
  "firstName": "Ján",
  "lastName": "Novák",
  "personalNumber": "9001011234",
  "email": "jan.novak@example.com",
  "permanentAddress": {
    "postalCode": "01001",
    "city": "Žilina",
    "street": "Hlavná",
    "houseNumber": "12A"
  },
  "contracts": [
    {
      "type": "TRAVEL",
      "contractNumber": "TRAV-001",
      "startDate": "2024-06-01",
      "insuranceFrom": "2024-06-10",
      "insuranceTo": "2024-06-20",
      "liabilityInsurance": true,
      "accidentInsurance": false
    },
    {
      "type": "TRAVEL",
      "contractNumber": "TRAV-002",
      "startDate": "2024-06-01",
      "insuranceFrom": "2024-06-10",
      "insuranceTo": "2024-06-20",
      "liabilityInsurance": true,
      "accidentInsurance": false
    },
    {
      "type": "PROPERTY",
      "contractNumber": "PROP-001",
      "startDate": "2024-01-01",
      "propertyType": "BYT",
      "propertyValue": 150000,
      "propertyAddress": {
        "postalCode": "01001",
        "city": "Žilina",
        "street": "Panelová",
        "houseNumber": "20"
      }
    }
  ]
}

