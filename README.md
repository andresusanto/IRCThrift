# IRC Thrift
![IRC Thrift](/../screenshoot/screenshoots/server.JPG?raw=true "IRC Thrift")

A simple IRC like chatting application that is made by Java and Apache Thrift

## Building Information
This project is made by using **Netbeans IDE 8.0**. In order to compile this project, you need to open this project with Netbenas, and build the project with it.

To build server version, open the project and change the project's main class in `File -> Project Properties -> Run -> Main Class` to `ircthrift.ChatServer`. Otherwise, change it to `ircthrift.IRCThrift` to build the client version.

## Pre-built binaries
Pre built binaries can be found in `dist` folder in the project root folder. There are two jar files:

1. `IRCThrift-Server.jar` : Server binary file
2. `IRCThrift-Client.jar` : Client binary file

## Running
To run the server, issue this command:
```
java -jar IRCThrift-Server.jar
```
To run the client, issue this command:
```
java -jar IRCThrift-Client.jar
```

## Test and Execution Result
I have tried several tests to this project as following:

### Server Execution
![IRC Thrift](/../screenshoot/screenshoots/server.JPG?raw=true "IRC Thrift")

### Two client test
![IRC Thrift](/../screenshoot/screenshoots/client-test.JPG?raw=true "IRC Thrift")

1. First client logged in into server and select a nick. The second client did so.
2. First client joined #ITB and #Crowd, but second client only joined #ITB.
3. First client send chat to All Channel, #ITB, and #Crowd.
4. Second client read its inbox, and only got two messages from first client as it didn't join #Crowd.

### Leave test
![IRC Thrift](/../screenshoot/screenshoots/testing-leave.JPG?raw=true "IRC Thrift")

1. First client left #ITB
2. Second client sent messages to ITB
3. First client didn't get messages from second client as it had left #ITB.

### Three client test
![IRC Thrift](/../screenshoot/screenshoots/testing-3-client.JPG?raw=true "IRC Thrift")

1. Third client joined #Crowd.
2. Third client sent first message to #Crowd.
3. First client received the message as it had joined #Crowd before
4. Second client didn't receive the first message that had been sent by the third client as it hadn't joined #Crowd
5. Second message from third client was sent after second client joined #Crowd
6. First and second client received the second message as they had joined #Crowd.
