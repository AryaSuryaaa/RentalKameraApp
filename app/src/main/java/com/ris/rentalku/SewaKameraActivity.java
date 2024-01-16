package com.ris.rentalku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SewaKameraActivity extends AppCompatActivity {
    int harga_sewa_kamera,jml_lmsw,ttl_hargasewa,jml_uang;
    String s_nama;

    Spinner ad_listkamera;
    TextView harga_kamera;
    EditText lama_sewa,uangbayar,nama_penyewa;


    String list_kamera[] = {"Kamera DSLR","Kamera Mirrorless","Kamera Vlog","Webcam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_kamera);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        ad_listkamera = findViewById(R.id.ad_listkamera);
        harga_kamera = findViewById(R.id.harga_kamera);
        lama_sewa = findViewById(R.id.lama_sewa);
        uangbayar = findViewById(R.id.uangbayar);

        ArrayAdapter ad_kamera = new ArrayAdapter(SewaKameraActivity.this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, list_kamera);
        ad_listkamera.setAdapter(ad_kamera);


    }

    public void tmbl_OK (View view) {
        jml_lmsw= Integer.parseInt(lama_sewa.getText().toString());
        s_nama = nama_penyewa.getText().toString();


        if(ad_listkamera.getSelectedItem().toString()=="Kamera DSLR"){
            harga_sewa_kamera = 300000;
            ttl_hargasewa = jml_lmsw * harga_sewa_kamera;
            harga_kamera.setText(Integer.toString(ttl_hargasewa));
        } else if (ad_listkamera.getSelectedItem().toString() == "Kamera Mirrorless"){
            harga_sewa_kamera = 350000;
            ttl_hargasewa = jml_lmsw * harga_sewa_kamera;
            harga_kamera.setText(Integer.toString(ttl_hargasewa));
        } else if (ad_listkamera.getSelectedItem().toString() == "Kamera Vlog"){
            harga_sewa_kamera = 600000;
            ttl_hargasewa = jml_lmsw * harga_sewa_kamera;
            harga_kamera.setText(Integer.toString(ttl_hargasewa));
        } else if (ad_listkamera.getSelectedItem().toString() == "Webcam"){
            harga_sewa_kamera = 700000;
            ttl_hargasewa = jml_lmsw * harga_sewa_kamera;
            harga_kamera.setText(Integer.toString(ttl_hargasewa));
        }

    }

    public void tombol_sewa2(View view) {
        jml_uang = Integer.parseInt(uangbayar.getText().toString());
        if (jml_uang < ttl_hargasewa) {
            Toast.makeText(this, "Uang Kurang", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(SewaKameraActivity.this,StrukActivity.class);

            intent.putExtra("nama",s_nama);
            intent.putExtra("kamera", ad_listkamera.getSelectedItem().toString());
            intent.putExtra("lama",jml_lmsw);
            intent.putExtra("total",ttl_hargasewa);
            intent.putExtra("uang",jml_uang);
            intent.putExtra("kembalian",jml_uang-ttl_hargasewa);

            startActivity(intent);
        }
    }

    public void hal_utama(View view) {
        Intent intent = new Intent(SewaKameraActivity.this,MainActivity.class);
        startActivity(intent);
    }
}