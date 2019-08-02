package com.todev.bieon.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.todev.bieon.R;

import static com.todev.bieon.API.ApiClient.BASE_URL_IMAGE;

public class DetailActivity extends AppCompatActivity {

    // Deklarasi
    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis, tvJudul, isiberita;
    WebView wvKontenBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi
        ivGambarBerita = (ImageView) findViewById(R.id.ivGambarBerita);
        tvTglTerbit = (TextView) findViewById(R.id.tvTglTerbit);
        tvJudul = (TextView) findViewById(R.id.tvJudulBeritaDetail);
        wvKontenBerita = (WebView) findViewById(R.id.wvKontenBerita);

        showDetailBerita();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showDetailBerita() {
        // Tangkap data dari intent
        String judul = getIntent().getStringExtra("judul");
        String foto = getIntent().getStringExtra("foto");
        String tgl_dibuat = getIntent().getStringExtra("tgl_dibuat");
        String isi = getIntent().getStringExtra("isi");


        // Set judul actionbar / toolbar
        tvJudul.setText(judul);

        // Set ke widget
        tvTglTerbit.setText(tgl_dibuat);
        // Untuk gambar berita
        Picasso.with(this).load(BASE_URL_IMAGE + foto).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView
        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(isi, "text/html; charset=utf-8", "UTF-8");
    }
}
