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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import static com.example.gogeta.R.layout.activity_history;
import static com.example.gogeta.R.layout.activity_melihat_pemesanan;


public class History extends AppCompatActivity {
    String hostURL = "";
    String roleUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        hostURL = pref.getString("hostURL", "");
        roleUser = pref.getString("roleUser", "");
        String emailUser = pref.getString("emailUser", "");

        String serverURL = "";
        if(roleUser.equals("siswa")){
            serverURL = "http://"+hostURL+"/pemesanan/userProgres?email="+emailUser;
        }
        else{
            serverURL = "http://"+hostURL+"/pemesanan/guruProgres?email="+emailUser;
        }


        // Use AsyncTask execute Method To Prevent ANR Problem
        new History.LongOperation().execute(serverURL);


        super.onCreate(savedInstanceState);
        setContentView(activity_history);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //       setSupportActionBar(toolbar);
        ButterKnife.bind(this);


    };
    @Bind(R.id.pemesanan)
    Button pesan;

    @Bind(R.id.setting)
    Button sett;

    @Bind(R.id.completed)
    Button completed;

    @OnClick(R.id.pemesanan)
    public void change(){
        finish();
        Intent sebuahIntent = new Intent(this,Main2Activity.class);
        startActivity(sebuahIntent);
    }

    @OnClick(R.id.setting)
    public void change2(){
        finish();
        Intent sebuahIntent = new Intent(this,SettingActivity.class);
        startActivity(sebuahIntent);
    }

    @OnClick(R.id.completed)
    public void completed(){
        finish();
        Intent sebuahIntent = new Intent(this,HistoryCompletedActivity.class);
        startActivity(sebuahIntent);
    }

    // Class with extends AsyncTask class
    private class LongOperation extends AsyncTask<String, Void, Void> {

        // Required initialization

        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(History.this);
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
                        String guru = jsonChildNode.optString("guru").toString();
                        String waktu = jsonChildNode.optString("waktu").toString();
                        String tingkat = jsonChildNode.optString("tingkat").toString();
                        String kelas = jsonChildNode.optString("kelas").toString();
                        String pelajaran = jsonChildNode.optString("pelajaran").toString();
                        String topik = jsonChildNode.optString("topik").toString();
                        String durasi = jsonChildNode.optString("durasi").toString();
                        String harga = jsonChildNode.optString("harga").toString();

                        OutputData = "";
                        OutputData += " Siswa 		        : " + pemesan + " \n "
                                    + "Guru 		        : " + guru + " \n "
                                    + "Waktu 				: " + waktu + " \n "
                                    + "Tingkat				: " + tingkat + " \n "
                                    + "Kelas				: " + kelas+ " \n "
                                    + "Pelajaran			: " + pelajaran + " \n "
                                    + "Topik				: " + topik + " \n "
                                    + "Durasi				: " + durasi + " \n "
                                    + "Harga				: " + harga + " \n "
                        ;
                        //                               + "--------------------------------------------------\n";

                        something[i] = OutputData;
                        //Log.i("JSON parse", song_name);
                    }
                    /****************** End Parse Response JSON Data *************/


                    //Show Parsed Output on screen (activity)
                    //jsonParsedText.setText(OutputData);
//
                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.item_list,something);
                    ListView listView = (ListView) findViewById(R.id.history_progress);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {

                    e.printStackTrace();
                }


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