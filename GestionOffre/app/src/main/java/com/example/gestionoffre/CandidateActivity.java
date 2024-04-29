package com.example.gestionoffre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private String JSON_URL = "http://192.168.43.149/offre_api/candidates.php";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    Candidate candidate;
    private List<Candidate> candidateList = new ArrayList<>();
    private RecyclerView recyclerView;
    Toolbar toolbar;
    AdapterCandidate adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        recyclerView = (RecyclerView) findViewById(R.id.candidates_recyclerview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        jsonCall();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }
    private void jsonCall() {

        StringRequest jsonArrayRequest =  new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = jsonArray.getJSONObject(i);
                            candidate = new Candidate();
                            candidate.setNom(jsonObject.getString("nom"));
                            candidate.setPrenom(jsonObject.getString("prenom"));
                            candidate.setEmail(jsonObject.getString("email"));
                            candidate.setTelephone(jsonObject.getString("telephone"));
                            candidate.setCv(jsonObject.getString("cv"));

                            candidateList.add(candidate);


                        } catch (Exception e) {

                        }

                    }
                    adapter = new AdapterCandidate(CandidateActivity.this, candidateList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CandidateActivity.this));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CandidateActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("offre_id", getIntent().getStringExtra("offre_id"));
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(CandidateActivity.this);
        requestQueue.add(jsonArrayRequest);


    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String useriput = s.toLowerCase();
        List<Candidate> newList = new ArrayList<Candidate>();
        for (Candidate items : candidateList) {
            if ((items.getNom().toLowerCase().contains(useriput)) || (items.getPrenom().toLowerCase().contains(useriput))) {
                newList.add(items);
            }
        }
        adapter.filterlist(newList);
        return true;
    }




}
