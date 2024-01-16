package com.ris.rentalku;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "rentalku.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SEWA = "sewa";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA_PENYEWA = "nama_penyewa";
    public static final String COLUMN_NAMA_KAMERA = "nama_kamera";
    public static final String COLUMN_HARGA = "harga";
    public static final String COLUMN_UANG_BAYAR = "uang_bayar";
    public static final String COLUMN_STATUS = "status";

    // SQL statement to create the table
    private static final String CREATE_TABLE_SEWA =
            "CREATE TABLE " + TABLE_SEWA + " (" +
                    COLUMN_ID + " TEXT, " +
                    COLUMN_NAMA_PENYEWA + " TEXT, " +
                    COLUMN_NAMA_KAMERA + " TEXT, " +
                    COLUMN_HARGA + " INTEGER, " +
                    COLUMN_UANG_BAYAR + " INTEGER, " +
                    COLUMN_STATUS + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SEWA);
    }

    public int getRowCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_SEWA, null);
        int count = 0;

        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0);
            cursor.close();
        }

        return count;
    }

    public void logAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SEWA, null);

        if (cursor != null) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int namaPenyewaIndex = cursor.getColumnIndex(COLUMN_NAMA_PENYEWA);
            int namaKameraIndex = cursor.getColumnIndex(COLUMN_NAMA_KAMERA);
            int hargaIndex = cursor.getColumnIndex(COLUMN_HARGA);
            int uangBayarIndex = cursor.getColumnIndex(COLUMN_UANG_BAYAR);
            int statusIndex = cursor.getColumnIndex(COLUMN_STATUS);

            while (cursor.moveToNext()) {
                int id = (idIndex >= 0) ? cursor.getInt(idIndex) : -1;
                String namaPenyewa = (namaPenyewaIndex >= 0) ? cursor.getString(namaPenyewaIndex) : "";
                String namaKamera = (namaKameraIndex >= 0) ? cursor.getString(namaKameraIndex) : "";
                int harga = (hargaIndex >= 0) ? cursor.getInt(hargaIndex) : -1;
                int uangBayar = (uangBayarIndex >= 0) ? cursor.getInt(uangBayarIndex) : -1;
                String status = (statusIndex >= 0) ? cursor.getString(statusIndex) : "";

                Log.d("DatabaseLog", "ID: " + id + ", Nama Penyewa: " + namaPenyewa +
                        ", Nama Kamera: " + namaKamera + ", Harga: " + harga +
                        ", Uang Bayar: " + uangBayar + ", Status: " + status);
            }

            cursor.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEWA);
        onCreate(db);
    }
}
