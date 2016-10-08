package com.example.gogeta;

import android.content.Intent;
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


}
