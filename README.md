# QuizApp-Microservices

This **Spring Boot Microservices** project allows users to create quizzes, retrieve quizzes, and submit quizzes.  
It is composed of several microservices, including **Quiz-Service, Question-Service, Eureka Server, API Gateway, and Config Server**.


---

## 🏗 Microservices Architecture

### 1️⃣ **Quiz-Service**
Handles quiz creation, retrieval, and submission.

#### **Endpoints:**
- `POST /quiz/create` - Create a new quiz by specifying the category and number of questions.
- `GET /quiz/{quizId}` - Retrieve a quiz by its ID.
- `POST /quiz/submit/{quizId}` - Submit a quiz.

---

### 2️⃣ **Question-Service**
Manages question creation, retrieval, updates, and deletion.

#### **Endpoints:**
- `POST /question` - Create a new question.
- `GET /question/{questionId}` - Retrieve a question by its ID.
- `GET /question/all` - Retrieve all questions.
- `GET /question/category/{category}` - Retrieve questions by category.
- `GET /question/generateQuiz/{category}/{numOfQuestions}` - Get question IDs by category and number.
- `POST /question/getQuestions` - Retrieve questions based on question IDs.
- `POST /question/scores` - Get the count of correct answers based on responses.
- `PUT /question/{questionId}` - Update a question.
- `DELETE /question/{questionId}` - Delete a question.

---

### 3️⃣ **Eureka Server**
Acts as a service registry for service discovery.

#### **Endpoint:**
🔗 `http://localhost:8761`

---

### 4️⃣ **API Gateway**
Serves as an entry point for external clients, routing requests to the appropriate microservices.

#### **Endpoint:**
🔗 `http://localhost:8095`

---

### 5️⃣ **Config Server**
Manages centralized configuration for all microservices.

#### **Endpoint:**
🔗 `http://localhost:8765`

---

## 🚀 Getting Started

### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/avisharma7/QuizApp-Microservices.git


