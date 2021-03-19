package com.example.groupcat2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Registration extends Fragment {

   EditText surname,course,regno,year;
  Button register;
  SendMessage SM;


    public Registration()

    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration, container, false);
        register = view.findViewById(R.id.register);
        surname = view.findViewById(R.id.surname);
        course = view.findViewById(R.id.course);
        regno = view.findViewById(R.id.regno);
        year = view.findViewById(R.id.year);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(surname.getText().toString().isEmpty())
                {
                    surname.setError("Required");
                    return;
                }
                if(course.getText().toString().isEmpty())
                {
                    course.setError("Required");
                    return ;
                }
                if(regno.getText().toString().isEmpty())
                {
                    regno.setError("Required");
                    return;
                }
                if(year.getText().toString().isEmpty())
                {
                    year.setError("Required");
                    return;
                }
               Model model = new Model(surname.getText().toString(),
                       course.getText().toString(),
                       regno.getText().toString(),
                       year.getText().toString());
                FirebaseAuth mauth;
                String id;
                mauth = FirebaseAuth.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                Random rand = new Random();
                int key = rand.nextInt(2147483647);
                DatabaseReference myRef = database.getReference("Students").child(String.valueOf(key));
                myRef.setValue(model);
                Toast.makeText(getContext(),"Thankyou kindly Complete Registration",Toast.LENGTH_LONG).show();
                surname.setText("");
                course.setText("");
                regno.setText("");
                year.setText("");
                SM.sendData(String.valueOf(key));
            }

        });

    return view;
    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
}
