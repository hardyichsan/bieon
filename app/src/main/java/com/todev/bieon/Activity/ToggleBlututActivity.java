package com.todev.bieon.Activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.todev.bieon.R;

public class ToggleBlututActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    TextView textView;

    BluetoothAdapter bluetoothadapter;
    Intent bluetoothIntent;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle_blutut);

        // Getting toggle button and textView from activity_main
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        textView = (TextView) findViewById(R.id.textView);

        bluetoothadapter = BluetoothAdapter.getDefaultAdapter();

        // Put listener on toggle button
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    textView.setText("Bluetooth is ON");
                    bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(bluetoothIntent, i);
                } else {
                    textView.setText("Bluetooth is OFF");
                    bluetoothadapter.disable();
                }
            }
        });
        // For initial setting
        if (toggleButton.isChecked()) {
            textView.setText("Bluetooth is ON");
            bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(bluetoothIntent, i);
        } else {
            textView.setText("Bluetooth is OFF");
            bluetoothadapter.disable();
        }
    }
}
