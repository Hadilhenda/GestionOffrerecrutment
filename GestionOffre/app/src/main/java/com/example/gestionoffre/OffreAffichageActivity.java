package com.example.gestionoffre;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class OffreAffichageActivity extends AppCompatActivity {
TextView  txt_titre_details,txt_date_debut_details,txt_date_fin_details,txt_description_details;
Button btn_postuler,btn_spprimer_offre,liste_candidates ;
    String url_postuler = "http://192.168.43.149/offre_api/postuler.php";
    String url_suppression = "http://192.168.43.149/offre_api/suppression_offre.php";
    String user_id,profile;

    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_offre);
        txt_titre_details = findViewById(R.id.txt_titre_details);
        txt_date_debut_details = findViewById(R.id.txt_date_debut_details);
        txt_date_fin_details = findViewById(R.id.txt_date_fin_details);
        txt_description_details = findViewById(R.id.txt_description_details);
        btn_postuler = findViewById(R.id.btn_postuler);
        liste_candidates = findViewById(R.id.liste_candidates);
        btn_spprimer_offre = findViewById(R.id.btn_spprimer_offre);
        builder = new AlertDialog.Builder(OffreAffichageActivity.this);
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        profile = sharedPreferences.getString("profile","");

        if(profile.equals("admin"))
        {
            btn_postuler.setVisibility(View.GONE);
            btn_spprimer_offre.setVisibility(View.VISIBLE);
            liste_candidates.setVisibility(View.VISIBLE);
            liste_candidates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent i = new Intent(OffreAffichageActivity.this,CandidateActivity.class);
                    i.putExtra("offre_id", getIntent().getStringExtra("id"));
                    startActivity(i);
                }
            });

            btn_spprimer_offre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.setMessage("Voulez vous Vraiment Supprimer ce offre");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            supprimer_stage();

                        }
                    }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });

        }

        txt_titre_details.setText("Titre du poste "+getIntent().getStringExtra("titre"));
        txt_date_debut_details.setText(" Date début: "+getIntent().getStringExtra("date_debut"));
        txt_date_fin_details.setText(" Date fin: "+getIntent().getStringExtra("date_fin"));


        txt_description_details.setText("Description: \n  " + getIntent().getStringExtra("description"));
        Typeface home_txt_face = Typeface.createFromAsset(getAssets(), "fonts/dbplus.otf");
        txt_titre_details.setTypeface(home_txt_face);
        txt_date_debut_details.setTypeface(home_txt_face);

        txt_description_details.setTypeface(home_txt_face);
        btn_postuler.setTypeface(home_txt_face);

        btn_postuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postuler();
            }
        });

    }

    void postuler()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_postuler, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toasty.success(OffreAffichageActivity.this, response, Toast.LENGTH_LONG, true).show();
                finish();
                startActivity(new Intent(OffreAffichageActivity.this, OffreActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OffreAffichageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("offre_id",getIntent().getStringExtra("id"));
                params.put("candidate_id",user_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(OffreAffichageActivity.this);
        requestQueue.add(stringRequest);
    }


    void supprimer_stage()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_suppression, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toasty.success(OffreAffichageActivity.this, response, Toast.LENGTH_LONG, true).show();
                finish();
                startActivity(new Intent(OffreAffichageActivity.this, OffreActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OffreAffichageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("id",getIntent().getStringExtra("id"));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(OffreAffichageActivity.this);
        requestQueue.add(stringRequest);
    }
}
