##ERP SDAC Project

📌 Overview
This project is an ERP system developed in Java (Servlets + JSP) using MVC architecture. It runs on Eclipse IDE with Apache Tomcat and uses MySQL as the database.

🚀 Prerequisites

Before running the project, ensure you have the following installed:
Java JDK 8+
Eclipse IDE for Enterprise Java Developers
Apache Tomcat 9/10
MySQL Server
MySQL Workbench (optional, for database management)
MySQL Connector/J (mysql-connector-j-8.x.x.jar)

⚙️ Setup Instructions

1. Import Project into Eclipse
Open Eclipse.
Go to File → Import → Existing Projects into Workspace.
Select the project folder (ERP_SDAC).
Finish.

2. Configure Tomcat Server
Download and extract Apache Tomcat (if not already installed).
In Eclipse, go to:
Window → Preferences → Server → Runtime Environments → Add
Select Apache Tomcat vX.X
Browse to the Tomcat installation directory.
Set this Tomcat as the default runtime for the project.

3. Configure Database
Start MySQL server.
Create a new database (example):
CREATE DATABASE erp_sdac;
USE erp_sdac;
Import the SQL schema from database/erp_sdac.sql (if provided).

4. Add Required Libraries
Servlet API
Provided by Tomcat, but make sure Eclipse recognizes it:
Right-click project → Properties → Build Path → Add Library → Server Runtime → Apache Tomcat.
MySQL Connector
Download mysql-connector-j-8.x.x.jar from MySQL official site.
Copy it to:
WebContent/WEB-INF/lib/
Refresh project in Eclipse.

5. Project Structure

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
      
6. Run the Project
Right-click project → Run As → Run on Server.
Choose Tomcat server and start it.
Edit
http://localhost:8080/ERP_SDAC
