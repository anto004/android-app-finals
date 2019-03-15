package com.anto004.app_finals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anto004.app_finals.model.ToDoList;
import java.util.List;

public class ToDoListAdapter extends ArrayAdapter<ToDoList> {

    private List<ToDoList> toDoLists;
    private int layoutResourceId;
    private LayoutInflater inflater;


    public ToDoListAdapter(Context context, int resource, List<ToDoList> objects) {
        super(context, resource, objects);

        toDoLists = objects;
        layoutResourceId = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        ToDoList toDoList = toDoLists.get(position);

        if(convertView == null){
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        holder.titleTV.setText(toDoList.getTitle());
        holder.detailsTV.setText(toDoList.getDetails());
        holder.dueDateTV.setText(toDoList.getDueDate());

        return convertView;
    }

    class Holder {
        TextView titleTV;
        TextView detailsTV;
        TextView dueDateTV;
        Holder(View view){
            titleTV = (TextView) view.findViewById(R.id.title_textview);
            detailsTV = (TextView) view.findViewById(R.id.details_textview);
            dueDateTV = (TextView) view.findViewById(R.id.duedate_textview);
        }
    }
}
