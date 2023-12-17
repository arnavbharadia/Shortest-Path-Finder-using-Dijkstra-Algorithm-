run:rundependencies  #TODO finish once main file is written
	java TripMapper

runTests: runAlgorithmEngineerTests runDataWranglerTests runFrontendDeveloperTests runBackendDeveloperTests

runAlgorithmEngineerTests:
	javac ICityGraph.java
	javac ICityValidator.java
	javac CityValidator.java
	javac CityGraph.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar --scan-classpath -cp . -n AlgorithmEngineerTests

runDataWranglerTests:
	javac IdataLoader.java
	javac IRoad.java
	javac DWIRoad.java
	javac DWIdataLoader.java
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar --class-path=. --include-classname=DataWranglerTests --select-class=DataWranglerTests

runFrontendDeveloperTests: frontendTestDependencies # runs the JUnit tests for the frontendDeveloper                                                                        
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar --scan-classpath -cp . -n FrontendDeveloperTests

frontendTestDependencies:
	javac TextUITester.java
	javac TripFrontend.java
	javac FrontendDeveloperBackendPlaceholder.java
	javac FrontendDeveloperValidatorPlaceholder.java

clean: # removes all class files if there are any
	rm *.class

runBackendDeveloperTests: CityBackend.class
	javac -cp .:junit5.jar BackendDeveloperTests.java
	java -jar junit5.jar --scan-classpath -cp . -n BackendDeveloperTests

CityBackend.class:
	javac CityGraphBackendDeveloperPlaceholder.java
	javac CityValidatorBackendPlaceholder.java
	javac CityBackend.java


rundependencies: TripMapper.java CityValidator.java CityGraph.java TripFrontend.java CityBackend.java DWIRoad.java DWIdataLoader.java 
	javac DWIdataLoader.java 
	javac DWIRoad.java
	javac CityValidator.java
	javac CityGraph.java
	javac CityBackend.java
	javac TripFrontend.java
	javac TripMapper.java
