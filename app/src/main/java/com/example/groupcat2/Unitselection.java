package com.example.groupcat2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Unitselection extends Fragment  {
    TextView feesv;
    ListView listView;
    ArrayAdapter <String> arrayAdapter;
    Button select;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.unitselection,container,false);
        return  view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feesv = view.findViewById(R.id.fees1);
        listView = view.findViewById(R.id.units);
        select = view.findViewById(R.id.book);
        String courses[] = {"Discrete Mathematics",
                "Compiler Construction",
                "Mobile Application",
                "Programming Languages",
                "Electronics ",
                "Vector Analysis",
                "Calculus 1",
                "Machine Learning"};
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_multiple_choice,courses);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(arrayAdapter);

    }
    protected void displayReceivedData (String message)
    {

        int feespaidm = Integer.valueOf(feesv.getText().toString().trim());

        select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (feespaidm >= 55000) {
                    List<String> list = new ArrayList<String>();
                    for (int m = 0; m < listView.getCount(); m++) {

                        if (listView.isItemChecked(m)) {

                            list.add((String) listView.getItemAtPosition(m));

                        }

                    }
                    int unitsselected = list.size();
                    if (unitsselected != 6) {
                        Toast.makeText(getContext(), "kindly select six units" , Toast.LENGTH_LONG).show();
                    } else {

                        String unitone = list.get(0);
                        String unittwo = list.get(1);
                        String unitthree = list.get(2);
                        String unitfour = list.get(3);
                        String unitfive = list.get(4);
                        String unitsix = list.get(5);
                        Model2 model2 = new Model2(unitone, unittwo, unitthree, unitfour, unitfive, unitsix);
                        FirebaseAuth mauth;
                        String id;
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Students").child(message).child("units");
                        myRef.setValue(model2);
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                    }


                }
                else
                {Toast.makeText(getContext(),"PAY KSH 55000",Toast.LENGTH_LONG).show();}
            }
        });
    }
    protected void displayfees(Integer fee)
    {
        feesv.setText(String.valueOf(fee));
    }
}
