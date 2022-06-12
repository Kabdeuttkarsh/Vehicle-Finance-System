package com.solutionstouch.omsaifinance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.solutionstouch.omsaifinance.adapter.EmiAdapter;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.Transaction;
import com.solutionstouch.omsaifinance.model.TransactionResult;
import com.solutionstouch.omsaifinance.model.User;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmiScheduleActivity extends AppCompatActivity {
    LocalStorage localStorage;
    Gson gson;
    User user;
    String loan_id;
    String user_id;
    private ArrayList<Transaction> transactions;
    private ArrayList<Transaction> emiSchedule;
    String borrower_name;
    String mobile_no;
    String vehicle_model;
    String vehicle_model_type;
    RecyclerView mRecyclerView;
    Toolbar mToolbar;
    TextView textView;
    private EmiAdapter emiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_schedule);
        mRecyclerView = findViewById(R.id.rv_emi_detail);
        mToolbar = findViewById(R.id.toolbarEmi);
        textView = findViewById(R.id.noEmi);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            loan_id = extras.getString("loan_id");
            Log.d("loan_id",loan_id);
        }
        localStorage = new LocalStorage(getApplicationContext());
        gson = new Gson();
        user = gson.fromJson(localStorage.getUserLogin(),User.class);
        user_id = user.getUser_id();
        Log.d("user_id",user_id);
        if (TextUtils.isEmpty(user_id) && TextUtils.isEmpty(loan_id)){
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{
            getAllEmi(loan_id,user_id);
        }
    }
    public void getAllEmi(String loan_id, String user_id){
        Call<TransactionResult> call = RestClient.getRestService(getApplicationContext()).getCollections(user_id,loan_id);
        call.enqueue(new Callback<TransactionResult>() {
            @Override
            public void onResponse(Call<TransactionResult> call, Response<TransactionResult> response) {
                if (response.isSuccessful()){
                    textView.setText("EMI");
                    textView.setTextColor(Color.BLACK);
                    TransactionResult transactionResult = response.body();
                    transactions = transactionResult.getTransaction();
                    emiSchedule = transactionResult.getEmiSchedule();
                    borrower_name = transactionResult.getBorrower_name();
                    mobile_no = transactionResult.getMobile_no();
                    vehicle_model = transactionResult.getVehicle_model();
                    vehicle_model_type = transactionResult.getVehicle_model_type();
                    // mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    emiAdapter = new EmiAdapter(getApplicationContext(),emiSchedule);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    mRecyclerView.setLayoutManager(manager);
                    mRecyclerView.setAdapter(emiAdapter);
                    Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Try Again",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TransactionResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
