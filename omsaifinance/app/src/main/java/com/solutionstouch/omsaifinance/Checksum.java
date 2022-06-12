package com.solutionstouch.omsaifinance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.ColectionResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checksum extends AppCompatActivity implements PaytmPaymentTransactionCallback {

    String custid , orderId , mid = "";
    String STATUS;
    String ORDERID;
    String TXNAMOUNT;
    String TXNDATE;
    String TXNID;
    String BANKNAME;
    String RESPCODE;
    String PAYMENTMODE;
    String BANKTXNID;
    String CURRENCY;
    String GATEWAYNAME;
    String RESPMSG;

    String emi_id;
    String loan_id;
    String borrower_id;
    String due_amount;
    String emi_due_months;
    String emi_amount;
    String financer_id;
    String date;
    String mode;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //initOrderId();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        emi_id = intent.getExtras().getString("emi_id");
        loan_id = intent.getExtras().getString("loan_id");
        borrower_id = intent.getExtras().getString("borrower_id");
        custid = intent.getExtras().getString("borrower_id");
        due_amount = intent.getExtras().getString("due_amount");
        emi_due_months = intent.getExtras().getString("emi_due_months");
        emi_amount = intent.getExtras().getString("emi_amount");
        financer_id = intent.getExtras().getString("financer_id");
        date = intent.getExtras().getString("date");
        mode = intent.getExtras().getString("mode");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-");
        LocalDateTime now = LocalDateTime.now();
        orderId = String.valueOf(new Random().nextInt(61) + 20);
        Log.d("order id",orderId);
        mid = "iiFPxQ63678151339244"; /// your marchant id
        if (TextUtils.isEmpty(due_amount) || due_amount == null){
            due_amount = "0";
        }
        Log.d("due amount","_"+due_amount);
        Log.d("emi amount","_"+emi_amount);
        sendUserDetailTOServerdd dl = new sendUserDetailTOServerdd();
        dl.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
// vollye , retrofit, asynch

    }

    @Override
    public void onTransactionResponse(Bundle inResponse) {
                Log.d("Response",inResponse.toString());
    }

    @Override
    public void networkNotAvailable() {
        Log.d("Nw","Error");

    }

    @Override
    public void clientAuthenticationFailed(String inErrorMessage) {
        Log.d("Response",inErrorMessage.toString());
    }

    @Override
    public void someUIErrorOccurred(String inErrorMessage) {
                Log.d("Response",inErrorMessage.toString());

    }

    @Override
    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
        Log.d("Response",inErrorMessage.toString());
    }

    @Override
    public void onBackPressedCancelTransaction() {
        Log.d("Back","pressed");
    }

    @Override
    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
        Log.d("Back",inErrorMessage+" "+inResponse);
    }

    public class sendUserDetailTOServerdd extends AsyncTask<ArrayList<String>, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(Checksum.this);

        //private String orderId , mid, custid, amt;
      //  String url = "https://www.blueappsoftware.com/payment/payment_paytm/generateChecksum.php";
        String url = "https://omsaifinance.solutionstouch.com/api/Paytm_gateway/start_paytm_payment";
        String varifyurl = "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+orderId;
       // String varifyurl = "https://securegw.paytm.in/theia/processTransaction";
        // "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID"+orderId;
        String CHECKSUMHASH = "";


        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected String doInBackground(ArrayList<String>... alldata) {
            JSONParser jsonParser = new JSONParser(Checksum.this);
            String param = "&MID=" + mid +
                    "&ORDER_ID=" + orderId +
                    "&CUST_ID=" + custid +
                    "&CHANNEL_ID=WEB" +
                    "&TXN_AMOUNT="+String.valueOf(Integer.parseInt(due_amount)+Integer.parseInt(emi_amount)) +
                    "&WEBSITE=WEBSTAGING" +
                    "&CALLBACK_URL=" + varifyurl +
                    "&INDUSTRY_TYPE_ID=Retail&" +
                    "CURRENCY=INR";


            JSONObject jsonObject = jsonParser.makeHttpRequest(url, "POST", param);
            // yaha per checksum ke saht order id or status receive hoga..
          Log.e("CheckSum result >>", jsonObject.toString());
            if (jsonObject != null) {
                Log.e("CheckSum result >>", jsonObject.toString());
                try {

                    CHECKSUMHASH = jsonObject.has("CHECKSUMHASH") ? jsonObject.getString("CHECKSUMHASH") : "";
                    Log.e("CheckSum result >>", CHECKSUMHASH);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return CHECKSUMHASH;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e(" setup acc ", "  signup result  " + result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            PaytmPGService Service = PaytmPGService.getProductionService();

            HashMap<String, String> paramMap = new HashMap<String, String>();
            //these are mandatory parameters
            paramMap.put("MID", mid); //MID provided by paytm
            paramMap.put("ORDER_ID", orderId);
            paramMap.put("CUST_ID", custid);
            paramMap.put("CHANNEL_ID", "WEB");
            paramMap.put("TXN_AMOUNT", String.valueOf(Integer.parseInt(due_amount)+Integer.parseInt(emi_amount)));
            paramMap.put("WEBSITE", "WEBSTAGING");
           // paramMap.put("CALLBACK_URL", varifyurl);
            paramMap.put( "CALLBACK_URL", "https://securegw.paytm.in/theia/paytmCallback?ORDER_ID="+orderId);
         //   paramMap.put( "EMAIL" , "abc@gmail.com");   // no need
        //    paramMap.put( "MOBILE_NO" , "9144040888");  // no need
            paramMap.put("CHECKSUMHASH", CHECKSUMHASH);
            //paramMap.put("PAYMENT_TYPE_ID" ,"CC");    // no need
            paramMap.put("INDUSTRY_TYPE_ID", "Retail");
              paramMap.put("CURRENCY", "INR");

            //  paramMap.put("THEME", "merchant");
            PaytmOrder Order = new PaytmOrder(paramMap);
            Log.e("checksum ", "param " + paramMap.toString());
            Service.initialize(Order, null);

//            TransactionManager transactionManager = new TransactionManager(paytmOrder, new
//                    PaytmPaymentTransactionCallback(){
//                        @Override
//                        public void onTransactionResponse(Bundle bundle) {
//                            Log.e("server response", ""+bundle);
//                            String status = bundle.getString("STATUS");
//                            String transactionid = bundle.getString("TXNID");
//                            String amount = bundle.getString("TXNAMOUNT");
//                            if(status!=null){
//                                Log.e("STATUS", status);
//                            }
//                        }
            // start payment service call here
         // Service.startPaymentTransaction(Checksum.this, true, true,
             //       Checksum.this);

            Service.startPaymentTransaction(Checksum.this, true, true, new PaytmPaymentTransactionCallback() {
                /*Call Backs*/
                public void someUIErrorOccurred(String inErrorMessage) {
                    Log.d("ui_error",inErrorMessage);
                }
                public void onTransactionResponse(Bundle bundle) {
                    Log.d("otr_error",bundle.toString());
                    STATUS = bundle.getString("STATUS");
                    BANKNAME = bundle.getString("BANKNAME");
                    ORDERID = bundle.getString("ORDERID");
                    TXNAMOUNT = bundle.getString("TXNAMOUNT");
                    TXNDATE = bundle.getString("TXNDATE");
                    TXNID = bundle.getString("TXNID");
                    RESPCODE = bundle.getString("RESPCODE");
                    PAYMENTMODE = bundle.getString("Online");
                    BANKTXNID = bundle.getString("BANKTXNID");
                    CURRENCY = bundle.getString("CURRENCY");
                    GATEWAYNAME = bundle.getString("GATEWAYNAME");
                    RESPMSG = bundle.getString("RESPMSG");
//                    TXNAMOUNT=due_amount+emi_amount;
                    if (RESPCODE.equals("01")){
                        Call<ColectionResponse> call = RestClient.getRestService(getApplicationContext()).submitCollection(
                                emi_id,borrower_id,loan_id,financer_id,TXNAMOUNT,due_amount,mode,"Regular","EMI Collection by paytm",
                                "Paytm Gateway",TXNID,TXNDATE,STATUS,BANKNAME,TXNID,RESPCODE,PAYMENTMODE,BANKTXNID,CURRENCY,
                                GATEWAYNAME,RESPMSG);
                        call.enqueue(new Callback<ColectionResponse>() {
                            @Override
                            public void onResponse(Call<ColectionResponse> call, Response<ColectionResponse> response) {
                                if (response.isSuccessful()){
                                    Log.d("executed"," api");
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ColectionResponse> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), RESPMSG, Toast.LENGTH_LONG).show();
                   }
                    Intent intent = new Intent(Checksum.this, MainActivity.class);
                    startActivity(intent);
                }
                public void networkNotAvailable() {
                    Log.d("nw"," error");
                }
                public void clientAuthenticationFailed(String inErrorMessage) {
                    Log.d("cae_error",inErrorMessage);
                }
                
                public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                    Log.d("oelwp_error",inErrorMessage+" "+inFailingUrl);
                }
                public void onBackPressedCancelTransaction() {
                    Log.d("back"," pressed");
                }

                public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                    Log.d("otc_error",inErrorMessage);
                }
            });

        }
    }


}