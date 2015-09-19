/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ircthrift;

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
        
        client.nick("AB");
    }
    
}
