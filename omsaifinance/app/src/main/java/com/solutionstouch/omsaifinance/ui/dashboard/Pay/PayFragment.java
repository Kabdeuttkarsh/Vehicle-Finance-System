package com.solutionstouch.omsaifinance.ui.dashboard.Pay;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.solutionstouch.omsaifinance.R;

public class PayFragment extends Fragment {

    private PayViewModel payViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        payViewModel =
                ViewModelProviders.of(this).get(com.solutionstouch.omsaifinance.ui.dashboard.Pay.PayViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pay, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        payViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        return root;
    }
}
