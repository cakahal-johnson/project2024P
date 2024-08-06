package com.example.assignmentapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AsstNum1 extends AppCompatActivity {
    LinearLayout container;
    LayoutInflater layoutInflater;
    EditText pname, pnumber;
    TextView initial;
    Button bun;
    AlertDialog alertDialog;
    AlertDialog.Builder alertBuilder;
    ArrayList contacts = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asst_num1);

        initial = findViewById(R.id.initial);
        pname = findViewById(R.id.pname);
        pnumber = findViewById(R.id.pnumber);
        bun = findViewById(R.id.bun);
        container = findViewById(R.id.container);
        layoutInflater = layoutInflater.from(this);

        alertBuilder = new AlertDialog.Builder(AsstNum1.this);

        bun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                String nameTxt = pname.getText().toString();
                String phoneTxt = pnumber.getText().toString();

                String[] contact = {nameTxt, phoneTxt};
                contacts.add(contact);

                for (int i = 0; i < contacts.size(); i++) {
                    String[] single_person = (String[]) contacts.get(i);
                    String name = single_person[0];
                    String phone = single_person[1];

                    String initial = name.trim().substring(0,1);

                    View view = layoutInflater.inflate(R.layout.asst_user, container, false);
                    TextView name_textview = view.findViewById(R.id.name);
                    TextView phone_textview = view.findViewById(R.id.phone_number);
                    TextView initial_textview = view.findViewById(R.id.initial);
                    TextView delete_textview = view.findViewById(R.id.delelete);

                    name_textview.setText(name);
                    phone_textview.setText(phone);
                    initial_textview.setText(initial);

                    container.addView(view);

                    delete_textview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {

                            alertBuilder.setTitle("DELETE");
                            alertBuilder.setMessage("Are you sure");

                            alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    container.removeView(view);
                                }
                            });

                            alertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });

                            AlertDialog alertDialog = alertBuilder.create();
                            alertDialog.show();

                        }
                    });
                }
            }
        });
    }

}