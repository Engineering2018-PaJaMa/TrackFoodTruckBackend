# Before you push your changes
mvn clean install
If both goals aren't reached do not push it to the repo.

# Run package
mvn clean install
java -jar target/track-food-truck-rest-WS-[version].jar
Change [version] according to build, or simply do not type whole path, just start with "target/tra" press tab and terminal will fill out class path