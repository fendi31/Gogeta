package com.example.gogeta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        if(pref.getString("roleUser", "").equals("guru")){
            finish();
            Intent sebuahIntent = new Intent(this,MelihatPemesanan.class);
            startActivity(sebuahIntent);
        }


        final String hostURL = pref.getString("hostURL", "");
        final String emailUser = pref.getString("emailUser", "");



        Button mSubmitButton = (Button) findViewById(R.id.tombolsubmit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner jenjangSpinner = (Spinner)findViewById(R.id.spinner1);
                String jenjang = jenjangSpinner.getSelectedItem().toString();
                Spinner kelasSpinner = (Spinner)findViewById(R.id.spinner1b);
                String kelas = kelasSpinner.getSelectedItem().toString();
                Spinner pelajaranSpinner = (Spinner)findViewById(R.id.spinner2);
                String pelajaran = pelajaranSpinner.getSelectedItem().toString();
                EditText topikEdit = (EditText)findViewById(R.id.edittetx3);
                String topik = topikEdit.getText().toString();
                Spinner durasiSpinner = (Spinner)findViewById(R.id.spinner4);
                String durasi = durasiSpinner.getSelectedItem().toString();
                EditText catatanEdit = (EditText)findViewById(R.id.edittext5);
                String catatan = catatanEdit.getText().toString();

                //            String host = "10.5.95.161";
                final String urlString = "http://" + hostURL + "/pemesanan/add?siswa=" + emailUser + "&tingkat=" + jenjang + "&kelas=" + kelas  + "&pelajaran=" + pelajaran + "&topik=" + topik + "&durasi=" + durasi + "&catatan=" + catatan  ;

                // Use AsyncTask execute Method To Prevent ANR Problem
                new Main2Activity.LongOperation().execute(urlString);
            }
        });
        ButterKnife.bind(this);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);

    };

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


    // Class with extends AsyncTask class
    private class LongOperation extends AsyncTask<String, Void, Void> {

        // Required initialization

        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(Main2Activity.this);
        String data = "";
        //TextView uiUpdate = (TextView) findViewById(R.id.output);
        //TextView jsonParsed = (TextView) findViewById(R.id.jsonParsed);

        int sizeData = 0;
        //EditText serverText = (EditText) findViewById(R.id.serverText);


//        protected void onPreExecute() {
//            // NOTE: You can call UI Element here.
//
//            //Start Progress Dialog (Message)
//
//            Dialog.setMessage("Please wait..");
//            Dialog.show();
//
//
//
//        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {

            /************ Make Post Call To Web Server ***********/
            BufferedReader reader = null;

            // Send data
            try {

                // Defined URL  where to send data
                URL url = new URL(urls[0]);

                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();
                Log.d("MASUK", url.toString());
                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    // Append server response in string
                    sb.append(line + "\n");
                }

                // Append Server Response To Content String
                Content = sb.toString();
            } catch (Exception ex) {
                Error = ex.getMessage();
            } finally {
                try {

                    reader.close();
                } catch (Exception ex) {
                }
            }

            /*****************************************************/
            return null;
        }

        protected void onPostExecute(Void unused) {
            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();

            if (Error != null) {
//                jsonParsedText.setText(Error);
                //      uiUpdate.setText("Output : " + Error);
                ;
            } else {

                // Show Response Json On Screen (activity)
                //   uiUpdate.setText(Content);

                /****************** Start Parse Response JSON Data *************/

                String OutputData = "";
                JSONObject jsonResponse;

                try {

                    /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                    jsonResponse = new JSONObject(Content);

                    /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                    /*******  Returns null otherwise.  *******/
                    JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");

                    /*********** Process each JSON Node ************/

                    int lengthJsonArr = jsonMainNode.length();

                    String[] something = new String[lengthJsonArr];


                    for (int i = 0; i < lengthJsonArr; i++) {
                        /****** Get Object for each JSON node.***********/
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                        /******* Fetch node values **********/
                        String pemesan = jsonChildNode.optString("siswa").toString();


                        OutputData = "";
                        OutputData += " Siswa 		        : " + pemesan + " \n "

                        ;
                        //                               + "--------------------------------------------------\n";

//                        something[i] = OutputData;

//                        TextView test = (TextView) findViewById(R.id.test);
//                        test.setText(OutputData);
                        berhasilSend();

                        //Log.i("JSON parse", song_name);
                    }
                    /****************** End Parse Response JSON Data *************/


                    //Show Parsed Output on screen (activity)
                    //jsonParsedText.setText(OutputData);
//
//                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_list,something);
//                    ListView listView = (ListView) findViewById(R.id.history_progress);
//                    listView.setAdapter(adapter);


                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }

        }

    }

    public void berhasilSend(){
        finish();
        Intent sebuahIntent = new Intent(this,History.class);
        startActivity(sebuahIntent);
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




