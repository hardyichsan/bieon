package com.todev.bieon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.todev.bieon.BottomBars.AmbilDataFragment;
import com.todev.bieon.BottomBars.HomeFragment;
import com.todev.bieon.BottomBars.LihatDataFragment;
import com.todev.bieon.BottomBars.PengaturanFragment;
import com.todev.bieon.BottomBars.ProfilFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeNav:
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, homeFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.ambilDataNav:
                    AmbilDataFragment ambilDataFragment = new AmbilDataFragment();
                    FragmentTransaction fragmentDashTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentDashTransaction.replace(R.id.content, ambilDataFragment);
                    fragmentDashTransaction.commit();
                    return true;
                case R.id.lihatDataNav:
                    LihatDataFragment lihatDataFragment = new LihatDataFragment();
                    FragmentTransaction l = getSupportFragmentManager().beginTransaction();
                    l.replace(R.id.content, lihatDataFragment);
                    l.commit();
                    return true;
                case R.id.pengaturanNav:
                    PengaturanFragment pengaturanFragment = new PengaturanFragment();
                    FragmentTransaction u = getSupportFragmentManager().beginTransaction();
                    u.replace(R.id.content, pengaturanFragment);
                    u.commit();
                    return true;
                case R.id.profilNav:
                    ProfilFragment profilFragment = new ProfilFragment();
                    FragmentTransaction p = getSupportFragmentManager().beginTransaction();
                    p.replace(R.id.content, profilFragment);
                    p.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, homeFragment);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        //set pesan dari dialog
        alertDialogBuilder
                .setMessage("Apakah yakin ingin keluar dari aplikasi ?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //jika tombol ini di klik, maka akan menutup activity ini
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //jika tombol ini diklik, akan menutup dialog
                        //dan tidak terjadi apa"
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}
