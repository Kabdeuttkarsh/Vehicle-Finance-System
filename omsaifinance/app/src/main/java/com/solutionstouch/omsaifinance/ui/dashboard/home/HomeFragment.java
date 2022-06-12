package com.solutionstouch.omsaifinance.ui.dashboard.home;
//profile

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.adapter.LoanAdapter;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.Loan;
import com.solutionstouch.omsaifinance.model.LoanResult;
import com.solutionstouch.omsaifinance.model.User;
import com.solutionstouch.omsaifinance.ui.dashboard.documents.DocumentFragment;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    LocalStorage localStorage;
    Gson gson = new Gson();
    private RecyclerView rvloan;
    private LoanAdapter loanAdapter;
    private ArrayList<Loan> loans;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Home");
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ImageView profile = root.findViewById(R.id.profile_image);
      //  final ImageView reschedule = root.findViewById(R.id.rschedule);
        final ProgressBar progressBar = root.findViewById(R.id.progressbar);
        final TextView username = root.findViewById(R.id.user_name);
        final TextView mobile = root.findViewById(R.id.mobile);
        final TextView gender = root.findViewById(R.id.gender);
        final Button documentsView = root.findViewById(R.id.documentsID);
         rvloan = root.findViewById(R.id.rvSubject);


        localStorage = new LocalStorage(getContext());
        User user = gson.fromJson(localStorage.getUserLogin(), User.class);
        username.setText(user.getFull_name());
        mobile.setText(user.getMobile_no());
        gender.setText(user.getGender());
        Call<LoanResult> call = RestClient.getRestService(getContext()).getMyLoans(user.getUser_id());
        call.enqueue(new Callback<LoanResult>() {
            @Override
            public void onResponse(Call<LoanResult> call, Response<LoanResult> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    LoanResult loanResult = response.body();
                   loans = loanResult.getLoan();
                    loanAdapter = new LoanAdapter(getContext(), loans);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    rvloan.setLayoutManager(manager);
                    rvloan.setAdapter(loanAdapter);
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"Loan not Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoanResult> call, Throwable t) {
                Log.d("EXception",t.toString());
                Toast.makeText(getContext(),t.toString(), Toast.LENGTH_LONG).show();
            }
        });


        documentsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DocumentFragment();
// Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment,fragment)
                        .commit();
            }
        });

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });

//        reschedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Repayment_Schedule.class);
//                startActivity(intent);
//
//
//            }
//        });
        return root;
    }
}

