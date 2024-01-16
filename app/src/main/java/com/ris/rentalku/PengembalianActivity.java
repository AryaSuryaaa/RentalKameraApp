package com.ris.rentalku;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PengembalianActivity extends AppCompatActivity {

    private EditText idSewaEditText;
    private TextView namaPenyewaTextView, jenisKameraTextView, hargaSewaTextView, uangBayarTextView;
    private Button kembalikanButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengembalian);

        idSewaEditText = findViewById(R.id.id_sewa);
        namaPenyewaTextView = findViewById(R.id.nama_penyewa);
        jenisKameraTextView = findViewById(R.id.jenis_kamera);
        hargaSewaTextView = findViewById(R.id.harga_sewa);
        uangBayarTextView = findViewById(R.id.uang_bayar);
        kembalikanButton = findViewById(R.id.kembalikan);

        dbHelper = new DatabaseHelper(this);
    }

    public void tombol_cek_id(View view) {
        // Get the entered ID
        String enteredId = idSewaEditText.getText().toString();

        // Retrieve data based on the entered ID from the database
        DatabaseHelper.SewaItem sewaItem = dbHelper.getSewaItemById(enteredId);

        if (sewaItem != null) {
            // Populate the TextViews with retrieved data
            namaPenyewaTextView.setText(sewaItem.getNamaPenyewa());
            jenisKameraTextView.setText(sewaItem.getJenisKamera());
            hargaSewaTextView.setText("Rp. " + sewaItem.getHargaSewa());
            uangBayarTextView.setText("Rp. " + sewaItem.getUangBayar());

            // Check the status and enable/disable the button accordingly
            if ("sewa".equals(sewaItem.getStatus())) {
                kembalikanButton.setEnabled(true);
            } else {
                kembalikanButton.setEnabled(false);
                Toast.makeText(this, "Sudah dikembalikan", Toast.LENGTH_SHORT).show();
            }
        } else {
            namaPenyewaTextView.setText("");
            jenisKameraTextView.setText("");
            hargaSewaTextView.setText("");
            uangBayarTextView.setText("");
            kembalikanButton.setEnabled(false);
            // Handle the case where the ID is not found in the database
            Toast.makeText(this, "ID not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void tombol_sewa2(View view) {
        // Update the status to "kembali" in the database
        String enteredId = idSewaEditText.getText().toString();
        dbHelper.updateStatus(enteredId, "kembali");

        // Disable the button after updating the status
        kembalikanButton.setEnabled(false);
        Toast.makeText(this, "Barang telah dikembalikan", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void hal_utama(View view) {
        // Handle the button click to go back to the main activity
        finish();
    }
}
