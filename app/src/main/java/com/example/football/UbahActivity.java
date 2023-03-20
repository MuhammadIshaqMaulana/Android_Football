package com.example.football;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent varIntent = getIntent();
        String id = varIntent.getStringExtra("varID");
        String nama = varIntent.getStringExtra("varNama");
        String nomor = varIntent.getStringExtra("varNomor");
        String klub = varIntent.getStringExtra("varKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getKlub;

                getNama = etNama.getText().toString();
                getNomor = etNomor.getText().toString();
                getKlub = etKlub.getText().toString();

                if(getNama.trim().equals("")){
                    etNama.setError("Nama Player Tidak boleh kosong");
                }
                else if(getNomor.trim().equals("")){
                    etNomor.setError("Nomor Player Tidak boleh kosong");
                }
                else if(getKlub.trim().equals("")){
                    etKlub.setError("Klub Player Tidak boleh kosong");
                }
                else {
                    long eks = myDB.ubahPlayer(id, getNama, getNomor, getKlub);
                    if (eks == -1){
                        Toast.makeText(UbahActivity.this, "Gagal Mengubah data", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(UbahActivity.this, "Berhasil Mengubah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}