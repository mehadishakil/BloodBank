package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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

public class RegisterDonar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button dDone;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner bgSpinner;
    String gndr, bgGrp;
    EditText dName, dParentsName, dBirth, dMobile, dEmail, dCity, dAddress;
    String name = "", parents = "", birth = "", mobile = "", email = "", city = "", address = "", gender = "", bloodgroup = "";
    private final String donarUrl = "http://10.0.2.2/bloodbank/donar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_donar);

        radioGroup = findViewById(R.id.dRadioGroup);
        bgSpinner = findViewById(R.id.dBloodGroupSpinner);
        dName = findViewById(R.id.dName);
        dParentsName = findViewById(R.id.dParentsName);
        dBirth = findViewById(R.id.dBirth);
        dMobile = findViewById(R.id.dMobile);
        dEmail = findViewById(R.id.dEmail);
        dCity = findViewById(R.id.dCity);
        dAddress = findViewById(R.id.dAddress);
        dDone = findViewById(R.id.dDone);

        bloodGroupSpinner();


        dDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = dName.getText().toString();
                parents = dParentsName.getText().toString();
                birth = dBirth.getText().toString();
                mobile = dMobile.getText().toString();
                email = dEmail.getText().toString();
                city = dCity.getText().toString();
                address = dAddress.getText().toString();
                gender = gndr;
                bloodgroup = bgGrp;


                String qryString = "?dName=" + name + "&dParentsName=" + parents + "&dBirth=" + birth + "&dMobile=" + mobile
                        + "&dEmail=" + email + "&dCity=" + city + "&dAddress=" + address + "&gndr=" + gender + "&bgGrp=" +bloodgroup;

                class dbClass extends AsyncTask<String, Void, String> {

                    protected void onPostExecute(String data) {

                        dName.setText("");
                        dParentsName.setText("");
                        dBirth.setText("");
                        dMobile.setText("");
                        dEmail.setText("");
                        dCity.setText("");
                        dAddress.setText("");

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
                obj.execute(donarUrl + qryString);
            }
        });


    }

    public void checkButton(View v) {
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        gndr = radioButton.getText().toString().trim();
    }

    public void bloodGroupSpinner() {
        bgSpinner = findViewById(R.id.dBloodGroupSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bloodGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bgSpinner.setAdapter(adapter);
        bgSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        bgGrp = adapterView.getItemAtPosition(i).toString().trim();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Toast.makeText(this, "Please select your blood group", Toast.LENGTH_SHORT).show();
    }


}