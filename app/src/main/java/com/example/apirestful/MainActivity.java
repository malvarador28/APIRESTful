package com.example.apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtLista = (TextView)findViewById(R.id.txtLista);
        ArrayList<String> lstUsuario = new ArrayList<String>();
        JSONObject JSONObjeto = new JSONObject(result);
        JSONArray JSONlista =  JSONObjeto.getJSONArray("data");
        String Lista="";
        for(int i=0; i< JSONlista.length();i++){
            JSONObject usuario=  JSONlista.getJSONObject(i);
            Lista=Lista + usuario.getString("name") + "\n";
        }

        txtLista.setText(Lista);
    }
}
