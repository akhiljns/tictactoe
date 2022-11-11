# tictactoe
tictactoe game with concurrency handled

IDEA BEHIND GAME BOARD

so there are 9 boxes in tictactoe board

they start from 0 to 8

0 is first box in first row
1 is middle box in first row
2 is third box in first row

3 is first box in second row
4 is middle box in second row
5 is third box in second row


6 is first box in third row
7 is middle box in third row
8 is third box in third row


requirements - 
maven
java

## steps to build the project and run it:

- ./mvnw clean install
- ./mvnw spring-boot:run

OR

I have also kept jar of the project which can be run without having to build using maven build by (from project home directory)
- java -jar target/tictactoe-0.0.1-SNAPSHOT.jar

## testing the project APIs using postman or curl
```
1) INITIALIZE GAME
HTTP method - POST
url - http://localhost:8080/tictactoe/initialize?playerx=<enter player 1>&playero=<enter player 2>
sample - http://localhost:8080/tictactoe/initialize?playerx=akhil&playero=computer

curl -X POST 'http://localhost:8080/tictactoe/initialize?playerx=akhil&playero=comp'

2) PLAY TURN

HTTP method - POST 
url - http://localhost:8080/tictactoe/turn
REQUEST BODY sample data - 
{
  "playerId":"X",
  "position":"0"
}

sample curl command for the same - 

curl -X POST -H 'Accept: application/json' -H 'Content-Type: application/json' -i http://localhost:8080/tictactoe/turn --data '{
"playerId":"X",
"position":"6"
}'


3) RESET GAME

HTTP method - POST 
url - http://localhost:8080/tictactoe/reset

sample curl command for the same - 

curl -X POST http://localhost:8080/tictactoe/reset

4) VIEW CURRENT BOARD STATE

HTTP method - GET 
url - http://localhost:8080/tictactoe/state

sample curl command for the same - 

curl -X POST http://localhost:8080/tictactoe/state

5) VIEW PLAYERS IN GAME

HTTP method - GET 
url - http://localhost:8080/tictactoe/players

sample curl command for the same - 

curl -X POST http://localhost:8080/tictactoe/players

```
## steps to view the database using h2 console on web browser

url to hit on browser http://localhost:8080/h2-console/
then
click on connect button


NOTE regarding data-h2.sql
inserting into transaction values has been commented out as they follow auto generated value


