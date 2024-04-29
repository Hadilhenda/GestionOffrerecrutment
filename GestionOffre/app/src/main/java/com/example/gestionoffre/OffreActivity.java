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
import java.util.List;

public class OffreActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private String JSON_URL = "http://192.168.43.149/offre_api/offres.php";
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;
    Offre offre;
    private List<Offre> offreList = new ArrayList<>();
    private RecyclerView recyclerView;
    Toolbar toolbar;
    AdapterOffre adapter;
    Button ajout_offre,mes_offres;
    String profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offre);

        recyclerView = (RecyclerView) findViewById(R.id.offres_recyclerview);
        ajout_offre =  findViewById(R.id.ajout_offre);
        mes_offres = findViewById(R.id.mes_offres);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        profile = sharedPreferences.getString("profile","");
        if(profile.equals("admin"))
        {
            Typeface home_txt_face = Typeface.createFromAsset(getAssets(), "fonts/dbplus.otf");
            ajout_offre.setVisibility(View.VISIBLE);
            ajout_offre.setTypeface(home_txt_face);
            ajout_offre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent i = new Intent(OffreActivity.this,AjoutActivity.class);
                    startActivity(i);
                }
            });

        }

        if(profile.equals("candidate"))
        {
            Typeface home_txt_face = Typeface.createFromAsset(getAssets(), "fonts/dbplus.otf");
            mes_offres.setVisibility(View.VISIBLE);
            mes_offres.setTypeface(home_txt_face);
            mes_offres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent i = new Intent(OffreActivity.this,MesOffresActivity.class);
                    startActivity(i);
                }
            });
        }
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
                            offre = new Offre();
                            offre.setId(jsonObject.getInt("id") + "");
                            offre.setTitre(jsonObject.getString("titre"));
                            offre.setDescription(jsonObject.getString("description"));
                            offre.setDate_debut(jsonObject.getString("date_debut"));
                            offre.setDate_fin(jsonObject.getString("date_fin"));

                            offreList.add(offre);


                        } catch (Exception e) {

                        }

                    }
                    adapter = new AdapterOffre(OffreActivity.this, offreList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OffreActivity.this));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OffreActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(OffreActivity.this);
        requestQueue.add(jsonArrayRequest);


    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String useriput = s.toLowerCase();
        List<Offre> newList = new ArrayList<Offre>();
        for (Offre items : offreList) {
            if ((items.getDate_debut().toLowerCase().contains(useriput)) || (items.getTitre().toLowerCase().contains(useriput))) {
                newList.add(items);
            }
        }
        adapter.filterlist(newList);
        return true;
    }




}
