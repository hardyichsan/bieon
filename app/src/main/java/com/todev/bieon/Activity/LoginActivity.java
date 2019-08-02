package com.todev.bieon.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.todev.bieon.App.AppConfig;
import com.todev.bieon.App.AppController;
import com.todev.bieon.Helper.SQLiteHandler;
import com.todev.bieon.Helper.SessionManager;
import com.todev.bieon.MainActivity;
import com.todev.bieon.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    Button login;
    ProgressDialog progressDialog;



    int Permission_All = 1;

    private static final String TAG = DaftarActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String[] Permessions = {Manifest.permission.BLUETOOTH,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION};
        if(!hasPermisions(this,Permessions)){
            ActivityCompat.requestPermissions(this, Permessions, Permission_All);
        }

       /* login = (Button)findViewById(R.id.button_signin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });*/


        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        login = (Button)findViewById(R.id.button_signin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setMessage("Loading..."); // Setting Message
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*//* finally {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }*//* progressDialog.dismiss();
                    }
                }).start();*/

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() || !password.isEmpty()){
                    checkLogin();
                }else {
                    inputEmail.setError("Please insert email");
                    inputPassword.setError("Please insert password");
                }
            }
        });


    }


    public void Daftar(View view) {

        Intent intent = new Intent(getApplicationContext(),DaftarActivity.class);
        startActivity(intent);
    }

    public static boolean hasPermisions(Context context, String... permissions){

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && context!=null && permissions!=null){
            for(String permission:permissions){{
                if(ActivityCompat.checkSelfPermission(context, permission) !=PackageManager.PERMISSION_GRANTED){
                    return  false;
                }
            }}
        }
        return true;
    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin() {

        final String email = this.inputEmail.getText().toString().trim();
        final String password = this.inputPassword.getText().toString().trim();
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    String success = jObj.getString("success");

                    // Check for error node in json
                    if (success.equals("true")) {


                        session.setLogin(true);

                        Toast.makeText(getApplicationContext(), "Login Success!!", Toast.LENGTH_LONG).show();

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                       /* intent.putExtra("email",email);
                        intent.putExtra("phone", phone);*/

                        startActivity(intent);
                        // user successfully logged in
                        // Create login session

                        // Now store the user in SQLite

                        JSONObject user = jObj.getJSONObject("user");
                        String phone = user.getString("phone");
                        String email = user.getString("email");


                        // Inserting row in users table
                        db.addUser(phone, email);

                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance(this).addToRequestQueue(strReq);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}
