package com.solutionstouch.omsaifinance.ui.dashboard.tools;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.adapter.FCAdapter;
import com.solutionstouch.omsaifinance.adapter.NocAdapter;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.Loan;
import com.solutionstouch.omsaifinance.model.LoanResult;
import com.solutionstouch.omsaifinance.model.User;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    LocalStorage localStorage;
    Gson gson = new Gson();
    private RecyclerView rvFc;
    private RecyclerView rvNoc;
    private NocAdapter nocAdapter;
    private FCAdapter fcAdapter;
    private ArrayList<Loan> loans;
    ProgressBar fc;
    ProgressBar noc;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Request Status");

        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragemnt_request, container, false);
        fc = root.findViewById(R.id.progressbarRequest);
        noc = root.findViewById(R.id.progressbarNoc);
        rvFc = root.findViewById(R.id.rvRequest);
        rvNoc = root.findViewById(R.id.rvNoc);
//        toolsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//                textView.setText(s);
//            }
//        });
        localStorage = new LocalStorage(getContext());
        User user = gson.fromJson(localStorage.getUserLogin(), User.class);
        loadFcLoans(user.getUser_id());
        LoadNocLoans(user.getUser_id());
        return root;
    }

    public void loadFcLoans(String user_id){
        Call<LoanResult> call = RestClient.getRestService(getContext()).getMyLoans(user_id);
        call.enqueue(new Callback<LoanResult>() {
            @Override
            public void onResponse(Call<LoanResult> call, Response<LoanResult> response) {
                if (response.isSuccessful()){
                    fc.setVisibility(View.GONE);
                    LoanResult loanResult = response.body();
                    loans = loanResult.getLoan();
                    fcAdapter = new FCAdapter(getContext(), loans);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    rvFc.setLayoutManager(manager);
                    rvFc.setAdapter(fcAdapter);
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
    }

    public void LoadNocLoans(String user_id){
        Call<LoanResult> call = RestClient.getRestService(getContext()).getMyLoans(user_id);
        call.enqueue(new Callback<LoanResult>() {
            @Override
            public void onResponse(Call<LoanResult> call, Response<LoanResult> response) {
                if (response.isSuccessful()){
                    noc.setVisibility(View.GONE);
                    LoanResult loanResult = response.body();
                    loans = loanResult.getLoan();
                    nocAdapter = new NocAdapter(getContext(), loans);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    rvNoc.setLayoutManager(manager);
                    rvNoc.setAdapter(nocAdapter);
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
    }
}
