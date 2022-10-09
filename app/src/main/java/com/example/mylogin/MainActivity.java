package com.example.mylogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario, txtPassword, txtid, txtEmail;
    Button buttonConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = findViewById(R.id.usuario);
        txtPassword = findViewById(R.id.password);
        txtid = findViewById(R.id.id);
        txtEmail = findViewById(R.id.email);
        buttonConsultar = findViewById(R.id.buttonConsultar);

        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LeerWs();
            }
        });



    }

    private void LeerWs(){
        String url = "http://127.0.0.1:8087/consultar/1";

        StringRequest postReesquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txtid.setText(jsonObject.getString("id"));
                    txtUsuario.setText(jsonObject.getString("user"));
                    txtPassword.setText(jsonObject.getString("password"));
                    txtEmail.setText(jsonObject.getString("email"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(postReesquest);
    }
}