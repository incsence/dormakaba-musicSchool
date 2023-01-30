# dormakaba-musicSchool
This is my JAVA sandbox project - a time table tool for a music school

## JAVA
This used jdk-17.0.5.

## Import Database
Download musicSchool.bacpac
To import musicSchool.bacpac into MS SQL Server, please try this ( due to https://www.youtube.com/watch?v=HrE2GmxGXoM ): 
In SQL Server right click on "Databases".
Click on "Import Data-tier Application"
Choose the file on the computer and follow instructions. Done.

## Download and Install JDBC Driver
https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16

## Edit batch file
Download the MusicSchool.jar and MusicSchool.bat.

Edit MusicSchool.bat:
1. The location of your java version (which could be the same of course )
2. Location of JDBC Driver (which could be the same of course) 
3. Location of MusicSchool.jar
