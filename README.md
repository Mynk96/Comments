A user Guide for setting up the Application.
    
Necessary tools and language required:-
    1)Java (jdk 1.7 or greater)
    2)Eclipse for java ee developers.
    (http://www.eclipse.org/downloads/packages/release/Luna/SR2)
    3)Mysql.
    (https://dev.mysql.com/downloads/installer/)
    4)Apache Tomacat (version g or greater).
    (http://tomcat.apache.org/)
    5)Web browser.
    
    
Steps to set up a environment:-
    1)Install Jdk 1.7 or greater.
    2)Install Eclipse.
    3)Install MySQL
    4)Install Apache Tomcat
    5)Clone repo or download zip from https://github.com/Mynk96/Comments
    (if downloaded as zip,extract and then import)
    6)Open Eclipse and import the cloned or extracted project.
    (File->Import->General->Existing Projects into Workspace)
    7)Dowload additional Libraries and add in the project.
    (http://www.java2s.com/Code/Jar/j/Downloadjsonrpc10jar.htm)
    (https://dev.mysql.com/downloads/connector/j/)
    9)Right click on project and select Build path.
    10)Select Libraries TAB and choose Add External JARS AND ADD BOTH THE DOWLOADED JARS.
    
    
Setting up Mysql and Connecting with eclipse.
    1)Download and install mysql.
    2)Set up username and password and remember it.
    3)Open Mysql Workbench and login wiht your username and password.
    4)And execute Command
    "CREATE DATABASE comments"
    5)Import commentsData.sql from the cloned or extracted folder.
    (Data import/restore-> import from Self contained file->browse to the file)


Running the application:-
    1)Open Eclipse.
    2)Right click on the project and select Run as->Run on server.
    3)Select Apache version you have downloaded and then browse to the downloaded apache directory and finish.
    4)Now a servers project would apper on the right side,expand and opn context.xml.
    5)Paste this in between <Context></Context> tags.
    <Resource name="jdbc/comments" auth="Container" type="javax.sql.DataSource"
               maxActive="100" maxIdle="30" maxWait="10000"
               username="root" password="root" driverClassName="com.mysql.jdbc.Driver"
               url="jdbc:mysql://localhost:3306/comments"/>
    6)Replace username and password with your Mysql username and password and save.           
    7)Now Right click on the Tomcat server in the servers tab and restart.
    8)go the brwoser and type
    http://localhost:8080/Comments 
    
If their are any issues setting up the application please contact mayank.harsani@gmail.com.    
    
    

	