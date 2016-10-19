package com.example.gogeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //       setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        //String[] items = new String[]{"1", "2", "three"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //dropdown.setAdapter(adapter);

    };
        /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); **/
        @Bind(R.id.history)
        Button hist;
        @Bind(R.id.setting)
        Button sett;

        @OnClick(R.id.history)
        public void change(){
            Intent sebuahIntent = new Intent(this,History.class);
            startActivity(sebuahIntent);
        }

        @OnClick(R.id.setting)
        public void change2(){
            Intent sebuahIntent = new Intent(this,SettingActivity.class);
            startActivity(sebuahIntent);
        }

        public void addListenerOnSpinnerItemSelection() {
            spinner1 = (Spinner) findViewById(R.id.spinner1);
            spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);



        };

/**
    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }
**/

    }




