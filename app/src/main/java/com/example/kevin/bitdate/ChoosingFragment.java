package com.example.kevin.bitdate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ChoosingFragment extends Fragment implements
        UserDataSource.UserDataCallbacks{

    private CardStackContainer mCardStack;

    public ChoosingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mCardStack = (CardStackContainer)v.findViewById(R.id.card_stack);
        UserDataSource.getUnseenUsers(this);

        ImageButton nahButton = (ImageButton)v.findViewById(R.id.nah_button);
        nahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.swipeLeft();
            }
        });
        ImageButton yeahButton = (ImageButton)v.findViewById(R.id.yeah_button);
        yeahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardStack.swipeRight();
            }
        });

        return v;
    }

    @Override
    public void onUsersFetched(List<User> users) {
        CardAdapter cardAdapter = new CardAdapter(getActivity(), users);
        mCardStack.setAdapter(cardAdapter);
    }
}
