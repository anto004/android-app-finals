package com.anto004.app_finals;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.anto004.app_finals.model.ToDoList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final List<ToDoList> toDoLists = setupData();
        ToDoListAdapter adapter = new ToDoListAdapter(getActivity(), R.layout.list_item, toDoLists);
        ListView listView = getActivity().findViewById(R.id.todolist_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDoList toDoList = toDoLists.get(position);
                Toast.makeText(getActivity(), toDoList.getAdditionalInfo(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private List<ToDoList> setupData(){
       ToDoList[] toDoLists = new ToDoList[]{
               new ToDoList("Grocery Shopping", "Groceries for pasta",
                       "Any supermarket", "04/01/2019"),
               new ToDoList("Meet up with Jane Doe", "Brunch",
                       "At a diner", "04/10/2019")
       };

       return new ArrayList<>(Arrays.asList(toDoLists));

    }
}
