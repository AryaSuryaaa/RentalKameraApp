package com.ris.rentalku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StrukActivity extends AppCompatActivity {
    TextView nama_penyewa, jenis_kamera, lama_sewa, total, uang_bayar, uang_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        jenis_kamera = findViewById(R.id.jenis_kamera);
        lama_sewa = findViewById(R.id.lama_sewa);
        total = findViewById(R.id.total);
        uang_bayar = findViewById(R.id.uang_bayar);
        uang_kembali = findViewById(R.id.uang_kembali);

        nama_penyewa.setText(getIntent().getStringExtra("nama"));
        jenis_kamera.setText(getIntent().getStringExtra("kamera"));
        lama_sewa.setText(String.valueOf(getIntent().getIntExtra("lama",0)));
        total.setText(String.valueOf(getIntent().getIntExtra("total",0)));
        uang_bayar.setText(String.valueOf(getIntent().getIntExtra("uang",0)));
        uang_kembali.setText(String.valueOf(getIntent().getIntExtra("kembalian",0)));
    }

    public void hal_utama(View view) {
        Intent intent =new Intent(StrukActivity.this, MainActivity.class);
        startActivity(intent);
    }
}