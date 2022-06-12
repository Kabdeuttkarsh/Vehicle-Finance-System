package com.solutionstouch.omsaifinance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.solutionstouch.omsaifinance.adapter.TableViewAdapter;
import com.solutionstouch.omsaifinance.model.PaymentModal;

import java.util.ArrayList;
import java.util.List;

public class Repayment_Schedule extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_repayment);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        TableViewAdapter adapter = new TableViewAdapter(paymentlist());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    private List<PaymentModal> paymentlist() {
        List<PaymentModal> movieList = new ArrayList<>();
        // src Wikipedia
        movieList.add(new PaymentModal(1, "Pirates of the Caribbean: On Stranger Tides", 2011, 378));
        movieList.add(new PaymentModal(2, "Avengers: Age of Ultron", 2015, 365));
        movieList.add(new PaymentModal(3, "Avengers: Infinity War", 2018, 316));
        movieList.add(new PaymentModal(4, "Pirates of the Caribbean: At World's End", 2007, 300));
        movieList.add(new PaymentModal(5, "Justice League", 2017, 300));
        movieList.add(new PaymentModal(6, "Solo: A Star Wars Story", 2018, 275));
        movieList.add(new PaymentModal(7, "John Carter", 2012, 264));
        movieList.add(new PaymentModal(8, "Batman v Superman: Dawn of Justice", 2016, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(9, "Star Wars: The Last Jedi", 2017, 263));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));
        movieList.add(new PaymentModal(10, "Tangled", 2010, 260));

        return movieList;
    }
}