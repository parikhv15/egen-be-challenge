echo "Checking for Maven executable to build the program. "
hash mvn 2>/dev/null
if [ $? -eq 1 ]; then
    echo >&2 "Maven was not found on your system. Please install maven and run this script again."
fi
echo "Maven Found!" 
echo "Checking for 'mongod' process"
pgrep mongod 2> /dev/null 
if [ $? -gt 0 ]; then 
    echo >&2 "Mongodb server not found. If you believe mongodb is running. Please execute the Spring boot program manually". 
    exit;
fi 
echo "MongoDB service running. Now compiling Spring Boot Application Services"
mvn clean package
if [ $? -gt 0 ]; then 
	echo "There was an error while starting this program. Please ensure that all the requirements have been met and try again."; 
	exit;
fi
#start service.
java -jar -Dbase.value=$1 target/egen-be-challenge-0.0.1-SNAPSHOT.jar
if [ $? -gt 0 ]; then 
        echo "There was an error while starting this program. Please ensure that all the requirements have been met and try again.";
        exit; 
fi
echo "Web service started successfully."; 
