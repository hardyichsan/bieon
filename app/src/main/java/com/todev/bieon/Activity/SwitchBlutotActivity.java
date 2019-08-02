package com.todev.bieon.Activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.todev.bieon.R;

public class SwitchBlutotActivity extends AppCompatActivity {

    Switch switchButton;
    TextView textview;
    BluetoothAdapter bluetoothadapter;
    int i = 1;
    Intent bluetoothIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_blutot);

        switchButton = (Switch)findViewById(R.id.switch1);
        textview = (TextView)findViewById(R.id.textView1);


        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean cheked) {
                // TODO Auto-generated method stub

                if(cheked){
                    textview.setText("Bluetooth Status ON");
                    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                    adapter.enable();
                }else {
                    textview.setText("Bluetooth Status OFF");
                    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                    adapter.disable();

                }

            }
        });
        // For initial setting
        if (switchButton.isChecked()) {
            textview.setText("Bluetooth Status ON");
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            adapter.enable();
        } else {
            textview.setText("Bluetooth Status OFF");
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            adapter.disable();
        }
    }

}
