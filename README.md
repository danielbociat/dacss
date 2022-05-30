# dacss

The SQL Server is a Microsoft SQL Server

On the local machine the following command should be run 

CREATE TABLE dacss.students (id int PRIMARY KEY, name text NOT NULL)

Also, the connection port used was the default one, 1433, but it may differ from user to user

If that is the case, 9th line in ClientSide.java should be changed accordingly.

String SQLConn = "jdbc:jtds:sqlserver://127.0.0.1:[port]/DACSS";

Also the jtds jdbc driver should be added to the project.

To do that: right-click on Project -> Open Module Settings -> Libraries -> + -> add the .jar file from the driver
