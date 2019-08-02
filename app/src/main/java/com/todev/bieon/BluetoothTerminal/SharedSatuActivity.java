package com.todev.bieon.BluetoothTerminal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.todev.bieon.R;

public class SharedSatuActivity extends AppCompatActivity {

    public static final String KEY_SHARED = "FirstActivity.KEY";

    private EditText editText_inputValue;
    private Button btn_goNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1_shared);



        editText_inputValue = (EditText) findViewById(R.id.inputtext_value);
        btn_goNext = (Button) findViewById(R.id.btn_goNext);

        btn_goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * misalnya kita akan ambil valuenya dari apa yang
                 * kita input di EditText
                 */
                String value = editText_inputValue.getText().toString();

                /**
                 * selanjutnya kita akan mengirim value ini ke Activity selanjutnya
                 */
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SharedSatuActivity.this);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(KEY_SHARED, value);
                edit.commit();

                Intent intent = new Intent(SharedSatuActivity.this, ShareDuaActivity.class);
                intent.putExtra(KEY_SHARED, value);
                startActivity(intent);
            }
        });
    }
}
