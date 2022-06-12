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

import com.solutionstouch.omsaifinance.Fc_Activity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.Loan;

import java.util.ArrayList;

public class FCAdapter  extends RecyclerView.Adapter<FCAdapter.CustomViewHolder>{


    private Context context;
    private ArrayList<Loan> loans;
    private LayoutInflater inflater;
    private String loan_id;
    public String remain_amount;

    public FCAdapter(Context context, ArrayList<Loan> loans) {
        this.context = context;
        this.loans = loans;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.fc_view_page, parent, false);
        return new CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Loan loan = loans.get(position);
//        holder.tvChapterName.setText(chapter.chapterName);
        holder.loanType.setText("Loan "+loans.get(position).vehicle_model);
        remain_amount=loan.getExtra_amount().toString();
        holder.loanAmount.setText(loan.getLoan_amount());
        holder.amountPaid.setText(loan.getPaid_amount());
        holder.amountRemaining.setText(loan.getExtra_amount());
        holder.totalDue.setText(loan.getDue_amount());
//        holder.progressBar.setVisibility(View.GONE);
        loan_id = loans.get(position).getLoan_id();


        holder.fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Toast.makeText(context.getApplicationContext(), "Wait", Toast.LENGTH_SHORT).show();
                if(remain_amount.equals("0")){
//                    Toast.makeText(context.getApplicationContext(), loan_id, Toast.LENGTH_SHORT).show()
                    Toast.makeText(context.getApplicationContext(), "Not able to applied.", Toast.LENGTH_SHORT).show();
                }
                else{
                   Intent intent= new Intent(context, Fc_Activity.class);
                    intent.putExtra("loan_id",loan_id);
                    context.startActivity(intent);

                }


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
        public TextView amountPaid;
        public TextView amountRemaining;
        public TextView totalDue;
        public ProgressBar progressBar;
        public Button fc;
        public CustomViewHolder(View itemView) {
            super(itemView);

            loanType = (TextView) itemView.findViewById(R.id.loanTypef);
            loanAmount = (TextView) itemView.findViewById(R.id.loanAmountf);
            amountPaid = (TextView) itemView.findViewById(R.id.amountPaidf);
            amountRemaining = (TextView) itemView.findViewById(R.id.amountRemainingf);
            totalDue = (TextView) itemView.findViewById(R.id.totalDuef);
            fc = (Button) itemView.findViewById(R.id.idRequestFC);
        }
    }
}
