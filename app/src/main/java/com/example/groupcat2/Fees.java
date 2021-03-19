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

import com.google.android.gms.common.util.NumberUtils;

public class Fees extends Fragment {
    EditText fees;
    String code;
    Button pay;
    SendMessage2 DM;
    SendMessage3 DM2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fees,container,false);
        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fees = view.findViewById(R.id.fees);
        pay = view.findViewById(R.id.pay);





    }

    interface SendMessage2{
        void sendData2(String message);
    }

    interface SendMessage3
    {
        void sendData3(int fee);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            DM = (SendMessage2) getActivity();
            DM2 = (SendMessage3) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
    protected String displayReceivedData (String message)
    {

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fees.getText().toString().trim().isEmpty())
                {
                    fees.setError("PLease fill in the fee");
                    return;
                }
                String amount = fees.getText().toString().trim();
                int amountpaid = Integer.valueOf(amount);
                if(amountpaid < 55000)
                {
                    Toast.makeText(getContext(),"Kindly pay 55000",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Proceed to book units",Toast.LENGTH_LONG).show();

                }
                DM2.sendData3(amountpaid);
                DM.sendData2(message);

            }
        });
        return message;

    }


}
