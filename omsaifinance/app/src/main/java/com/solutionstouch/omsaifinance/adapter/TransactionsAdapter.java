package com.solutionstouch.omsaifinance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.Transaction;

import java.util.ArrayList;

public class TransactionsAdapter  extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder>{

    public ArrayList<Transaction> transactions;
    public Context context;
   public LayoutInflater inflater;
    public TransactionsAdapter(Context context, ArrayList<Transaction> transactions) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.transactions = transactions;
    }
        @Override
        public TransactionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_loan_detail, parent, false);
            return new TransactionsAdapter.ViewHolder(itemLayoutView);
        }
    @Override
    public void onBindViewHolder(final TransactionsAdapter.ViewHolder viewHolder, int position) {
        Transaction transaction = transactions.get(position);
        viewHolder.tenure.setText(transaction.getCollection_date());
        viewHolder.emi.setText(transaction.getPayment_type());
        viewHolder.total.setText(transaction.getCollection_amount());
        viewHolder.due.setText(transaction.getCollection_due_amount());
        viewHolder.grand.setText(transaction.getGrand_total());
        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tenure, emi, total, grand, due;
        CardView cv;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tenure = (TextView) itemLayoutView.findViewById(R.id.tv_tenure);
            emi = (TextView) itemLayoutView.findViewById(R.id.tv_emi);
            total = (TextView) itemLayoutView.findViewById(R.id.tv_total);
            cv = (CardView) itemLayoutView.findViewById(R.id.cv);
            grand = (TextView) itemLayoutView.findViewById(R.id.tv_grand);
            due = (TextView) itemLayoutView.findViewById(R.id.tv_due);
        }
    }

    }

