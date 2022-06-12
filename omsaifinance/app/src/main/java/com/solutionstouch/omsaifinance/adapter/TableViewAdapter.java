package com.solutionstouch.omsaifinance.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.PaymentModal;

import java.util.List;

public class TableViewAdapter extends RecyclerView.Adapter {

    List<PaymentModal> paymentlist;

    public TableViewAdapter(List<PaymentModal> paymentlist) {
        this.paymentlist = paymentlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.table_list_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.txtInstallment_No.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.txtBalance.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.txtInstallment_No.setText("Installment_No");
            rowViewHolder.txtMovieName.setText("Name");
            rowViewHolder.txtYear.setText("Year");
            rowViewHolder.txtBalance.setText("Balance");
        } else {
            PaymentModal modal = paymentlist.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.txtInstallment_No.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtMovieName.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtYear.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.txtBalance.setBackgroundResource(R.drawable.table_content_cell_bg);

            rowViewHolder.txtInstallment_No.setText(modal.getInstallment_No()+"");
            rowViewHolder.txtMovieName.setText(modal.getMovieName());
            rowViewHolder.txtYear.setText(modal.getYear()+"");
            rowViewHolder.txtBalance.setText(modal.getBalance()+"");
        }
    }

    @Override
    public int getItemCount() {
        return paymentlist.size()+1; // one more to add header row
    }


    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtInstallment_No;
        protected TextView txtMovieName;
        protected TextView txtYear;
        protected TextView txtBalance;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtInstallment_No = itemView.findViewById(R.id.txtInstallment_No);
            txtMovieName = itemView.findViewById(R.id.txtMovieName);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtBalance = itemView.findViewById(R.id.txtBalance);
        }
    }
}
