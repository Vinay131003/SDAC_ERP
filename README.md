# ERP SDAC Project  

## 📌 Overview  
This project is an **ERP system** developed in **Java (Servlets + JSP)** using **MVC architecture**. It runs on **Eclipse IDE with Apache Tomcat** and uses **MySQL** as the database.  

---

## 🚀 Prerequisites  
Before running the project, ensure you have the following installed:  

- **Java JDK 8+**  
- **Eclipse IDE for Enterprise Java Developers**  
- **Apache Tomcat 9/10**  
- **MySQL Server**  
- **MySQL Workbench** (optional, for database management)  
- **MySQL Connector/J (mysql-connector-j-8.x.x.jar)**  

---

## ⚙️ Setup Instructions  

### 1. Import Project into Eclipse  
1. Open **Eclipse**.  
2. Go to `File → Import → Existing Projects into Workspace`.  
3. Select the project folder (`ERP_SDAC`).  
4. Finish.  

---

### 2. Configure Tomcat Server  
1. Download and extract **Apache Tomcat** (if not already installed).  
2. In Eclipse, go to:  
   - `Window → Preferences → Server → Runtime Environments → Add`  
   - Select **Apache Tomcat vX.X**  
   - Browse to the Tomcat installation directory.  
3. Set this Tomcat as the default runtime for the project.  

---

### 3. Configure Database  
1. Start MySQL server.  
2. Create a new database (example):  

   ```sql
   CREATE DATABASE erp_sdac;
   USE erp_sdac;
````

---

### 4. Add Required Libraries

* **Servlet API**

  * Provided by Tomcat, but make sure Eclipse recognizes it:

    * Right-click project → `Properties → Build Path → Add Library → Server Runtime → Apache Tomcat`.

* **MySQL Connector**

  * Download `mysql-connector-j-8.x.x.jar` from MySQL official site.
  * Copy it to:

    ```
    WebContent/WEB-INF/lib/
    ```
  * Refresh project in Eclipse.

---

### 5. Project Structure

```
ERP_SDAC/
 ├── src/
 │    ├── com.erp.controller   (Servlets)
 │    ├── com.erp.dao          (Database Access Objects)
 │    ├── com.erp.model        (POJO classes)
 │    └── com.erp.util         (Utilities: DB connection, helpers)
 │
 ├── WebContent/
 │    ├── index.jsp
 │    ├── dashboard.jsp
 │    └── WEB-INF/
 │         ├── web.xml         (Deployment Descriptor)
 │         └── lib/            (MySQL Connector)
 │
 └── database/
      └── erp_sdac.sql
```

---

### 6. Run the Project

1. Right-click project → **Run As → Run on Server**.
2. Choose **Tomcat server** and start it.
3. Open your browser at:

   ```
   http://localhost:8080/ERP_SDAC
   ```

---

## 🔑 Default Login (if applicable)

* **Username**: `admin`
* **Password**: `admin123`

*(Update as per your actual credentials)*

---

## 🛠 Troubleshooting

* **Error: `ClassNotFoundException: com.mysql.cj.jdbc.Driver`**
  → Ensure `mysql-connector-j.jar` is in `WEB-INF/lib`.

* **Error: `HTTP Status 404 – Not Found`**
  → Check if the servlet is correctly mapped in `web.xml`.

* **Port Conflict (Tomcat not starting)**
  → Change Tomcat port from `8080` to another (e.g., `8081`) in `server.xml`.

---

## 🔗 Project Links

* **GitHub Repository**: \[Your Repo Link Here]
* **Hosted Page**: \[Your Hosted Page Link Here]

---

## 👨‍💻 Author

**Your Name**
ERP SDAC Project – Developed as part of SDAC course
