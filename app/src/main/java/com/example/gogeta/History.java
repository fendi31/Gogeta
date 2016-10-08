package com.example.gogeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gogeta.R.layout.activity_history;
import static com.example.gogeta.R.layout.activity_main;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(activity_main2);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        /**FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }**/
 ///        );
    }

    @Bind(R.id.menu)
    Button hist;

    @Bind(R.id.setting)
    Button sett;

    @OnClick(R.id.menu)
    public void change(){
        Intent sebuahIntent = new Intent(this,Main2Activity.class);
        startActivity(sebuahIntent);
    }

    @OnClick(R.id.setting)
    public void change2(){
        Intent sebuahIntent = new Intent(this,SettingActivity.class);
        startActivity(sebuahIntent);
    }


}
