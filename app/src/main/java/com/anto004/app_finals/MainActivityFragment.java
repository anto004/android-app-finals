package com.anto004.app_finals;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.anto004.app_finals.database.DBHelper;
import com.anto004.app_finals.model.ToDoList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final int REQUEST_CODE = 100;
    private static final String LOG_TAG = "MainActivityFragment";

    private ToDoListAdapter adapter;
    private DBHelper dbHelper;
    List<ToDoList> toDoLists;

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

        try {
            dbHelper = new DBHelper(getActivity());
            toDoLists = dbHelper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new ToDoListAdapter(getActivity(), R.layout.list_item, toDoLists);
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

        FloatingActionButton fab = getActivity().findViewById(R.id.newToDoList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewToDoList.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getExtras();
                String title = bundle.getString(NewToDoListFragment.TITLE);
                String details = bundle.getString(NewToDoListFragment.DETAILS, "");
                String additionalInfo = bundle.getString(NewToDoListFragment.ADDITIONAL_INFO, "");
                String dueDate = bundle.getString(NewToDoListFragment.DUE_DATE, "");

                ToDoList toDoList = new ToDoList(title, details, additionalInfo, dueDate);

                long rowId = 0;
                if(dbHelper != null){
                    rowId = dbHelper.insert(toDoList);
                    toDoList.setId(rowId);
                }

                adapter.add(toDoList);
                adapter.notifyDataSetChanged();

            }
        }
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
