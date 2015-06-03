package com.example.kevin.bitdate;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class ChatActivity extends ActionBarActivity implements View.OnClickListener{

    public static final String USER_EXTRA = "USER";
    public static final String TAG = "Chat Activity";


    private ArrayList<Message> mMessages;
    private MessageAdapter mAdapter;
    private User mRecipient;
    private ListView mListView;
    private Date mLastMessageDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mRecipient = (User)getIntent().getSerializableExtra(USER_EXTRA);
        mListView = (ListView)findViewById(R.id.messages_list);
        mAdapter = new MessageAdapter(mMessages);

        setTitle(mRecipient.getFirstName());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Button sendMessage= (Button)findViewById(R.id.send_message);
        sendMessage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText newMessageView = (EditText)findViewById(R.id.new_message);
        String newMessage = newMessageView.getText().toString();
        newMessageView.setText("");
        Message msg = new Message();
        msg.setDate(new Date());
        msg.setText(newMessage);
        msg.setSender(UserDataSource.getCurrentUser().getId());
        String[] ids = {mRecipient.getId(), UserDataSource.getCurrentUser().getId()};
        Arrays.sort(ids);
        String convoId = ids[0]+ids[1];
        MessageDataSource.saveMessage(msg, convoId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_settings){
            return true;
        }else if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MessageAdapter extends ArrayAdapter<Message> {

        MessageAdapter(ArrayList<Message> messages){
            super(ChatActivity.this, R.layout.messages_list_item, R.id.message, messages);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView =  super.getView(position, convertView, parent);
            Message message = getItem(position);

            TextView nameView = (TextView)convertView.findViewById(R.id.message);
            nameView.setText(message.getText());

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)nameView.getLayoutParams();

            int sdk = Build.VERSION.SDK_INT;
            if (message.getSender().equals(UserDataSource.getCurrentUser().getId())){
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN){
                    nameView.setBackground(getDrawable(R.drawable.bubble_right_green));
                }else{
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_right_green));
                }
                layoutParams.gravity = Gravity.RIGHT;
            }else{
                if (sdk >= Build.VERSION_CODES.JELLY_BEAN){
                    nameView.setBackground(getDrawable(R.drawable.bubble_left_gray));
                }else{
                    nameView.setBackgroundDrawable(getDrawable(R.drawable.bubble_left_gray));
                }
                layoutParams.gravity = Gravity.LEFT;
            }

            nameView.setLayoutParams(layoutParams);

            return convertView;
        }

    }
}
