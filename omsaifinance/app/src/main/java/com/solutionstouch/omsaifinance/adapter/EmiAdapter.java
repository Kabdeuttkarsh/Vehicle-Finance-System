package com.solutionstouch.omsaifinance.adapter;

import static android.graphics.Color.RED;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.PayActivity;
import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.Transaction;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class EmiAdapter extends RecyclerView.Adapter<EmiAdapter.ViewHolder>{

    public ArrayList<Transaction> emiSchedule;
    public Context context;
    public LayoutInflater inflater;
    public EmiAdapter(Context context, java.util.ArrayList<Transaction> emiSchedule) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.emiSchedule = emiSchedule;
    }
    @Override
    public EmiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_emi_details, parent, false);
        return new EmiAdapter.ViewHolder(itemLayoutView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final EmiAdapter.ViewHolder viewHolder, int position) {
        Transaction transaction = emiSchedule.get(position);
        viewHolder.srno.setText(String.valueOf(position + 1));
        viewHolder.date.setText(transaction.getEmi_date());
        viewHolder.amount.setText(transaction.getEmi_amount());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatter);
        String date2 = transaction.getEmi_date();

    if (transaction.getIs_emi_paid().equals("Y")) {

        viewHolder.status.setText("Paid");
        } else {


        viewHolder.status.setTextColor(RED);
            viewHolder.status.setText("Pay");
            viewHolder.status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Date date = new Date();
                    Intent intent = new Intent(context, PayActivity.class);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    intent.putExtra("emi_id", transaction.getEmi_id());
                    intent.putExtra("loan_id", transaction.getLoan_id());
                    intent.putExtra("borrower_id", transaction.getUser_id());
                    intent.putExtra("due_amount", transaction.getEmi_due_amount());
                    intent.putExtra("emi_due_months", transaction.getPending_emi_months());
                    intent.putExtra("emi_amount", transaction.getEmi_amount());
                    intent.putExtra("financer_id", transaction.getFinancer_id());
                    intent.putExtra("date", formatter.format(date));
                    intent.putExtra("mode", "Online");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
    @Override
    public int getItemCount() {
        return emiSchedule.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView srno, date, amount ;
        Button status;
        CardView cv;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            srno = itemLayoutView.findViewById(R.id.emisrNo);
            date = itemLayoutView.findViewById(R.id.emiDate);
            amount = itemLayoutView.findViewById(R.id.emiAmount);

            status = itemLayoutView.findViewById(R.id.emiStatus);
            cv = (CardView) itemLayoutView.findViewById(R.id.cvEmi);

        }
    }
}
