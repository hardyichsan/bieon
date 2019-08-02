package com.todev.bieon.BluetoothTerminal;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.todev.bieon.R;

public class ShareDuaActivity extends AppCompatActivity {


    private Button mBtnBack;
    private TextView outPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_dua);

        mBtnBack = (Button) findViewById(R.id.btn_back);
        outPut = (TextView) findViewById(R.id.second_layout);

        /**
         * kita ambil value yang telah diset sebelumnya dengan KEY yang sama
         */


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ShareDuaActivity.this);
        String value = getIntent().getExtras().getString(SharedSatuActivity.KEY_SHARED);

        outPut.setText(value);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
