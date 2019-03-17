package com.anto004.app_finals;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewToDoListFragment extends Fragment {

    public static final String TITLE = "title";
    public static final String DETAILS = "details";
    public static final String ADDITIONAL_INFO = "additional_info";
    public static final String DUE_DATE = "due_date";

    public NewToDoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_to_do_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText titleET = getActivity().findViewById(R.id.title_editText);
        final EditText detailsET = getActivity().findViewById(R.id.details_editText);
        final EditText additionalInfoET = getActivity().findViewById(R.id.additionalInfo_editText);
        final EditText dueDateET = getActivity().findViewById(R.id.dueDate_editText);

        Button okButton = getActivity().findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titleET.getText().toString();
                String dueDate = dueDateET.getText().toString();

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(dueDate)){
                    Toast.makeText(getActivity(), "Enter Title and DueDate!", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra(TITLE, title);
                data.putExtra(DETAILS, detailsET.getText().toString());
                data.putExtra(ADDITIONAL_INFO, additionalInfoET.getText().toString());
                data.putExtra(DUE_DATE, dueDate);

                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }
        });
    }
}
