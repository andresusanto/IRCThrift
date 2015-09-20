namespace java ircthrift
typedef i32 int

service ChatService
{
	bool nick(1:string nickname),
	bool join(1:string channelname, 2:string nickname),
	bool leave(1:string channelname, 2:string nickname),
	bool sendall(1:string message, 2:string nickname),
	bool sendto(1:string message, 2:string channelname, 3:string nickname),
	list<string> getmessage(1:string nickname)
}