1. Import weather app as existing spring gradle project.
2. Refresh the Gradle dependencies (Right click on project->Gradle->Refresh Gradle Dependencies).
3. Right click on project -> Run As -> Spring Boot App.
4. Use Postman or SOAP UI (or) Browser to run the below tests. 


# Zip Code
http://localhost:8080/weatherinfo?zip=75001
http://localhost:8080/weatherinfo?zip=85001
# City
http://localhost:8080/weatherinfo?city=New York
http://localhost:8080/weatherinfo?city=California

# Application Heart Beat
http://localhost:8080/heartbeat