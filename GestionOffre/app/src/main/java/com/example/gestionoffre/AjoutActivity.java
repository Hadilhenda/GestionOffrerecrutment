package com.example.gestionoffre;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AjoutActivity extends AppCompatActivity {
    DatePickerDialog picker;
    ImageView img_get_date_debut,img_get_date_fin;
    TextView txt_date_debut,txt_date_fin;
    Button btn_add ;
    EditText edt_titre,edt_description;

    String str_titre,str_description,str_date_debut,str_date_fin;
    String url = "http://192.168.43.149/offre_api/ajout_offre.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        img_get_date_debut = findViewById(R.id.img_get_date_debut);
        img_get_date_fin = findViewById(R.id.img_get_date_fin);
        txt_date_debut = findViewById(R.id.txt_date_debut);
        txt_date_fin = findViewById(R.id.txt_date_fin);
        btn_add = findViewById(R.id.btn_add);
        edt_titre = findViewById(R.id.edt_titre);
        edt_description = findViewById(R.id.edt_description);



        img_get_date_debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(AjoutActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txt_date_debut.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        img_get_date_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(AjoutActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                txt_date_fin.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_titre = edt_titre.getText().toString();
                str_date_debut = txt_date_debut.getText().toString();
                str_date_fin = txt_date_fin.getText().toString();
                str_description = edt_description.getText().toString();
                if(str_titre.equals("") || str_description.equals(""))
                {
                    Toasty.error(AjoutActivity.this, "Merci de remplire toutes les champs !", Toast.LENGTH_LONG, true).show();
                }else if(str_date_debut.equals("00-00-0000") ||str_date_fin.equals("00-00-0000") ){
                    Toasty.error(AjoutActivity.this, "Merci d'entrer une date valide", Toast.LENGTH_LONG, true).show();
                }else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toasty.success(AjoutActivity.this, response, Toast.LENGTH_LONG, true).show();
                            Intent i = new Intent(AjoutActivity.this,OffreActivity.class);
                            startActivity(i);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toasty.error(AjoutActivity.this, error.getMessage(), Toast.LENGTH_LONG, true).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> params = new HashMap<>();
                            params.put("titre",str_titre);
                            params.put("description",str_description);
                            params.put("date_debut",str_date_debut);
                            params.put("date_fin",str_date_fin);

                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(AjoutActivity.this);
                    requestQueue.add(stringRequest);

                }
            }
        });
    }
}
