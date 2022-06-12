package com.solutionstouch.omsaifinance.api;


import com.solutionstouch.omsaifinance.model.ColectionResponse;
import com.solutionstouch.omsaifinance.model.DocumentResult;
import com.solutionstouch.omsaifinance.model.GeneratePaytmChecksomeResponse;
import com.solutionstouch.omsaifinance.model.LoanResult;
import com.solutionstouch.omsaifinance.model.TransactionResult;
import com.solutionstouch.omsaifinance.model.UserResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestService {

    @FormUrlEncoded
    @POST("User/login_user")
    Call<UserResult> login(@Field("mobile_no") String mobile_no);

    @FormUrlEncoded
    @POST("loan/noc_request_send")
    Call<UserResult> noc_request(@Field("loan_id") String loan_id);

    @FormUrlEncoded
    @POST("loan/fc_request_send")
    Call<UserResult> fc_request(@Field("loan_id") String loan_id);

    @GET("Loan/loan_list")
    Call<LoanResult> getMyLoans(@Query("borrower_id") String borrower_id);

    @GET("Borrowers_doc/all_doc")
    Call<DocumentResult> getMyDocs(@Query("borrowers_id") String borrowers_id);

    @GET("Collections/borrower_all_collections")
    Call<TransactionResult> getCollections(@Query("borrower_id") String borrower_id,
                                           @Query("loan_id") String loan_id);


    @POST("Payment_by_paytm/payby_paytm")
    @FormUrlEncoded
    Call<GeneratePaytmChecksomeResponse> generatePaytmChecksome(@Field("emi_amount") String emi_amount, @Field("borrower_id") String borrower_id);

    @POST("Collections/insert")
    @FormUrlEncoded
    Call<ColectionResponse> submitCollection(@Field("emi_id") String emi_id,
                                             @Field("borrower_id") String borrower_id,
                                             @Field("loan_id") String loan_id,
                                             @Field("financer_id") String financer_id,
                                             @Field("amount_paid") String amount_paid,
                                             @Field("due_amount") String due_amount,
                                             @Field("payment_mode") String payment_mode,
                                             @Field("collection_completed_type") String collection_completed_type,
                                             @Field("narration") String narration,
                                             @Field("collected_by") String collected_by,
                                             @Field("collection_receipt_no") String collection_receipt_no,
                                             @Field("collection_date") String collection_date,
                                             @Field("STATUS") String STATUS,
                                             @Field("BANKNAME") String BANKNAME,
                                             @Field("TXNID") String TXNID,
                                             @Field("RESPCODE") String RESPCODE,
                                             @Field("PAYMENTMODE") String PAYMENTMODE,
                                             @Field("BANKTXNID") String BANKTXNID,
                                             @Field("CURRENCY") String CURRENCY,
                                             @Field("GATEWAYNAME") String GATEWAYNAME,
                                             @Field("RESPMSG") String RESPMSG
                                             );
}
