package com.solutionstouch.omsaifinance.ui.dashboard.documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.adapter.DocumentAdapter;
import com.solutionstouch.omsaifinance.api.clients.RestClient;
import com.solutionstouch.omsaifinance.model.DocumentResult;
import com.solutionstouch.omsaifinance.model.Documents;
import com.solutionstouch.omsaifinance.model.User;
import com.solutionstouch.omsaifinance.util.localstorage.LocalStorage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentFragment extends Fragment {

    ProgressBar progressBar;
    LocalStorage localStorage;
    Gson gson = new Gson();
    ArrayList<Documents> documents;
    DocumentViewModel documentViewModel;
    private RecyclerView rcDocs;
    private DocumentAdapter docsAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("My Documents");
        documentViewModel =
                ViewModelProviders.of(this).get(DocumentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_document, container, false);
        progressBar = root.findViewById(R.id.documentProgress);
        rcDocs = root.findViewById(R.id.rvDocs);
        localStorage = new LocalStorage(getContext());
        User user = gson.fromJson(localStorage.getUserLogin(), User.class);
        documentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        Call<DocumentResult> call = RestClient.getRestService(getContext()).getMyDocs(user.getUser_id());
        call.enqueue(new Callback<DocumentResult>() {
            @Override
            public void onResponse(Call<DocumentResult> call, Response<DocumentResult> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    DocumentResult documentResult = response.body();
                    documents = documentResult.getDocuments();
                    docsAdapter = new DocumentAdapter(getContext(), documents);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
                    rcDocs.setLayoutManager(manager);
                    rcDocs.setAdapter(docsAdapter);
                }else{
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DocumentResult> call, Throwable t) {
                    Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG);
            }
        });
    return root;
    }
    }

