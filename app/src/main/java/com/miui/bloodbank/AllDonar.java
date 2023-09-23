package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllDonar extends AppCompatActivity {

    RecyclerView allDonarRecyclerView;
    RecyclerView.LayoutManager dLayoutManager;
    RecyclerView.Adapter dAdapter;
    private static final String donarListUrl = "http://10.0.2.2/bloodbank/display_donar.php";
    ArrayList<String> donarList = new ArrayList<>();
    ArrayList<String> donarMobileList = new ArrayList<>();
    ArrayList<String> donarBGList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donar);

        allDonarRecyclerView = findViewById(R.id.allDonarRecyclerview);
        fetchData();
    }


    public void fetchData()
    {
        allDonarRecyclerView = findViewById(R.id.allDonarRecyclerview);

        class dbManager extends AsyncTask<String, Void, String>
        {

            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    donarList.clear();
                    donarMobileList.clear();
                    donarBGList.clear();

                    for(int i=0; i<ja.length(); i++)
                    {
                        JSONObject jo = ja.getJSONObject(i);
                        String name = jo.getString("dname");
                        String mobile = jo.getString("dmobile");
                        String bg = jo.getString("dbloodgroup");
                        donarList.add(name);
                        donarMobileList.add(mobile);
                        donarBGList.add(bg);
                    }

                    dLayoutManager = new LinearLayoutManager(getApplicationContext());
                    allDonarRecyclerView.setLayoutManager(dLayoutManager);
                    dAdapter = new RecyclerAdapter(donarList, donarMobileList, donarBGList);
                    allDonarRecyclerView.setAdapter(dAdapter);

                } catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;

                    while((line=br.readLine()) != null)
                    {
                        data.append(line+"\n");
                    }
                    br.close();
                    return data.toString();

                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }


        }

        dbManager obj = new dbManager();
        obj.execute(donarListUrl);
    }

}