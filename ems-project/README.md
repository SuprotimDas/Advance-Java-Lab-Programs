# Employee Management System (Servlet + JSP + JDBC + MySQL)

A complete Java web app: HTML front page → Servlets → JSP views → JDBC → MySQL.
Runs with **one Maven command** — no manual Tomcat install/configuration needed.

## Project layout
```
ems-project/
├── pom.xml                          <- dependencies + embedded Tomcat runner
├── schema.sql                       <- run this in MySQL first
├── src/main/resources/db.properties <- your DB URL / username / password
├── src/main/java/com/ems/
│   ├── model/Employee.java
│   ├── dao/EmployeeDAO.java
│   ├── util/DBConnection.java
│   └── servlet/
│       ├── AddEmployeeServlet.java
│       ├── ViewEmployeeServlet.java
│       ├── UpdateEmployeeServlet.java
│       ├── DeleteEmployeeServlet.java
│       └── TestDBServlet.java
└── src/main/webapp/
    ├── index.html
    ├── addEmployee.jsp
    ├── viewEmployees.jsp
    ├── updateEmployee.jsp
    ├── selectEmployee.jsp
    ├── dbStatus.jsp
    └── WEB-INF/web.xml
```

## 1. Prerequisites
- **JDK 11+** — check with `java -version`
- **Maven** — check with `mvn -version`
  (If missing on Windows: install from https://maven.apache.org/download.cgi, or `choco install maven`.)
- **MySQL Server** running locally, plus a client (MySQL Workbench or the `mysql` CLI)
- **VS Code** with the **"Extension Pack for Java"** (Microsoft) installed — this alone is
  enough; you do NOT need the separate Tomcat/Community Server extensions.

## 2. Set up the database
Open MySQL Workbench (or the `mysql` CLI) and run the script in `schema.sql`:
```sql
-- creates ems_db, the employees table, and inserts 5 sample rows
source schema.sql;
```
This creates the database `ems_db`, the `employees` table, and inserts 5 sample records.

## 3. Configure your credentials
Edit `src/main/resources/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/ems_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.user=root
db.password=your_mysql_password
```
Replace `your_mysql_password` with your actual MySQL root (or app user) password.

## 4. Run it
Open the `ems-project` folder in VS Code, open a terminal (`` Ctrl+` ``), then:
```bash
mvn clean package
mvn tomcat7:run
```
Wait for the log line `INFO: Starting ProtocolHandler ["http-bio-8080"]`, then open:
```
http://localhost:8080/
```
You'll see the same dark employee-management page, now with working buttons:
- **Add Employee** → form → inserts a row
- **View Employee** → table of all employees, with Edit/Delete links per row
- **Update Employee** → pick a record → edit form → saves changes
- **Delete Employee** → pick a record → deletes it (with a confirm prompt)
- **Test DB Connection** → confirms the JDBC connection to MySQL is working

Stop the server anytime with `Ctrl+C` in the terminal.

## Troubleshooting
- **"Communications link failure" / connection refused** — MySQL isn't running, or the
  port/host in `db.url` is wrong.
- **"Access denied for user"** — wrong username/password in `db.properties`.
- **"Unknown database 'ems_db'"** — you haven't run `schema.sql` yet.
- **Port 8080 already in use** — change `<port>8080</port>` in `pom.xml`'s
  `tomcat7-maven-plugin` config to e.g. `8081`, then use that port in the URL.
- **mvn command not found** — install Maven and make sure it's on your PATH, or use the
  Java Extension Pack's bundled Maven support (VS Code will usually offer to run Maven
  goals via the "Maven" side panel too — look for `tomcat7:run` under Plugins).
