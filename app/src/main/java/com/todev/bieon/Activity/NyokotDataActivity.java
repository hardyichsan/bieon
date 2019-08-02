package com.todev.bieon.Activity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.todev.bieon.BluetoothTerminal.SelectActivity;
import com.todev.bieon.BottomBars.AmbilDataFragment;
import com.todev.bieon.MainActivity;
import com.todev.bieon.R;

import me.aflak.bluetooth.Bluetooth;

public class NyokotDataActivity extends AppCompatActivity implements Bluetooth.CommunicationCallback {

    Button btnhitung;
    private Bluetooth b;
    ProgressDialog progressDialog;
    private TextView text;


    String name;
    /*String No_Alat = "", Nacl = "", Whiteness = "" , WaterContent = "";*/
    TextView exnacl,exwhiteness, exkdarair, exnoDevice, ukur, nmBluetooth, et;
    private ScrollView scrollView;
    private boolean registered=false;

    public static final String EXTRA_MESSAGE = "com.otin.lier.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nyokot_data);

        btnhitung = (Button)findViewById(R.id.hitung);
        text = (TextView)findViewById(R.id.textTampil);

        b = new Bluetooth(this);
        b.enableBluetooth();
        b.setCommunicationCallback(this);

        int pos = getIntent().getExtras().getInt("pos");
        name = b.getPairedDevices().get(pos).getName();

//        Display("Connecting...");
        b.connectToDevice(b.getPairedDevices().get(pos));

        btnhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(v.getContext());
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            String emsatu = "1";
                            b.send(emsatu);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {

                        } progressDialog.dismiss();
                    }
                }).start();
            }
        });

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        registered=true;
    }

    public void shareItem(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        String yangMaudiShare = "lal";
        intent.putExtra(Intent.EXTRA_SUBJECT,"DATA ALAT");
        intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(new StringBuilder()
                .append("<b> yg item</b>")
                .append("something")
                .toString()
        ));
        startActivity(Intent.createChooser(intent,"Chooshaing method"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(registered) {
            unregisterReceiver(mReceiver);
            registered=false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.close:
                b.removeCommunicationCallback();
                b.disconnect();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

           /* case R.id.rate:
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String Display(final String s){
        this.runOnUiThread(new Runnable() {
            private Object String;

            @Override
            public void run() {
                text.append(s + "\n");
                /* scrollView.fullScroll(View.FOCUS_DOWN);*/
            }

        });
        return s;
    }

    public void Display(final String s, boolean forMessage){
        String[] result = s.split("[,]");
           /* Display("Data Start");
            for(String data : result){
                Display(data);
            }*/
        /*Display("End");*/
        switch (result[1]) {
               /* case "NoSeri":
                    String noseri = ("NoSeri : " + result[2].toString());
                    Display(noseri);

                    break;*/
            case "Data":
                String nacl = new String(result[2].toString());
                String whitnes =new String(result[3].toString());
                String water = new  String (result[4].toString());
                String noseri = new String (result[7].toString());
                    /*Display("Tegangan:" + new String (result[5].toString()));
                    Display("Persen :" + new String (result[6].toString()) + "%");*/


                Display(nacl);
                Display(whitnes);
                Display(water);
                Display(noseri);

                Intent intent = new Intent(getApplicationContext(), TampilHitungActivity.class);

                intent.putExtra("noseri",noseri);
                intent.putExtra("nacl",nacl);
                intent.putExtra("whiteness",whitnes);
                intent.putExtra("water",water);


                startActivity(intent);

                break;

        }

    }

    @Override
    public void onConnect(BluetoothDevice device) {
        Display("Connected to "+device.getName()+" - "+device.getAddress());

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnhitung.setEnabled(true);
            }
        });
    }

    @Override
    public void onDisconnect(BluetoothDevice device, String message) {
        Display("Disconnected!");
        Display("Connecting again...");
        b.connectToDevice(device);
    }

    @Override
    public void onMessage(String message) {
        Display(message, true);
    }

    @Override
    public void onError(String message) {
        /*Display("Error: "+message);*/
    }

    @Override
    public void onConnectError(final BluetoothDevice device, String message) {
        Display("Error: "+message);
        Display("Trying again in 3 sec.");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.connectToDevice(device);
                    }
                }, 2000);
            }
        });
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                Intent intent1 = new Intent(NyokotDataActivity.this, SelectActivity.class);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        if(registered) {
                            unregisterReceiver(mReceiver);
                            registered=false;
                        }
                        startActivity(intent1);
                        finish();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        if(registered) {
                            unregisterReceiver(mReceiver);
                            registered=false;
                        }
                        startActivity(intent1);
                        finish();
                        break;
                }
            }
        }
    };
}
