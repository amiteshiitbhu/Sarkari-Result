package com.nirmallabs.sarkariresult;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nirmallabs.sarkariresult.model.Result;

public class ResultDetailsActivity extends AppCompatActivity {

    private Result result;

    private TextView tvResultName;
    private TextView shortInformation;
    private TextView lastDate;
    private TextView applyLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowHomeEnabled(true);
        }

        tvResultName = findViewById(R.id.tvResultName);
        shortInformation = findViewById(R.id.shortInformation);
        lastDate = findViewById(R.id.lastDate);
        applyLink = findViewById(R.id.applyLink);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            result = (Result) extras.getSerializable("result");

        }

        tvResultName.setText(result.getNameOfPost());
        shortInformation.setText(result.getShortInformation());
        lastDate.setText(result.getLastDate());
        applyLink.setText(result.getApplyLink());

        applyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getApplyLink()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    ResultDetailsActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    intent.setPackage(null);
                    ResultDetailsActivity.this.startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
