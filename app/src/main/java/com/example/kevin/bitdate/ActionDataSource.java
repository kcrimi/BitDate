package com.example.kevin.bitdate;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by kevin on 6/2/15.
 */
public class ActionDataSource {

    private static final String TAG = "Action Data Source";
    public static final String TABLE_NAME = "Action";
    public static final String COLUMN_BY_USER = "byUser";
    public static final String COLUMN_TO_USER = "toUser";
    public static final String COLUMN_TYPE = "type";

    public static final String TYPE_LIKED = "liked";
    public static final String TYPE_MATCHED = "matched";
    public static final String TYPE_SKIPPED = "skipped";

    public static void saveUserLiked(final String userId){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TABLE_NAME);
        query.whereEqualTo(COLUMN_TO_USER, ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo(COLUMN_BY_USER, userId);
        query.whereEqualTo(COLUMN_TYPE, TYPE_LIKED);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                ParseObject action = null;
                if (e == null && list.size() > 0) {
                    ParseObject otherAction = list.get(0);
                    otherAction.put(COLUMN_TYPE, TYPE_MATCHED);
                    otherAction.saveInBackground();
                    action = createAction(userId, TYPE_MATCHED);
                } else {
                    action = createAction(userId, TYPE_LIKED);
                }
                action.saveInBackground();
            }
        });
    }

    public static void saveUserSkipped(String userId){
        ParseObject action = createAction(userId, TYPE_SKIPPED);
        action.saveInBackground();
    }


    private static ParseObject createAction(String userId, String type) {
        Log.d(TAG, "userId" + userId);
        ParseObject action = new ParseObject(TABLE_NAME);
        Log.d(TAG, ""+ParseUser.getCurrentUser().getObjectId());
        action.put(COLUMN_BY_USER, ParseUser.getCurrentUser().getObjectId());
        action.put(COLUMN_TO_USER, userId);
        action.put(COLUMN_TYPE, type);
        return action;
    }
}
