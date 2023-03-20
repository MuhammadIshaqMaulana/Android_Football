package com.example.football;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnTambah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, nomor, klub;

                nama = etNama.getText().toString();
                nomor = etNomor.getText().toString();
                klub = etKlub.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("Nama Player Tidak boleh kosong");
                }
                else if(nomor.trim().equals("")){
                    etNomor.setError("Nomor Player Tidak boleh kosong");
                }
                else if(klub.trim().equals("")){
                    etKlub.setError("Klub Player Tidak boleh kosong");
                }
                else {
                    long eks = myDB.tambahPlayer(nama, nomor, klub);
                    if(eks == -1){
                        Toast.makeText(TambahActivity.this, "Gagal Menambah Data Player", Toast.LENGTH_SHORT).show();
                                etNama.requestFocus();
                    }
                    else {
                        Toast.makeText(TambahActivity.this,
                                "Sukses Menambah Data Player", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}