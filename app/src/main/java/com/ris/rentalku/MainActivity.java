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
        Intent intent =new Intent(MainActivity.this, listCameraActivity.class);
        startActivity(intent);
    }

    public void tombol_sewa(View view) {
        Intent intent =new Intent(MainActivity.this, SewaKameraActivity.class);
        startActivity(intent);
    }

    public void tombol_contact(View view) {
        Intent intent =new Intent(MainActivity.this, PengembalianActivity.class);
        startActivity(intent);
    }
}