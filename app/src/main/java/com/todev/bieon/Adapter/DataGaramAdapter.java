package com.todev.bieon.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.todev.bieon.Helper.SQLiteDataGaram;
import com.todev.bieon.Model.DataGaram;
import com.todev.bieon.R;

import java.util.ArrayList;

public class DataGaramAdapter extends RecyclerView.Adapter<DataGaramAdapter.ViewHolder> {

    private ArrayList<DataGaram> dataList;
    private Context context;

    public DataGaramAdapter(ArrayList<DataGaram> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataGaramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_design, viewGroup, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String Noseri = dataList.get(position).getNoseri();//Mengambil data (Nama) sesuai dengan posisi yang telah ditentukan
        final String Nacl = dataList.get(position).getNacl();//Mengambil data (Jurusan) sesuai dengan posisi yang telah ditentukan
        final String Whiteness = dataList.get(position).getWhiteness();//Mengambil data (NIM) sesuai dengan posisi yang telah ditentukan
        final String Watercontent= dataList.get(position).getWatercontent();
        holder.Noseri.setText(Noseri);
        holder.Nacl.setText(Nacl);
        holder.Whiteness.setText(Whiteness);
        holder.Watercontent.setText(Watercontent);

        //Mengimplementasikan Menu Popup pada Overflow (ImageButton)
        holder.Overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Membuat Instance/Objek dari PopupMenu
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete:
                                //Menghapus Data Dari Database
                                SQLiteDataGaram getDatabase = new SQLiteDataGaram(view.getContext());
                                SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                                //Menentukan di mana bagian kueri yang akan dipilih
                                String selection = SQLiteDataGaram.kolom.NoSeri + " LIKE ?";
                                //Menentukan Nama Dari Data Yang Ingin Dihapus
                                String[] selectionArgs = {Noseri};
                                DeleteData.delete(SQLiteDataGaram.kolom.NamaTabel, selection, selectionArgs);

                                //Menghapus Data pada List dari Posisi Tertentu
                                String position2 = String.valueOf(Noseri.indexOf(Noseri));
                                dataList.remove(position);
                                notifyItemRemoved(position);
                                if (position2 == null) {
                                    notifyItemRangeChanged(Integer.parseInt(position2), dataList.size());
                                }
                                break;

                           /* case R.id.update:
                                Intent dataForm = new Intent(view.getContext(), UpdateActivity.class);
                                dataForm.putExtra("SendNIM", NIM);
                                context.startActivity(dataForm);
                                ((Activity)context).finish();
                                break;

                            case R.id.create:
                                Intent data = new Intent(view.getContext(), MainActivity.class);
                                context.startActivity(data);
                                ((Activity)context).finish();
                                break;*/
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return dataList.size();
    }


    public void setFilter(ArrayList<DataGaram> filterList){
        dataList = new ArrayList<>();
        dataList.addAll(filterList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Noseri, Nacl, Whiteness, Watercontent;
        private ImageButton Overflow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            Noseri = itemView.findViewById(R.id.noDeviceAlat);
            Nacl = itemView.findViewById(R.id.nacl);
            Whiteness = itemView.findViewById(R.id.whiteness);
            Watercontent = itemView.findViewById(R.id.kadarair);
            Overflow = itemView.findViewById(R.id.overflow);
        }
    }
}
