@echo off
javac Server.java
javac Client.java
start /B java Server
start /WAIT java Client
del *.class
