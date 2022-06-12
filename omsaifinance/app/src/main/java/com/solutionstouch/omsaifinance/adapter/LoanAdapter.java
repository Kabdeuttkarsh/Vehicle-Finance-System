package com.solutionstouch.omsaifinance.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.EmiScheduleActivity;
import com.solutionstouch.omsaifinance.PayActivity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.TransactionActivity;
import com.solutionstouch.omsaifinance.model.Loan;

import java.util.ArrayList;

public class LoanAdapter  extends RecyclerView.Adapter<LoanAdapter.CustomViewHolder>{


    private Context context;
    private ArrayList<Loan> loans;
    private LayoutInflater inflater;
    private String loan_id;
    String approved = "0";
    String rejected = "0";
    String emi_amount;
    public LoanAdapter(Context context, ArrayList<Loan> loans) {
        this.context = context;
        this.loans = loans;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.loan_view_page, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Loan loan = loans.get(position);

//          holder.tvChapterName.setText(chapter.chapterName);
            holder.loanType.setText("Your Loan "+loans.get(position).vehicle_model);

        holder.loanAmount.setText(loan.getLoan_amount());
        holder.amountPaid.setText(loan.getPaid_amount());
        holder.amountRemaining.setText(loan.getRemaining_amount());
        holder.totalDue.setText(loan.getDue_amount());
        if (null == loan.getIs_rejected() || TextUtils.isEmpty(loan.getIs_rejected())) {
            rejected = "null";
        }
        else if (loan.getIs_rejected().equals("0")){
            rejected = "0";
        }else {
            rejected = "1";
        }

        if (loan.getIs_approved().equals("0") ) {
                holder.approved.setText("Pending, Please Wait.");
                holder.approved.setTextColor(Color.DKGRAY);
            }
        else if (loan.getIs_approved().equals("1") && rejected.equals("0")) {
                holder.approved.setText("Approved");
                holder.approved.setTextColor(Color.GREEN);
            }
        else if (loan.getIs_approved().equals("0") && rejected.equals("1")) {
                holder.approved.setText("Rejected");
                holder.approved.setTextColor(Color.RED);
            }
        else{
            holder.approved.setText("Contact to ADMIN");
            holder.approved.setTextColor(Color.RED);
        }
//        holder.progressBar.setVisibility(View.GONE);
        loan_id = loans.get(position).getLoan_id();
        emi_amount = loans.get(position).getEmi_amount();

        holder.payEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PayActivity.class);
                intent.putExtra("loan_id",loan_id);
                intent.putExtra("emi_amount",emi_amount);
                context.startActivity(intent);
          }
        });

        holder.seeTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TransactionActivity.class);
                intent.putExtra("loan_id",loan_id);
                context.startActivity(intent);
            }
        });

        holder.emi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, EmiScheduleActivity.class);
                intent.putExtra("loan_id",loan_id);
                context.startActivity(intent);
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
        public TextView approved;
        public ProgressBar progressBar;
        public Button payEMI;
        public Button seeTrans;
        public Button emi;
        public CustomViewHolder(View itemView) {
            super(itemView);

            loanType = (TextView) itemView.findViewById(R.id.loanType);
            loanAmount = (TextView) itemView.findViewById(R.id.loanAmount);
         amountPaid = (TextView) itemView.findViewById(R.id.amountPaid);
            amountRemaining = (TextView) itemView.findViewById(R.id.amountRemaining);
            totalDue = (TextView) itemView.findViewById(R.id.totalDue);
            payEMI = (Button) itemView.findViewById(R.id.idpayEMI);
            seeTrans = (Button) itemView.findViewById(R.id.idTransactions);
            approved = (TextView) itemView.findViewById(R.id.approvedStatus) ;
            emi = (Button) itemView.findViewById(R.id.idEmiSchedule);
        }
    }
}
