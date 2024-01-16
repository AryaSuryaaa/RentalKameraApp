package com.ris.rentalku;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    TextView harga_kamera, id_sewa;
    EditText lama_sewa,uangbayar,nama_penyewa;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    int angka_id = 0;


    String list_kamera[] = {"Kamera DSLR","Kamera Mirrorless","Kamera Vlog","Webcam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sewa_kamera);
        id_sewa = findViewById(R.id.id_sewa);
        nama_penyewa = findViewById(R.id.nama_penyewa);
        ad_listkamera = findViewById(R.id.ad_listkamera);
        harga_kamera = findViewById(R.id.harga_kamera);
        lama_sewa = findViewById(R.id.lama_sewa);
        uangbayar = findViewById(R.id.uangbayar);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        int rowCount = dbHelper.getRowCount() + 1;
        angka_id = rowCount;
        id_sewa.setText(Integer.toString(rowCount));

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
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_ID, angka_id);
            values.put(DatabaseHelper.COLUMN_NAMA_PENYEWA, s_nama);
            values.put(DatabaseHelper.COLUMN_NAMA_KAMERA, ad_listkamera.getSelectedItem().toString());
            values.put(DatabaseHelper.COLUMN_HARGA, ttl_hargasewa);
            values.put(DatabaseHelper.COLUMN_UANG_BAYAR, jml_uang);
            values.put(DatabaseHelper.COLUMN_STATUS, "sewa");

            long newRowId = db.insert(DatabaseHelper.TABLE_SEWA, null, values);

            if (newRowId != -1) {
                dbHelper.logAllData();

                Intent intent = new Intent(SewaKameraActivity.this, StrukActivity.class);

                intent.putExtra("id", id_sewa.toString());
                intent.putExtra("nama",s_nama);
                intent.putExtra("kamera", ad_listkamera.getSelectedItem().toString());
                intent.putExtra("lama",jml_lmsw);
                intent.putExtra("total",ttl_hargasewa);
                intent.putExtra("uang",jml_uang);
                intent.putExtra("kembalian",jml_uang-ttl_hargasewa);

                startActivity(intent);

            } else {
                Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void hal_utama(View view) {
        Intent intent = new Intent(SewaKameraActivity.this,MainActivity.class);
        startActivity(intent);
    }
}