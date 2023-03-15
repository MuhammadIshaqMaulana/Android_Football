package com.example.football;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer>{

    private Context ctx;
    private ArrayList arrID, arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(Context ctx, ArrayList arrID, ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub) {
        this.ctx = ctx;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrKlub = arrKlub;
        this.arrID = arrID;
    }

    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_player, parent, false);
        return new ViewHolderPlayer(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position) {
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvNomor.setText(arrNomor.get(position).toString());
        holder.tvKlub.setText(arrKlub.get(position).toString());
        holder.tvID.setText(arrID.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderPlayer extends RecyclerView.ViewHolder{
        private TextView tvID, tvNama, tvNomor, tvKlub;
        public ViewHolderPlayer(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvKlub = itemView.findViewById(R.id.tv_klub);
            tvID = itemView.findViewById(R.id.tv_id);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Pilih perintah yang anda inginkan");
                    pesan.setCancelable(true);

                    pesan.setPositiveButton("ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                            long eksekusi = myDB.hapusPlayer(tvID.getText().toString());
                            if (eksekusi == -1) {
                                Toast.makeText(ctx, "Gagal menghapus Data!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ctx, "Sukses Menghapus Data!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }
    }
}
