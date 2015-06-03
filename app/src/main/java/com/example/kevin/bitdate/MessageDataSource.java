package com.example.kevin.bitdate;

import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by kevin on 6/2/15.
 */
public class MessageDataSource {

    private static final Firebase sRef = new Firebase("https://kcrimi-bitdate.firebaseio.com/messages");

    public static void saveMessage(Message message, String convoId){
        Date date = message.getDate();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddmmss");
        String key = df.format(date);
        HashMap<String, String> msg = new HashMap<>();
        msg.put("text", message.getText());
        msg.put("sender", message.getSender());
        sRef.child(convoId).child(key).setValue(msg);
    }

}
