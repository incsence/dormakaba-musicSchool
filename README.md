# Dormakaba MusicSchool

This is my JAVA sandbox project - a time table tool for a music school

For running, please download the full directory.

Although running fine, this is considered to be work in progress for code security and code efficiancy reasons.


## Requirements

This uses JAVA jdk-17.0.5.

Microsoft SQL Server



## Import Database

To import musicSchool.bacpac into MS SQL Server, please try this ( due to https://www.youtube.com/watch?v=HrE2GmxGXoM ): 

In SQL Server right click on "Databases".

Click on "Import Data-tier Application"

Choose musicSchool.bacpac on your computer and follow instructions. Done.



### MusicSchool.sql

If you want to create the database without bacpac-File, you can do so with MusicSchool.sql. The DB would then be empty. The Connection in Main most likely wont work.



## JDBC Driver

JDBC library is part of the repository.

It was downloaded here: 
https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16



## Edit MusicSchool.bat:

Edit the Path to the location of your java.exe. 



## to start the program

double click on MusicSchool.bat
