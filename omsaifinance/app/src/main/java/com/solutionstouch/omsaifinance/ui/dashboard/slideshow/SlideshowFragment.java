package com.solutionstouch.omsaifinance.ui.dashboard.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.adapter.PayAdapter;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.Loan;
import com.solutionstouch.omsaifinance.model.LoanResult;
import com.solutionstouch.omsaifinance.model.User;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    LocalStorage localStorage;
    Gson gson = new Gson();
    private RecyclerView rvloan;
    private PayAdapter payAdapter;
    private ArrayList<Loan> loans;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Home");
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final ProgressBar progressBar = root.findViewById(R.id.progressbarPay);
        rvloan = root.findViewById(R.id.rvPay);
        localStorage = new LocalStorage(getContext());
        User user = gson.fromJson(localStorage.getUserLogin(), User.class);
        Call<LoanResult> call = RestClient.getRestService(getContext()).getMyLoans(user.getUser_id());
        call.enqueue(new Callback<LoanResult>() {
            @Override
            public void onResponse(Call<LoanResult> call, Response<LoanResult> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    LoanResult loanResult = response.body();
                    loans = loanResult.getLoan();
                    payAdapter = new PayAdapter(getContext(), loans);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    rvloan.setLayoutManager(manager);
                    rvloan.setAdapter(payAdapter);
                }else {
                    Toast.makeText(getContext(),"Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoanResult> call, Throwable t) {
                Log.d("EXception",t.toString());
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return root;
    };
    }

