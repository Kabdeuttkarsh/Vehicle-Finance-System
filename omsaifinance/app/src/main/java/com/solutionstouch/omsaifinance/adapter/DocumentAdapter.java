package com.solutionstouch.omsaifinance.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.R;
import com.solutionstouch.omsaifinance.model.Documents;

import java.util.ArrayList;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.CustomViewHolder>{

    private Context context;
    private ArrayList<Documents> documents;
    private LayoutInflater inflater;

    public DocumentAdapter(Context context,ArrayList<Documents> documents){
        this.context = context;
        this.documents = documents;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.doc_view_page,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Documents documents1 = documents.get(position);
        if (documents1.getDocument_type_id().equals("1")) {
            holder.docType.setText("AADHAR CARD");
        }else if (documents1.getDocument_type_id().equals("2")) {
            holder.docType.setText("PAN CARD");
        }
        else if (documents1.getDocument_type_id().equals("3")) {
            holder.docType.setText("VOTER ID");
        }
        else if (documents1.getDocument_type_id().equals("4")) {
            holder.docType.setText("PASSPORT");
        }
        else if (documents1.getDocument_type_id().equals("5")) {
            holder.docType.setText("DRIVING LICENCE");
        }
        else if (documents1.getDocument_type_id().equals("6")) {
            holder.docType.setText("PASSBOOK");
        }
        else if (documents1.getDocument_type_id().equals("7")) {
            holder.docType.setText("OTHER");
        }

        holder.docFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(documents1.getFront())){
                    Toast.makeText(context.getApplicationContext(), "No document", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(context,DocumentDisplay.class);
                    Log.d("pressed",documents1.getFront());
                    intent.putExtra("document",documents1.getFront());
                    context.startActivity(intent);
                }

            }
        });

        holder.docBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(documents1.getBack())){
                    Toast.makeText(context.getApplicationContext(), "No Document", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(context,DocumentDisplay.class);
                    Log.d("pressed",documents1.getBack());
                    intent.putExtra("document",documents1.getBack());
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return documents.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView docType;
        public TextView docFront;
        public TextView docBack;

        public CustomViewHolder(View itemView) {
            super(itemView);
            docType = (TextView) itemView.findViewById(R.id.docomentName);
            docFront = (TextView) itemView.findViewById(R.id.docomentFront);
            docBack = (TextView) itemView.findViewById(R.id.docomentBack);
        }
    }
}
