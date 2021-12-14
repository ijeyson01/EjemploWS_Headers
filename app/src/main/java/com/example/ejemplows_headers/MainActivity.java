package com.example.ejemplows_headers;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue rq;
    TextView textView;
    public static final String URL1 = "https://jsonplaceholder.typicode.com/todos/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rq = Volley.newRequestQueue(this);
        initUI();
        jsonArrayRequest();
    }
    private void initUI(){
        textView = findViewById(R.id.txtListaTraduciones);
    }

    private void jsonArrayRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i=0; i<size; i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                String title = jsonObject.getString("title");
                                textView.append("\n"+title);
                            } catch (JSONException e) {
                                textView.setText("Error: "+e.toString());
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Error: "+error.toString());
                    }
                }
        );

        rq.add(jsonArrayRequest);

    }

}