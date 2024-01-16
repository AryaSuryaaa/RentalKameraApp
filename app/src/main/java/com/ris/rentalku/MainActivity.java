package com.ris.rentalku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Tombol_info(View view) {
        Intent intent =new Intent(MainActivity.this, ListCameraActivity.class);
        startActivity(intent);
    }

    public void tombol_sewa(View view) {
        Intent intent =new Intent(MainActivity.this, SewaKameraActivity.class);
        startActivity(intent);
    }

    public void tombol_pengembalian(View view) {
        Intent intent =new Intent(MainActivity.this, PengembalianActivity.class);
        startActivity(intent);
    }

    public void tombol_list(View view) {
        Intent intent =new Intent(MainActivity.this, ListSewaActivity.class);
        startActivity(intent);
    }
}