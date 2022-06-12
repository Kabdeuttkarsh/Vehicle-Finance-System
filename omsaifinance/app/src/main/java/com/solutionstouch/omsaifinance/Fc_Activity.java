package com.solutionstouch.omsaifinance;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fc_Activity extends AppCompatActivity {
    public  String loan_id;
    private Button send_request_fc_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fc);
        loan_id = getIntent().getStringExtra("loan_id");
        send_request_fc_btn = findViewById(R.id.idBtnGetfcsend);

        send_request_fc_btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (TextUtils.isEmpty(loan_id.toString()) ){
                    //when mobile number text field is empty displaying a toast message.
                    Toast.makeText(Fc_Activity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                }else{
                    //if the text field is not empty we are calling our send OTP method for gettig OTP from Firebase.
                    sendfcrequest(loan_id);
                }
            }

        });

    }
    private void sendfcrequest(String loan_id) {

        Call<UserResult> call = RestClient.getRestService(getApplicationContext()).fc_request(loan_id);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sended", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed...Please Retry", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.d("Error: ",t.toString());
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}