package com.ris.rentalku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarMobilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mobil);
    }

    public void hal_sewa(View view) {
        Intent intent =new Intent(DaftarMobilActivity.this, SewaMobilActivity.class);
        startActivity(intent);
    }
}