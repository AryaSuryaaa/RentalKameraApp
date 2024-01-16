package com.ris.rentalku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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

    // SQL statement untuk membuat tabel sewa
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

    // mendapatkan jummlah row yang ada
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

    // melihat data yang tersimpan
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

    public class SewaItem {
        private int id;
        private String namaPenyewa, jenisKamera, hargaSewa, uangBayar, status;

        // Constructors
        public SewaItem() { }

        public SewaItem(int id, String namaPenyewa, String jenisKamera, String status, String hargaSewa, String uangBayar) {
            this.id = id;
            this.namaPenyewa = namaPenyewa;
            this.jenisKamera = jenisKamera;
            this.hargaSewa = hargaSewa;
            this.uangBayar = uangBayar;
            this.status = status;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNamaPenyewa() {
            return namaPenyewa;
        }

        public void setNamaPenyewa(String namaPenyewa) {
            this.namaPenyewa = namaPenyewa;
        }

        public String getJenisKamera() {
            return jenisKamera;
        }

        public String getHargaSewa() {
            return hargaSewa;
        }

        public String getUangBayar() {
            return uangBayar;
        }

        public void setJenisKamera(String jenisKamera) {
            this.jenisKamera = jenisKamera;
        }

        public void setHargaSewa(String hargaSewa) {
            this.hargaSewa = hargaSewa;
        }

        public void setUangBayar(String uangBayar) {
            this.uangBayar = uangBayar;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public List<SewaItem> getAllSewaItems() {
        List<SewaItem> sewaItemList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(
                    TABLE_SEWA,
                    new String[]{COLUMN_ID, COLUMN_NAMA_PENYEWA, COLUMN_NAMA_KAMERA, COLUMN_STATUS},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    SewaItem sewaItem = createSewaItemFromCursor(cursor);
                    if (sewaItem != null) {
                        sewaItemList.add(sewaItem);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return sewaItemList;
    }

    private SewaItem createSewaItemFromCursor(Cursor cursor) {
        SewaItem sewaItem = null;

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int namaPenyewaIndex = cursor.getColumnIndex(COLUMN_NAMA_PENYEWA);
        int jenisKameraIndex = cursor.getColumnIndex(COLUMN_NAMA_KAMERA);
        int statusIndex = cursor.getColumnIndex(COLUMN_STATUS);

        if (idIndex >= 0 && namaPenyewaIndex >= 0 && jenisKameraIndex >= 0 && statusIndex >= 0) {
            sewaItem = new SewaItem();
            sewaItem.setId(cursor.getInt(idIndex));
            sewaItem.setNamaPenyewa(cursor.getString(namaPenyewaIndex));
            sewaItem.setJenisKamera(cursor.getString(jenisKameraIndex));
            sewaItem.setStatus(cursor.getString(statusIndex));
        }

        return sewaItem;
    }

    public SewaItem getSewaItemById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        SewaItem sewaItem = null;

        try {
            cursor = db.query(
                    TABLE_SEWA,
                    new String[]{COLUMN_ID, COLUMN_NAMA_PENYEWA, COLUMN_NAMA_KAMERA, COLUMN_HARGA, COLUMN_UANG_BAYAR, COLUMN_STATUS},
                    COLUMN_ID + " = ?",
                    new String[]{id},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                sewaItem = new SewaItem();

                int namaPenyewaIndex = cursor.getColumnIndex(COLUMN_NAMA_PENYEWA);
                if (namaPenyewaIndex != -1) {
                    sewaItem.setNamaPenyewa(cursor.getString(namaPenyewaIndex));
                }

                int jenisKameraIndex = cursor.getColumnIndex(COLUMN_NAMA_KAMERA);
                if (jenisKameraIndex != -1) {
                    sewaItem.setJenisKamera(cursor.getString(jenisKameraIndex));
                }

                int hargaSewaIndex = cursor.getColumnIndex(COLUMN_HARGA);
                if (hargaSewaIndex != -1) {
                    sewaItem.setHargaSewa(cursor.getString(hargaSewaIndex));
                }

                int uangBayarIndex = cursor.getColumnIndex(COLUMN_UANG_BAYAR);
                if (uangBayarIndex != -1) {
                    sewaItem.setUangBayar(cursor.getString(uangBayarIndex));
                }

                int statusIndex = cursor.getColumnIndex(COLUMN_STATUS);
                if (statusIndex != -1) {
                    sewaItem.setStatus(cursor.getString(statusIndex));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return sewaItem;
    }

    public void updateStatus(String id, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put(COLUMN_STATUS, newStatus);

            db.update(
                    TABLE_SEWA,
                    values,
                    COLUMN_ID + " = ?",
                    new String[]{id}
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void resetData() {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(TABLE_SEWA, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    // menghapus dan memperbarui data tabel jika ada data baru
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEWA);
        onCreate(db);
    }
}
