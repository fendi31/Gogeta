package com.example.gogeta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @Bind(R.id.pemesanan)
    Button pesan;
    @Bind(R.id.history)
    Button hist;
    @Bind(R.id.logout)
    Button logout;

    @OnClick(R.id.history)
    public void change(){
        Intent sebuahIntent = new Intent(this,History.class);
        startActivity(sebuahIntent);
    }

    @OnClick(R.id.pemesanan)
    public void change2(){
        Intent sebuahIntent = new Intent(this,Main2Activity.class);
        startActivity(sebuahIntent);
    }

    @OnClick(R.id.logout)
    public void logout(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogged", false); // Storing boolean - true/false
        editor.commit();
        Intent sebuahIntent = new Intent(this,LoginActivity.class);
        startActivity(sebuahIntent);
    }
}
