package com.solutionstouch.omsaifinance.ui.dashboard.emi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.solutionstouch.omsaifinance.R;

public class EmiFragment extends Fragment {

    EmiViewModel emiViewModel;
    SeekBar seekBar_loan, seekBar_interest, seekBar_tenure;
    EditText loan_amount, interest_rate, loan_tenure;
    Button cal;
    TextView res;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        emiViewModel =
                ViewModelProviders.of(this).get(EmiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_emi, container, false);

        final TextView textView = root.findViewById(R.id.text_emi);

        seekBar_interest = root.findViewById(R.id.seekBar_interest);
        seekBar_loan = root.findViewById(R.id.seekBar_loan);
        seekBar_tenure = root.findViewById(R.id.seekBar_tenure);
        loan_amount = root.findViewById(R.id.loan_amount);
        interest_rate = root.findViewById(R.id.interest_rate);
        loan_tenure = root.findViewById(R.id.loan_tenure);
        cal = root.findViewById(R.id.cal);
        res = root.findViewById(R.id.res);
        emiViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        seekBar_loan.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                loan_amount.setText(String.valueOf(i)+"00000");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar_interest.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                interest_rate.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        seekBar_tenure.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                loan_tenure.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        cal.setOnClickListener(view -> {
            double principal_amount = Double.parseDouble(loan_amount.getText().toString());
            double rate_of_interest = Double.parseDouble(interest_rate.getText().toString());
            double tenure = Double.parseDouble(loan_tenure.getText().toString());

            rate_of_interest = rate_of_interest / (12*100);
            tenure = tenure ;

        //    int emi= (int) ((principal_amount*rate_of_interest*Math.pow(1+rate_of_interest,tenure))/(Math.pow(1+rate_of_interest,tenure)-1));
            double  emi = ((principal_amount * rate_of_interest * Math.pow(1 + rate_of_interest, tenure)) / (Math.pow(1 + rate_of_interest, tenure) - 1));

            res.setText("EMI: " + String.valueOf( Math.round(emi)));
        });
        return root;
    }
}
