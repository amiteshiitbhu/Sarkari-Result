package com.nirmallabs.sarkariresult;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nirmallabs.sarkariresult.model.Result;

import java.util.Calendar;

public class AddNewResultDetail extends AppCompatActivity implements View.OnClickListener {

    private EditText nameOfPost;
    private EditText shortInformation;
    private EditText lastDate;
    private EditText applyLink;
    private Button btnSubmit;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_result);

        nameOfPost = findViewById(R.id.nameOfPost);
        shortInformation = findViewById(R.id.shortInformation);
        lastDate = findViewById(R.id.lastDate);
        applyLink = findViewById(R.id.applyLink);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        mFirestore = FirebaseFirestore.getInstance();

        lastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        lastDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                };
                DatePickerDialog dpDialog = new DatePickerDialog(AddNewResultDetail.this, listener, 2019, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                dpDialog.show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Result result = new Result(nameOfPost.getText().toString(),
                        shortInformation.getText().toString(),
                        lastDate.getText().toString(),
                        applyLink.getText().toString());

                mFirestore.collection("results").add(result).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddNewResultDetail.this, "Detail Submitted", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNewResultDetail.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });
                finish();
                break;
        }
    }
}
