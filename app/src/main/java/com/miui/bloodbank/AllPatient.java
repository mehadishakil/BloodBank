package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllPatient extends AppCompatActivity {

    RecyclerView allPatientRecyclerView;
    RecyclerView.LayoutManager pLayoutManager;
    PatientAdapter pAdapter;
    private static final String patientListUrl = "http://10.0.2.2/bloodbank/display_patient.php";
    ArrayList<String> patientList = new ArrayList<>();
    ArrayList<String> patientHospitalList = new ArrayList<>();
    ArrayList<String> patientMobileList = new ArrayList<>();
    ArrayList<String> patientBGList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_patient);


        allPatientRecyclerView = findViewById(R.id.allPatientRecyclerview);
        fetchPatient();

    }



    public void fetchPatient()
    {
        allPatientRecyclerView = findViewById(R.id.allPatientRecyclerview);

        class dbManager extends AsyncTask<String, Void, String>
        {

            protected void onPostExecute(String data) {
                try {
                    JSONArray ja = new JSONArray(data);
                    patientList.clear();
                    patientHospitalList.clear();
                    patientMobileList.clear();
                    patientBGList.clear();

                    for(int i=0; i<ja.length(); i++)
                    {
                        JSONObject jo = ja.getJSONObject(i);
                        String name = jo.getString("pname");
                        String hospital = jo.getString("phospital");
                        String mobile = jo.getString("pmobile");
                        String bg = jo.getString("pbloodgroup");

                        patientList.add(name);
                        patientHospitalList.add(hospital);
                        patientMobileList.add(mobile);
                        patientBGList.add(bg);
                    }

                    pLayoutManager = new LinearLayoutManager(getApplicationContext());
                    allPatientRecyclerView.setLayoutManager(pLayoutManager);
                    pAdapter = new PatientAdapter(patientList, patientHospitalList, patientMobileList, patientBGList);
                    allPatientRecyclerView.setAdapter(pAdapter);

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
        obj.execute(patientListUrl);
    }


}