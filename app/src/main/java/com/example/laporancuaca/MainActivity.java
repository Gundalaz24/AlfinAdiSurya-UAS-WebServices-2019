package com.example.laporancuaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView hasil;
    private RequestQueue nQueue;
    String url = "http://papaside.com/data.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nQueue = Volley.newRequestQueue(this);
        hasil = findViewById(R.id.tvhasil);
        tampilkanJson();

    }

    private void tampilkanJson() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    for (int i = 0; i < response.length(); i++) {
                        JSONObject cuaca = response.getJSONObject(i);

                        String kota = cuaca.getString("Kota");
                        String siang = cuaca.getString("siang");
                        String malam = cuaca.getString("malam");
                        String dini = cuaca.getString("dini_hari");
                        String suhu = cuaca.getString("suhu");
                        String lembap = cuaca.getString("Kelembapan");


                        hasil.append("Kota : "+ kota + "\nSiang : "+ siang + "\nMalam : "+ malam + "\nDini Hari : "+dini+"\nSuhu : "+suhu+"\nKelembapan : "+lembap+"\n\n");
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error oyy", Toast.LENGTH_SHORT).show();
            }
        });

        nQueue.add(request);
    }
}
