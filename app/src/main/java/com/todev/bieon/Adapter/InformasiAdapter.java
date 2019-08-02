package com.todev.bieon.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todev.bieon.Activity.DetailActivity;
import com.todev.bieon.Model.DataInformasi;
import com.todev.bieon.R;

import java.util.ArrayList;

import static com.todev.bieon.API.ApiClient.BASE_URL_IMAGE;

public class InformasiAdapter extends BaseAdapter {

    Activity context;
    private ArrayList<DataInformasi> item;

    public InformasiAdapter(Activity context, ArrayList<DataInformasi> item) {
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.card_row, null);


        TextView judul,tgl_dibuat;
        ImageView foto;

        judul = (TextView) view.findViewById(R.id.judul);
        tgl_dibuat = (TextView) view.findViewById(R.id.tgl_buat);
        foto = (ImageView) view.findViewById(R.id.foto);

        judul.setText(item.get(i).getJudul());
        tgl_dibuat.setText(item.get(i).getTgl_dibuat());
        Picasso.with(context).load(BASE_URL_IMAGE+item.get(i).getFoto().toString()).into(foto);
        Log.d("URL" , BASE_URL_IMAGE + item.get(i).getFoto().toString());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id_content", item.get(i).getId_content());
                intent.putExtra("judul", item.get(i).getJudul());
                intent.putExtra("isi", item.get(i).getIsi());
                intent.putExtra("tgl_dibuat", item.get(i).getTgl_dibuat());
                intent.putExtra("foto", item.get(i).getFoto());
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
