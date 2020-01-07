# What's in

'/greeting' : hello world greeting   Related link: [rest api](https://spring.io/guides/gs/rest-service/)

'/message' : Using RestTemplate to get response from another api and return back information Related link: [rest template](https://spring.io/guides/gs/consuming-rest/)

configuration:

Use `java -jar build/libs/helloworld-0.0.1.jar --spring.profiles.active=test` to using test configuration, which deploy service on port 8080 instead of 8081

# Run
1. Build & Run:
```
./gradlew build && java -jar build/libs/helloworld-0.0.1.jar
```
2. Run
```
./gradlew bootRun
```