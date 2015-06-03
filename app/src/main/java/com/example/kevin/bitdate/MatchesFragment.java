package com.example.kevin.bitdate;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends android.support.v4.app.Fragment
        implements ActionDataSource.ActionDataCallbacks, UserDataSource.UserDataCallbacks,
        AdapterView.OnItemClickListener{

    private static final String TAG = "MatchesFragment";
    private MatchesAdapter mAdapter;
    private ArrayList<User> mUsers;

    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ActionDataSource.getMatches(this);
        View v =  inflater.inflate(R.layout.fragment_matches, container, false);
        ListView matchesList = (ListView)v.findViewById(R.id.matches_list);
        mUsers = new ArrayList<>();
        mAdapter = new MatchesAdapter(mUsers);
        matchesList.setAdapter(mAdapter);
        matchesList.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = mUsers.get(position);
        Intent i = new Intent(getActivity(), ChatActivity.class);
        i.putExtra(ChatActivity.USER_EXTRA, user);
        startActivity(i);
    }

    @Override
    public void onFetchedMatches(List<String> matchIds) {
        UserDataSource.getUsersIn(matchIds, this);
    }

    @Override
    public void onUsersFetched(List<User> users) {
        for (User user : users){
            mUsers.clear();
            mUsers.addAll(users);
            mAdapter.notifyDataSetChanged();
        }
    }

    public class MatchesAdapter extends ArrayAdapter<User>{
        MatchesAdapter(List<User> users){
            super(MatchesFragment.this.getActivity(), android.R.layout.simple_list_item_1, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView v =  (TextView)super.getView(position, convertView, parent);
            v.setText(getItem(position).getFirstName());
            return v;
        }
    }
}
