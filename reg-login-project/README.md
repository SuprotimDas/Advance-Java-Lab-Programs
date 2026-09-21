# Registration & Login System (Servlet + JSP + JDBC + MySQL)

Matches the whiteboard: a Registration page that stores each signup in MySQL,
and a Login page that checks the User ID / Password against the database.
A "View Registered Users" page (the same idea as "View Employee" in the
earlier project) lists everyone who has signed up.

## A note on "Type 1 JDBC driver"
The whiteboard/course material refers to a Type 1 driver (the JDBC-ODBC
Bridge, `sun.jdbc.odbc.JdbcOdbcDriver`). That driver was removed from the JDK
starting with **Java 8** and cannot run on any current machine or JDK version
— this isn't specific to this project, it's true of any Java program today.

This project uses **MySQL Connector/J**, which is a **Type 4 ("thin")
driver** — pure Java, talks directly to MySQL over the network, no ODBC
layer. This is the standard, working way to connect Java to MySQL today, and
what your evaluator will actually see running. If you need to explain the
four driver types in a viva:
- **Type 1** — JDBC-ODBC Bridge (obsolete, removed from JDK 8+)
- **Type 2** — Native-API driver (part Java, part native DB client library)
- **Type 3** — Network-protocol driver (talks to a middleware server)
- **Type 4** — Thin driver (pure Java, talks directly to the DB) ← used here

## Project layout
```
reg-login-project/
├── pom.xml
├── schema.sql                       <- run this in MySQL first
├── src/main/resources/db.properties <- your DB URL / username / password
├── src/main/java/com/regsys/
│   ├── model/User.java
│   ├── dao/UserDAO.java
│   ├── util/DBConnection.java
│   ├── util/PasswordUtil.java       <- hashes passwords (SHA-256) before storing
│   └── servlet/
│       ├── RegisterServlet.java
│       ├── LoginServlet.java
│       └── ViewUsersServlet.java
└── src/main/webapp/
    ├── index.html
    ├── register.jsp
    ├── login.jsp
    ├── welcome.jsp
    ├── viewUsers.jsp
    └── WEB-INF/web.xml
```

## 1. Prerequisites
Same as the employee-management project: JDK 11+, Maven, MySQL Server, and
VS Code with the "Extension Pack for Java" extension.

## 2. Set up the database
Run `schema.sql` in MySQL Workbench or the `mysql` CLI:
```sql
source schema.sql;
```
This creates `reg_db` and the `users` table. No sample rows are inserted —
the point of this project is that rows appear as you register through the
web page.

## 3. Configure your credentials
Edit `src/main/resources/db.properties` with your MySQL username/password.

## 4. Run it
```bash
mvn clean package
mvn tomcat7:run
```
Open **http://localhost:8081/** (this project uses port 8081, not 8080, so
it can run side-by-side with the employee-management project if needed).

- **Register** → fills in Name, Enroll ID, Batch, DOB, Official Email,
  Address, Mobile No, plus a User ID and Password → inserts a row into
  `users` (password is hashed, never stored in plain text)
- **Login** → checks the User ID/Password against the database; wrong
  credentials show an error, correct ones show a Welcome page
- **View Registered Users** → table of everyone who has registered (this is
  the "View Employee" page from before, repurposed for this project)

## Troubleshooting
Same checklist as the employee-management project: MySQL running, correct
credentials in `db.properties`, `schema.sql` already run, and port 8081 free
(change `<port>8081</port>` in `pom.xml` if it's taken).
