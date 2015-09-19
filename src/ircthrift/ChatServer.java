/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ircthrift;

import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
/**
 *
 * @author Andre
 */
public class ChatServer {
    public static ChatHandler handler;
    public static ChatService.Processor processor;
    
    public static void main(String [] arg) throws TTransportException {
        handler = new ChatHandler();
        processor = new ChatService.Processor(handler);
        
        System.out.println("IRCThrift Server is Running ...");
        
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(9090);
        THsHaServer.Args args = new THsHaServer.Args(serverTransport);
        args.processor(processor);
        args.transportFactory(new TFramedTransport.Factory());
        TServer server = new THsHaServer(args);
        server.serve();
    }
}
