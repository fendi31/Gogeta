package com.example.gogeta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import butterknife.Bind;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gogeta.R.layout.activity_main2;


public class Main2Activity extends AppCompatActivity {
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_main2);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
//        Intent sebuahIntent = new Intent(this,MelihatPemesanan.class);
//        startActivity(sebuahIntent);
        if(pref.getString("roleUser", "").equals("guru")){
            finish();
            Intent sebuahIntent = new Intent(this,MelihatPemesanan.class);
            startActivity(sebuahIntent);
        }

        ButterKnife.bind(this);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);

    };

        @Bind(R.id.history)
        Button hist;
        @Bind(R.id.setting)
        Button sett;
        @Bind(R.id.tombolsubmit)
        Button subm;
        @Bind(R.id.test)
        TextView test;
        @Bind(R.id.spinner1)
        Spinner spinner1atext;
        @Bind(R.id.spinner1b)
        Spinner spinner1btext;
        @Bind(R.id.spinner2)
        Spinner spinner2text;
        @Bind(R.id.edittetx3)
        EditText edittext3text;
        @Bind(R.id.spinner4)
        Spinner spinner4text;
        @Bind(R.id.edittext5)
        EditText edittext5text;
        @Bind(R.id.edittext6)
        EditText edittext6text;


        String jenjang;
        String kelas;
        String pelajaran;
        String topik;
        String durasi;
        String catatan;
        String harga;


    @OnClick(R.id.history)
        public void change(){
            finish();
            Intent sebuahIntent = new Intent(this,History.class);
            startActivity(sebuahIntent);
        }

        @OnClick(R.id.setting)
        public void change2(){
            finish();
            Intent sebuahIntent = new Intent(this,SettingActivity.class);
            startActivity(sebuahIntent);
        }

/**
    @OnClick(R.id.tombolsubmit)
    public void submit() {
        //Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
        text = spinner1atext.getSelectedItem().toString();


        test.setText(text);
    }
**/


    @OnClick(R.id.tombolsubmit)
    public void submit() {


        jenjang = spinner1atext.getSelectedItem().toString();
        kelas = spinner1btext.getSelectedItem().toString();
        pelajaran = spinner2text.getSelectedItem().toString();
        topik = edittext3text.getText().toString();
        durasi = spinner4text.getSelectedItem().toString();
        catatan = edittext5text.getText().toString();
        harga = edittext6text.getText().toString();

        BufferedReader reader = null;
        String data = "";
        String Content;

        // Send data
        try {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            String hostURL = pref.getString("hostURL", "");
//            String host = "10.5.95.161";
            String urlString = "http://" + hostURL + "/tekmob/?jenjang=" + jenjang + "&kelas=" + kelas  + "&pelajaran=" + pelajaran + "&topik=" + topik + "&durasi=" + durasi + "&catatan=" + catatan  + "&harga=" + harga;
            // Defined URL  where to send data
            URL url = new URL(urlString);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();


        } catch (Exception ex) {
            return ;
        } finally {
            try {

                reader.close();
            } catch (Exception ex) {
            }
        }




    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}




