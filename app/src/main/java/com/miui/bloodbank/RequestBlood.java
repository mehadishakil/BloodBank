package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestBlood extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button pDone;
    RadioGroup pradioGroup;
    RadioButton radioButton;
    Spinner pbgSpinner;
    String gndr, bgGrp;
    EditText pName, pParentsName, pDisease, pCity, pHospital, pMobile, pAddress;
    String name = "", parents = "", disease = "", city = "", hospital = "", mobile = "", address = "", gender = "", bloodgroup = "";
    private final String rbUrl = "http://10.0.2.2/bloodbank/request_blood.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);


        pradioGroup = findViewById(R.id.pRadioGroup);
        pbgSpinner = findViewById(R.id.pBloodGroupSpinner);
        pName = findViewById(R.id.pName);
        pParentsName = findViewById(R.id.pParentsName);
        pDisease = findViewById(R.id.pDisease);
        pCity = findViewById(R.id.pCity);
        pHospital = findViewById(R.id.pHospital);
        pMobile = findViewById(R.id.pMobile);
        pAddress = findViewById(R.id.pAddress);
        pDone = findViewById(R.id.pDone);


        bloodGroupSpinner();


        pDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = pName.getText().toString();
                parents = pParentsName.getText().toString();
                disease = pDisease.getText().toString();
                city = pCity.getText().toString();
                hospital = pHospital.getText().toString();
                mobile = pMobile.getText().toString();
                address = pAddress.getText().toString();
                gender = gndr;
                bloodgroup = bgGrp;


                String qryString = "?pName=" + name + "&pParentsName=" + parents + "&pDisease=" + disease + "&pCity=" + city
                        + "&pHospital=" + hospital + "&pMobile=" + mobile + "&pAddress=" + address + "&gndr=" + gender + "&bgGrp=" + bloodgroup;

                class dbClass extends AsyncTask<String, Void, String> {

                    protected void onPostExecute(String data) {

                        pName.setText("");
                        pParentsName.setText("");
                        pDisease.setText("");
                        pCity.setText("");
                        pHospital.setText("");
                        pMobile.setText("");
                        pAddress.setText("");

                    }

                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            URL url = new URL(params[0]);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            return br.readLine();

                        } catch (Exception ex) {
                            return ex.getMessage();
                        }

                    }
                }

                dbClass obj = new dbClass();
                obj.execute(rbUrl + qryString);
            }
        });


    }


    public void selectButton(View v) {
        int radioID = pradioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        gndr = radioButton.getText().toString().trim();
    }

    public void bloodGroupSpinner() {
        pbgSpinner = findViewById(R.id.pBloodGroupSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bloodGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pbgSpinner.setAdapter(adapter);
        pbgSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        bgGrp = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Toast.makeText(this, "Please select your blood group", Toast.LENGTH_SHORT).show();
    }


}