package com.ris.rentalku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ListCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kamera);
    }

    public void halSewa(View view) {
        Intent intent = new Intent(ListCameraActivity.this, SewaKameraActivity.class);
        startActivity(intent);
    }
}