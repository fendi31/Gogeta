package com.example.gogeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gogeta.R.layout.activity_main2;
import static com.example.gogeta.R.layout.activity_melihat_pemesanan;


public class MelihatPemesanan extends AppCompatActivity {
    private Spinner spinner1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_melihat_pemesanan);
        ButterKnife.bind(this);
        String[] StringArray = {"Pemesanan : ", "Nomor Telepon", "Jenjang", "Topik", "Durasi"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.item_list,StringArray);
        ListView listView = (ListView) findViewById(R.id.pemesanannya);
        listView.setAdapter(adapter);


    }
        /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        });
        @Bind(R.id.history)
        Button hist;
        @Bind(R.id.setting)
        Button sett;

        @OnClick(R.id.history)
        public void change () {
            Intent sebuahIntent = new Intent(this, History.class);
            startActivity(sebuahIntent);
        }


        @OnClick(R.id.setting)
        public void change2 () {
            Intent sebuahIntent = new Intent(this, SettingActivity.class);
            startActivity(sebuahIntent);
        }
         **/

/**
 public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

 public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
 Toast.makeText(parent.getContext(),
 "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
 Toast.LENGTH_SHORT).show();
 }
 **/

}




