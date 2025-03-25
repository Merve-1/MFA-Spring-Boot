### 🛡️ Multi-Factor Authentication System  

#### 📌 Overview  
This is a **Spring Boot** application implementing a **Multi-Factor Authentication** system with **MySQL**, **Spring Security**, **JTE for templating**, and **JUnit for testing**. The application provides user registration, login, and authentication features, with OAuth2 integration for Google and GitHub.

---

### 📂 Project Structure  
```
multi-factor-auth/
│── src/main/java/mfa/website/multi_factor_auth/
│   ├── config/                     # Security configuration  
│   │   ├── SecurityConfig.java      # Defines security settings  
│   ├── controller/                  # API Controllers  
│   │   ├── DashboardController.java  
│   │   ├── LoginController.java  
│   │   ├── RegisterController.java  
│   ├── csrf/                        # CSRF Protection  
│   │   ├── CsrfHiddenInput.java  
│   │   ├── CsrfTokenAdvice.java  
│   ├── model/                       # Entity Models  
│   │   ├── Users.java  
│   ├── repository/                  # Database Repositories  
│   │   ├── UserRepository.java  
│   ├── MultiFactorAuthApplication.java  
│── src/main/jte/pages/              # JTE Templates  
│   ├── login.jte  
│   ├── register.jte  
│   ├── dashboard.jte  
│   ├── home.jte  
│── src/test/java/                   # Unit Tests  
│   ├── UserRepositoryTest.java  
│── application.properties           # Configuration  
```

---

### 🔧 Technologies Used  
- **Spring Boot** (MVC, Security)  
- **Spring Data JPA** (MySQL Integration)  
- **JTE** (Templating Engine)  
- **JUnit** (Unit Testing)  
- **OAuth2** (Google & GitHub Login)  
- **Postman** (API Testing)  

---

### 🛠️ Setup Instructions  

#### 1️⃣ Clone the Repository  
```bash
git clone https://github.com/YOUR_USERNAME/multi-factor-auth.git
cd multi-factor-auth
```

#### 2️⃣ Configure MySQL Database  & OAuth 2.0
Update `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/authenticated_login
spring.datasource.username=root
spring.datasource.password=your_password
```
```properties
# Google OAuth2 Configuration
spring.security.oauth2.client.registration.google.client-id=YOUR_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_SECRET
spring.security.oauth2.client.registration.google.scope=email,profile

# GitHub OAuth2 Configuration
spring.security.oauth2.client.registration.github.client-id= YOUR_ID
spring.security.oauth2.client.registration.github.client-secret= YOUR_SECRET
spring.security.oauth2.client.registration.github.scope=user:email,read:user
```

🖼 **Screenshot:**  
![image](https://github.com/user-attachments/assets/b348b310-504c-402a-8ec5-a1ee33454cb7)

---
#### 3️⃣ Run the Application  
```bash
mvn spring-boot:run
```
The server starts at **`http://localhost:8081`** 🚀  

---

### 🔑 API Endpoints  

| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `GET`  | `/api/auth/user/{username}` | Get user by username |
| `GET`  | `/api/auth/user/id/{id}` | Get user by ID |

#### 📝 Register User (Example Request)  
**POST** `http://localhost:8081/api/auth/register`  
🖼 **Screenshot:**  
![image](https://github.com/user-attachments/assets/bddb31de-dd50-4a68-9427-d6fa87114774)

---

### 🧪 Unit Testing  
The project includes **JUnit** tests to validate authentication logic.  
Example test from `UserRepositoryTest.java`:  
```java
@Test
public void testCreateUser() {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    Users user = new Users();
    user.setUsername("test1zx");
    user.setPassword(passwordEncoder.encode("test1233"));
    user.setRole("USER");

    Users savedUser = repo.save(user);
    Users existUser = entityManager.find(Users.class, savedUser.getId());

    assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
    assertThat(passwordEncoder.matches("test1233", existUser.getPassword())).isTrue();
}
```
🖼 **Screenshot:**  
![image](https://github.com/user-attachments/assets/e65518fe-b566-49fc-902e-df76a44304c1)

---

### 🔐 Security Features  
✔️ **CSRF Protection**  
✔️ **OAuth2 Authentication (Google & GitHub)**  
✔️ **BCrypt Password Hashing**  
✔️ **Role-Based Access Control**  

---

🖼 **Website Screenshot:**  
![image](https://github.com/user-attachments/assets/0c4df158-940c-42f6-9943-9ac4762ff381)
![image](https://github.com/user-attachments/assets/fabc540a-d3c2-47d9-af7c-94b428939936)
![image](https://github.com/user-attachments/assets/ab83dace-6cd8-4ffd-9e31-b6df2ba9c086)

