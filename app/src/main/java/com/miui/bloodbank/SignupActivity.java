package com.miui.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.miui.bloodbank.databinding.ActivityLoginBinding;
import com.miui.bloodbank.databinding.ActivitySignupBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupActivity extends AppCompatActivity {

    //viewBinding
    ActivitySignupBinding signupBinding;
    EditText sName, sMobile, sPass, sPass2;
    String n1, n2, n3;
    private final String signupUrl = "http://10.0.2.2/bloodbank/sign_up.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //viewBinding
        signupBinding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(signupBinding.getRoot());

        sName = findViewById(R.id.sName);
        sMobile = findViewById(R.id.sMobile);
        sPass = findViewById(R.id.sPass1);
        sPass2 = findViewById(R.id.sPass2);



        signupBinding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        signupBinding.sSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n1 = sName.getText().toString();
                n2 = sMobile.getText().toString();
                n3 = sPass.getText().toString();



                String qryString = "?sName="+n1+"&sMobile="+n2+"&sPass="+n3;

                class dbClass extends AsyncTask<String, Void, String>
                {

                    protected void onPostExecute(String data)
                    {
                        sName.setText("");
                        sMobile.setText("");
                        sPass.setText("");
                        sPass2.setText("");

                        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            URL url = new URL(params[0]);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                            return br.readLine();

                        }catch (Exception ex)
                        {
                            return ex.getMessage();
                        }

                    }
                }

                dbClass obj = new dbClass();
                obj.execute(signupUrl+qryString);
            }
        });








    }
}