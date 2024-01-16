package com.ris.rentalku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        // Mendapatkan data dari SQLite dan memasukkannya ke adapter
        sewaItemList = dbHelper.getAllSewaItems();

        listSewaAdapter = new ListSewaAdapter(this, sewaItemList);
        recyclerView.setAdapter(listSewaAdapter);
    }

    public void tombol_reset_data(View view) {
        // Menampilkan konfirmasi sebelum reset
        showConfirmationDialog();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Reset");
        builder.setMessage("Apakah Anda yakin ingin mereset data?");

        builder.setPositiveButton("Ya", (dialog, which) -> {
            dbHelper.resetData();

            finish();
            Toast.makeText(ListSewaActivity.this, "Data reset berhasil", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Tidak", (dialog, which) -> { });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}