package com.todev.bieon.BottomBars;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.todev.bieon.Activity.AppVersionActivity;
import com.todev.bieon.Activity.DeviceStatusActivity;
import com.todev.bieon.Activity.HelpActivity;
import com.todev.bieon.Activity.KonekPrinterActivity;
import com.todev.bieon.Activity.KonekSaltdecActivity;
import com.todev.bieon.Activity.LoginActivity;
import com.todev.bieon.Activity.PrivatePolicyActivity;
import com.todev.bieon.Activity.TermActivity;
import com.todev.bieon.BluetoothTerminal.KomunikasiActivity;
import com.todev.bieon.BluetoothTerminal.ScanActivity;
import com.todev.bieon.BluetoothTerminal.SelectActivity;
import com.todev.bieon.Helper.SQLiteHandler;
import com.todev.bieon.Helper.SessionManager;
import com.todev.bieon.MainActivity;
import com.todev.bieon.PrinterThermal.PrinterTothActivity;
import com.todev.bieon.R;

public class PengaturanFragment extends Fragment {

    private Button ksaltdect, kprinter, dstatus, term, privat, appversion, help, logout;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaturan, container, false);

        ksaltdect = (Button)view.findViewById(R.id.ksaltdec);
        kprinter = (Button)view.findViewById(R.id.kprinter);
        dstatus = (Button)view.findViewById(R.id.dstatus);
        term = (Button)view.findViewById(R.id.term);
        privat = (Button)view.findViewById(R.id.privat);
        appversion = (Button)view.findViewById(R.id.appversion);
        help = (Button)view.findViewById(R.id.help);
        logout = (Button)view.findViewById(R.id.logout);

       /* // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // logout dari activity
        // session manager
        session = new SessionManager(getApplicationContext());
        */

        // SqLite database handler
        db = new SQLiteHandler(getActivity());

        // session manager
        session = new SessionManager(getActivity());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        ksaltdect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivity(intent);
            }
        });

        kprinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PrinterTothActivity.class);
                startActivity(intent);
            }
        });

        dstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),DeviceStatusActivity.class);
                startActivity(intent);
            }
        });

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),TermActivity.class);
                startActivity(intent);
            }
        });

        privat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PrivatePolicyActivity.class);
                startActivity(intent);
            }
        });

        appversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AppVersionActivity.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HelpActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("You are sure you want to close this appilacation ?")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                logoutUser();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        }).show();

            }
        });

        return view;
    }


    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(getActivity().getApplication(), LoginActivity.class);
        startActivity(intent);

    }

}
