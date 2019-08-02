package com.todev.bieon.Activity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.todev.bieon.BluetoothTerminal.KomunikasiActivity;
import com.todev.bieon.BluetoothTerminal.SelectActivity;
import com.todev.bieon.BottomBars.LihatDataFragment;
import com.todev.bieon.Helper.SQLiteDataGaram;
import com.todev.bieon.R;

import org.w3c.dom.Text;

import me.aflak.bluetooth.Bluetooth;

public class TampilHitungActivity extends AppCompatActivity /*implements Bluetooth.CommunicationCallback*/ {


    //Variable Untuk Menyimpan Input Dari Ueer
    private String setNoseri, setNacl, setWhiteness, setWatercontent;

    //Variable Untuk Inisialisasi Database DBMahasiswa
    private SQLiteDataGaram dbData;

    private Bluetooth b;
    Button btnsimpan, btnukurulang;
    ProgressDialog progressDialog;
    String name;
    /*String No_Alat = "", Nacl = "", Whiteness = "" , WaterContent = "";*/
    TextView exnacl, exwhiteness, exwatercontent, exnoDevice, ukur, nmBluetooth, et;
    private ScrollView scrollView;
    private boolean registered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_hitung);


        et = (TextView) findViewById(R.id.text);
        ukur = (TextView) findViewById(R.id.ukurulang);

        exnoDevice = (TextView) findViewById(R.id.noDeviceAlat);
        exnacl = (TextView) findViewById(R.id.nacl);
        exwhiteness = (TextView) findViewById(R.id.whiteness);
        exwatercontent = (TextView) findViewById(R.id.kadarair);

        Intent intent = getIntent();

        /* exnoDevice.setText(intent.getStringExtra("noseri"));*/
        String NoSeri = getIntent().getStringExtra("noseri");
        String NacL = getIntent().getStringExtra("nacl");
        String Whiteness = getIntent().getStringExtra("whiteness");
        String Water = getIntent().getStringExtra("water");

        exnoDevice.setText(NoSeri);
        exnacl.setText(NacL);
        exwhiteness.setText(Whiteness);
        exwatercontent.setText(Water);

      /*  b = new Bluetooth(this);
        b.enableBluetooth();
        b.setCommunicationCallback(this);

        int pos = getIntent().getExtras().getInt("pos");
        name = b.getPairedDevices().get(pos).getName();

//        Display("Connecting...");
        b.connectToDevice(b.getPairedDevices().get(pos));*/
        //Inisialisasi dan Mendapatkan Konteks dari DBMah
        //Inisialisasi dan Mendapatkan Konteks dari DBMahasiswa
        dbData = new SQLiteDataGaram(getBaseContext());
        btnsimpan = (Button) findViewById(R.id.simpan);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

            }
        });

        btnukurulang = (Button) findViewById(R.id.ukurulang);
        btnukurulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("Loading ..."); // Setting Message
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            String emsatu = "1";
                            b.send(emsatu);
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });

      /*  IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        registered=true;*/
    }


    //Berisi Statement-Statement Untuk Mendapatkan Input Dari User
    private void setData() {
        setNoseri = exnoDevice.getText().toString();
        setNacl = exnacl.getText().toString();
        setWhiteness = exwhiteness.getText().toString();
        setWatercontent = exwatercontent.getText().toString();
        /*Log.d("blable",setNoseri +","+ setNacl +","+ setWhiteness +","+ setWatercontent);*/
    }

    //Berisi Statement-Statement Untuk Menyimpan Data Pada Database
    public void saveData() {
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbData.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(SQLiteDataGaram.kolom.NoSeri, setNoseri);
        values.put(SQLiteDataGaram.kolom.Nacl, setNacl);
        values.put(SQLiteDataGaram.kolom.Whiteness, setWhiteness);
        values.put(SQLiteDataGaram.kolom.Watercontent, setWatercontent);

        Log.d("datagaram", values.toString());

       /* //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
        create.insert(DBData.MyColumns.NamaTabel, null, values);*/

    }
}
