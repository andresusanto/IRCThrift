/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ircthrift;

import java.util.List;
import java.util.Scanner;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 *
 * @author Andre
 */
public class IRCThrift {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TTransportException, TException {
        // TODO code application logic hereircthrift
        TTransport transport;
        
        transport = new TSocket("localhost", 9090);
        transport.open();
        TProtocol protocol = new  TBinaryProtocol(new TFramedTransport(transport));
        ChatService.Client client = new ChatService.Client(protocol);
        
        String nick = "", command = "";
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Welcome to IRCThrift Chat Client. (c) 2015 by Andre Susanto 13512028");
        System.out.println("Please choose nick by using /nick <your nick>");
        
        while( !(command = scn.next()).equals("/exit") ) {
            if (nick.equals("")){
                if (command.equals("/nick")){
                    String userPickNick = scn.next();
                    if (client.nick(userPickNick)){
                        nick = userPickNick;
                        System.out.println("Nick changed to " + nick);
                    }else{
                        System.out.println("Nick " + nick + " is used by someone else ... Please choose another");
                    }
                }else{
                    System.out.println("Please select you nick by using /nick <nickname>");
                }
            }else{
                if (command.equals("/join")){
                    String channelName = scn.next();
                    if (client.join(channelName, nick)){
                        System.out.println("You are now a member of #" + channelName);
                    }else{
                        System.out.println("You are already a member of #" + channelName);
                    }
                    
                }else if (command.equals("/leave")){
                    String channelName = scn.next();
                    if (client.leave(channelName, nick)){
                        System.out.println("You are out of #" + channelName);
                    }else{
                        System.out.println("You are not a member of #" + channelName);
                    }
                }else if (command.equals("/read")){
                    List<String> messages = client.getmessage(nick);
                    if (messages.size() == 0){
                        System.out.println("No new messages.");
                    }else{
                        System.out.println("=================NEW MESSAGES=================");
                        for (String message : messages){
                            System.out.println(message);
                        }
                        System.out.println("==============================================");
                    }
                }else{
                    StringBuffer messageBuffer = new StringBuffer();
                    if (command.substring(0, 1).equals("@")){
                        messageBuffer.append(scn.nextLine());
                        messageBuffer.trimToSize();
                        
                        client.sendto(messageBuffer.toString(), command.substring(1, command.length()), nick);
                        System.out.println("Message sent to " + command.substring(1, command.length()));
                    }else{
                        messageBuffer.append(command);
                        messageBuffer.append(scn.nextLine());
                        messageBuffer.trimToSize();
                        
                        client.sendall(messageBuffer.toString(), nick);
                        System.out.println("Message sent to all #channel.");
                    }
                }
            }
        }
        
        System.out.println("Bye bye!");
    }
    
}
