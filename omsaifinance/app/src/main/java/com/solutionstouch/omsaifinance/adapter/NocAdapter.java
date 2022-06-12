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

import com.solutionstouch.omsaifinance.NocActivity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.Loan;

import java.util.ArrayList;

public class NocAdapter  extends RecyclerView.Adapter<NocAdapter.CustomViewHolder>{


    private Context context;
    private ArrayList<Loan> loans;
    private LayoutInflater inflater;
    private String loan_id;
    public String remain_amount;

    public NocAdapter(Context context, ArrayList<Loan> loans) {
        this.context = context;
        this.loans = loans;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.noc_view_page, parent, false);
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

        holder.noc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(remain_amount.equals("0")){
//                    Toast.makeText(context.getApplicationContext(), loan_id, Toast.LENGTH_SHORT).show();

                Intent intent= new Intent(context, NocActivity.class);
                intent.putExtra("loan_id",loan_id);
                context.startActivity(intent);




                }
                else{
                    Toast.makeText(context.getApplicationContext(), "Not able applied noc this time, untill paid all amount.", Toast.LENGTH_SHORT).show();
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
        public Button noc;
        public CustomViewHolder(View itemView) {
            super(itemView);

            loanType = (TextView) itemView.findViewById(R.id.loanTypen);
            loanAmount = (TextView) itemView.findViewById(R.id.loanAmountn);
            amountPaid = (TextView) itemView.findViewById(R.id.amountPaidn);
            amountRemaining = (TextView) itemView.findViewById(R.id.amountRemainingn);
            totalDue = (TextView) itemView.findViewById(R.id.totalDuen);
            noc = (Button) itemView.findViewById(R.id.idRequestNoc);
        }
    }
}
