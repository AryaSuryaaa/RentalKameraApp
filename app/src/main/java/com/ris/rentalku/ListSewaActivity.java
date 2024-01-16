package com.ris.rentalku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListSewaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListSewaAdapter listSewaAdapter;
    private List<DatabaseHelper.SewaItem> sewaItemList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sewa);

        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sewaItemList = new ArrayList<>();
        // Fetch data from the SQLite database using your DatabaseHelper and add it to sewaItemList
        sewaItemList = dbHelper.getAllSewaItems();

        listSewaAdapter = new ListSewaAdapter(this, sewaItemList);
        recyclerView.setAdapter(listSewaAdapter);
    }
}