/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ircthrift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TException;

/**
 *
 * @author Andre
 */
public class ChatHandler implements ChatService.Iface{
    
    private Map<String, List<String>> user_membership;
    private Map<String, List<String>> user_message;
    
    
    public ChatHandler(){
        user_membership = new HashMap<>();
        user_message = new HashMap<>();
    }

    @Override
    public boolean nick(String nickname) throws TException {
        if (user_membership.containsKey(nickname)){
            return false;
        }else{
            user_membership.put(nickname, new ArrayList<String>());
            user_message.put(nickname, new ArrayList<String>());
            return true;
        }
    }

    @Override
    public boolean join(String channelname, String nickname) throws TException {
        if (user_membership.containsKey(nickname)){
            if (user_membership.get(nickname).contains(channelname)){
                return false;
            }else{
                user_membership.get(nickname).add(channelname);
                return true;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean leave(String channelname, String nickname) throws TException {
        if (user_membership.containsKey(nickname)){
            if (user_membership.get(nickname).contains(channelname)){
                user_membership.get(nickname).remove(channelname);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean sendall(String message, String nickname) throws TException {
        int allChannel = user_membership.get(nickname).size();
        for (int i = 0; i < allChannel; i++){
            
            sendto(message, user_membership.get(nickname).get(i), nickname);
        }
        return true;
    }

    @Override
    public boolean sendto(String message, String channelname, String nickname) throws TException {
        
        for (Map.Entry<String, List<String>> entry : user_membership.entrySet()){
            if (entry.getValue().contains(channelname)){
                user_message.get(entry.getKey()).add("[" + channelname + "] (" + nickname + ") " + message);
            }
        }

        return true;
    }

    @Override
    public List<String> getmessage(String nickname) throws TException {
        List<String> message = new ArrayList<>(user_message.get(nickname));
        user_message.get(nickname).clear();
        return message;
    }

}
