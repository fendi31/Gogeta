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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import butterknife.Bind;

import butterknife.ButterKnife;
import butterknife.OnClick;

//import static com.example.gogeta.R.id.jsonParsed;
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

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        String hostURL = pref.getString("hostURL", "");
        // WebServer Request URL
        String serverURL = "http://"+hostURL+"/pemesanan/guru";

        // Use AsyncTask execute Method To Prevent ANR Problem
        new LongOperation().execute(serverURL);

        super.onCreate(savedInstanceState);
        setContentView(activity_melihat_pemesanan);
        ButterKnife.bind(this);
//        String[] StringArray = {"Pemesanan oleh : ", "Nomor Telepon: ", "Jenjang: ", "Kelas: ", "Pelajaran: ","Topik: ","Durasi: ", "Catatan: ", "Harga: "};
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.item_list,StringArray);
//        ListView listView = (ListView) findViewById(R.id.pemesanannya);
//        listView.setAdapter(adapter);


    }

    @Bind(R.id.history)
    Button hist;
    @Bind(R.id.setting)
    Button sett;
  //  @Bind(R.id.jsonParsed)
   // TextView jsonParsedText;

    @OnClick(R.id.history)
    public void change() {
        finish();
        Intent sebuahIntent = new Intent(this, History.class);
        startActivity(sebuahIntent);
    }


    @OnClick(R.id.setting)
    public void change2() {
        finish();
        Intent sebuahIntent = new Intent(this, SettingActivity.class);
        startActivity(sebuahIntent);
    }

    // Class with extends AsyncTask class
    private class LongOperation extends AsyncTask<String, Void, Void> {

        // Required initialization

        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(MelihatPemesanan.this);
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
                        String nomortelepon = "";
                        String jenjang = jsonChildNode.optString("tingkat").toString();
                        String kelas = jsonChildNode.optString("kelas").toString();
                        String pelajaran = jsonChildNode.optString("pelajaran").toString();
                        String topik = jsonChildNode.optString("topik").toString();
                        String durasi = jsonChildNode.optString("durasi").toString();
                        String catatan = jsonChildNode.optString("catatan").toString();
                        String harga = jsonChildNode.optString("harga").toString();

                        OutputData = "";
                        OutputData += " Pemesan 		    : " + pemesan+ " \n "
                                + "Jenjang 		: " + jenjang+ " \n "
                                + "Kelas 				: " + kelas + " \n "
                                + "Pelajaran				: " + pelajaran+ " \n "
                                + "Topik				: " + topik+ " \n "
                                + "Durasi				: " + durasi+ " \n "
                                + "Catatan				: " + catatan+ " \n "
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
                    ListView listView = (ListView) findViewById(R.id.pemesanannya);
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