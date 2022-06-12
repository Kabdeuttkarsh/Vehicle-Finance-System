package com.solutionstouch.omsaifinance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.PayActivity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.Loan;

import java.util.ArrayList;

public class PayAdapter extends RecyclerView.Adapter<PayAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Loan> loans;
    private LayoutInflater inflater;
    private String loan_id;

    public PayAdapter(Context context, ArrayList<Loan> loans) {
        this.context = context;
        this.loans = loans;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PayAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.loan_pay_page, parent, false);
        return new PayAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PayAdapter.CustomViewHolder holder, int position) {
        Loan loan = loans.get(position);
        holder.loanType.setText("Loan "+loans.get(position).vehicle_model);
        holder.loanAmount.setText(loan.getLoan_amount());
        holder.amountPaid.setText(loan.getPaid_amount());
        holder.amountRemaining.setText(loan.getExtra_amount());
        holder.totalDue.setText(loan.getDue_amount());
//        holder.progressBar.setVisibility(View.GONE);
        loan_id = loans.get(position).getLoan_id();
        holder.payEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PayActivity.class);
                context.startActivity(intent);
            }
        });

        holder.payEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "Coming Soon....!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.payALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "Coming Soon....!!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return loans.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView loanType;
        public TextView loanAmount;
        public TextView emiPaid;
        public TextView emiRemaining;
        public TextView amountPaid;
        public TextView amountRemaining;
        public TextView totalDue;
        public TextView loanEndon;
        public ProgressBar progressBar;
        public Button payEMI;
        public Button payALL;
        public CustomViewHolder(View itemView) {
            super(itemView);

            loanType = (TextView) itemView.findViewById(R.id.payloanType);
            loanAmount = (TextView) itemView.findViewById(R.id.payloanAmount);
            amountPaid = (TextView) itemView.findViewById(R.id.payamountPaid);
            amountRemaining = (TextView) itemView.findViewById(R.id.payamountRemaining);
            totalDue = (TextView) itemView.findViewById(R.id.paytotalDue);
            payEMI = (Button) itemView.findViewById(R.id.payEMI);
            payALL = (Button) itemView.findViewById(R.id.idPayall);
        }
    }

}
