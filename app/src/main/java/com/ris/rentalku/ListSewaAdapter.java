package com.ris.rentalku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListSewaAdapter extends RecyclerView.Adapter<ListSewaAdapter.ViewHolder> {

    private Context context;
    private List<DatabaseHelper.SewaItem> sewaItemList;

    public ListSewaAdapter(Context context, List<DatabaseHelper.SewaItem> sewaItemList) {
        this.context = context;
        this.sewaItemList = sewaItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_sewa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatabaseHelper.SewaItem sewaItem = sewaItemList.get(position);

        holder.idTextView.setText(String.valueOf(sewaItem.getId()));
        holder.namaPenyewaTextView.setText(sewaItem.getNamaPenyewa());
        holder.jenisKameraTextView.setText(sewaItem.getJenisKamera());
        holder.statusTextView.setText(sewaItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return sewaItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, namaPenyewaTextView, jenisKameraTextView, statusTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.id_sewa);
            namaPenyewaTextView = itemView.findViewById(R.id.nama_penyewa);
            jenisKameraTextView = itemView.findViewById(R.id.jenis_kamera);
            statusTextView = itemView.findViewById(R.id.status_sewa);
        }
    }
}
