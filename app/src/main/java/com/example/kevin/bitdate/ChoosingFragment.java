package com.example.kevin.bitdate;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ChoosingFragment extends Fragment {

    public ChoosingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        CardStackContainer cardStack = (CardStackContainer)v.findViewById(R.id.card_stack);
        User user = new User();
        user.setFirstName("Kevin");
        List<User> users = new ArrayList<User>();
        users.add(user);
        CardAdapter cardAdapter = new CardAdapter(getActivity(), users);
        cardStack.setAdapter(cardAdapter);
        return v;
    }
}
