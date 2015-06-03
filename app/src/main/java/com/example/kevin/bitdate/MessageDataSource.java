package com.example.kevin.bitdate;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by kevin on 6/2/15.
 */
public class MessageDataSource {

    private static final String TAG = "MessageDataSource";
    private static final Firebase sRef = new Firebase("https://kcrimi-bitdate.firebaseio.com/messages");
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddmmss");


    public static void saveMessage(Message message, String convoId){
        Date date = message.getDate();
        String key = sDateFormat.format(date);
        HashMap<String, String> msg = new HashMap<>();
        msg.put("text", message.getText());
        msg.put("sender", message.getSender());
        sRef.child(convoId).child(key).setValue(msg);
    }

    public static MessagesListener addMessageListener(final String convoId, final MessagesCallbacks callbacks){
        MessagesListener listener = new MessagesListener(callbacks);
        sRef.child(convoId).addChildEventListener(listener);
        return listener;
    }

    public static void stop(MessagesListener listener){
        sRef.removeEventListener(listener);
    }

    public static class MessagesListener implements  ChildEventListener{

        private MessagesCallbacks callbacks;

        MessagesListener(MessagesCallbacks callbacks){

        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            HashMap<String, String> msg = (HashMap)dataSnapshot.getValue();
            Message message = new Message();
            message.setSender(msg.get("sender"));
            message.setText(msg.get("text"));
            try {
                message.setDate(sDateFormat.parse(dataSnapshot.getKey()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(callbacks != null){
                callbacks.onMessageAdded(message);
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    }

    public interface MessagesCallbacks{
        public void onMessageAdded(Message message);
    }
}
