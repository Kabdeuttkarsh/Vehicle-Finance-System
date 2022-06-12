package com.solutionstouch.omsaifinance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PayActivity extends AppCompatActivity{

    String emi_id;
    String loan_id;
    String borrower_id;
    String due_amount;
    String emi_due_months;
    String emi_amount;
    String financer_id;
    String date;
    String mode;
    int grand;
    int due;
    TextView pay_due_amount;
    TextView pay_emi_amount;
    TextView pay_grand_amount;
    Button pay_grand_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        emi_id = getIntent().getStringExtra("emi_id");
        loan_id = getIntent().getStringExtra("loan_id");
        borrower_id = getIntent().getStringExtra("borrower_id");
        due_amount = getIntent().getStringExtra("due_amount");
        emi_due_months = getIntent().getStringExtra("emi_due_months");
        emi_amount = getIntent().getStringExtra("emi_amount");
        financer_id = getIntent().getStringExtra("financer_id");
        date = getIntent().getStringExtra("date");
        mode = getIntent().getStringExtra( "mode" );
        due = Integer.parseInt(emi_due_months) * Integer.parseInt(due_amount);
        pay_due_amount = findViewById(R.id.pay_due_amount);
        pay_emi_amount = findViewById(R.id.pay_emi_amount);
        pay_grand_amount = findViewById(R.id.pay_grand_amount);
        pay_grand_pay = findViewById(R.id.pay_grand_pay);
        pay_due_amount.setText(String.valueOf(due));
        pay_emi_amount.setText(emi_amount);
        grand = (Integer.parseInt(emi_due_months) * Integer.parseInt(due_amount)) + Integer.parseInt(emi_amount);
        pay_grand_amount.setText(String.valueOf(grand));
        pay_grand_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayActivity.this, Checksum.class);
                intent.putExtra("emi_id", emi_id);
                intent.putExtra("loan_id", loan_id);
                intent.putExtra("borrower_id", borrower_id);
                intent.putExtra("due_amount", String.valueOf(due));
                intent.putExtra("emi_due_months", emi_due_months);
                intent.putExtra("emi_amount", emi_amount);
                intent.putExtra("financer_id", financer_id);
                intent.putExtra("date", date);
                intent.putExtra("mode", mode);
                startActivity(intent);
            }
        });

        if (ContextCompat.checkSelfPermission(PayActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PayActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }
    }
}


