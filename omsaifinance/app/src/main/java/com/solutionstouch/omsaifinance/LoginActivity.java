package com.solutionstouch.omsaifinance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.User;
import com.solutionstouch.omsaifinance.model.UserResult;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtPhone,edtOTP;
    //buttons for generating OTP and verifying OTP
    private Button verifyOTPBtn,generateOTPBtn;
    //string for storing our verification ID
    private String verificationId;
    private static final int RC_SIGN_IN = 101;
    LocalStorage localStorage;
    User user;
    Gson gson = new Gson();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPhone = findViewById(R.id.idEdtPhoneNumber);
        edtOTP = findViewById(R.id.idEdtOtp);
        verifyOTPBtn = findViewById(R.id.idBtnVerify);
        generateOTPBtn = findViewById(R.id.idBtnGetOtp);
        edtOTP.setVisibility(View.GONE);
        verifyOTPBtn.setVisibility(View.GONE);
        localStorage = new LocalStorage(getApplicationContext());
        // Set button listen
        if (localStorage.isUserLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (TextUtils.isEmpty(edtPhone.getText().toString()) ){
                    //when mobile number text field is empty displaying a toast message.
                    Toast.makeText(LoginActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                }else{
                    //if the text field is not empty we are calling our send OTP method for gettig OTP from Firebase.
                    String phone = edtPhone.getText().toString();
                    Log.d("mobile: ",phone);
                    sendVerificationCode(phone);
                }
            }

        });
        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtOTP.getText().toString()) ){
                    //when mobile number text field is empty displaying a toast message.
                    Toast.makeText(LoginActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                }else{
                    //if the text field is not empty we are calling our send OTP method for getting OTP from Firebase.
                    verifyOtp(edtOTP.getText().toString());
                }
            }

        });
    }

    private void sendVerificationCode(String mobile_no) {
        Call<UserResult> call = RestClient.getRestService(getApplicationContext()).login(mobile_no);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                if (response.isSuccessful()){
                            UserResult userResult = response.body();
                            String userString = gson.toJson(userResult.getUser());
                            userString = userString+"";
                            localStorage.createUserLoginSession(userString);
                            verificationId = userResult.getUser().getOTP_for_LOGIN();
                            edtPhone.setVisibility(View.GONE);
                            generateOTPBtn.setVisibility(View.GONE);
                            edtOTP.setVisibility(View.VISIBLE);
                            verifyOTPBtn.setVisibility(View.VISIBLE);
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

    public void   verifyOtp(String otp){

            if (verificationId.equals(otp)){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
    }
}
