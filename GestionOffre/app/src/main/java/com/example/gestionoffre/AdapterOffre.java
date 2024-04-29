package com.example.gestionoffre;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterOffre extends RecyclerView.Adapter<AdapterOffre.MyViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<Offre> offreList;




    public AdapterOffre(Context context, List<Offre> offreList)
    {

        this.offreList = offreList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.offre_element,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Offre offre = offreList.get(position);
        holder.txt_titre.setText(offre.getTitre());
        holder.txt_date_debut.setText("Date debut : " + offre.getDate_debut());
        holder.txt_date_fin.setText("Date Fin : " + offre.getDate_fin());


        Typeface home_txt_face = Typeface.createFromAsset(context.getAssets(), "fonts/dbplus.otf");
        holder.txt_titre.setTypeface(home_txt_face);
        holder.txt_date_debut.setTypeface(home_txt_face);
        holder.txt_date_fin.setTypeface(home_txt_face);
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, OffreAffichageActivity.class);
                i.putExtra("id", offre.getId());
                i.putExtra("titre", offre.getTitre());
                i.putExtra("categorie", offre.getCategorie());
                i.putExtra("description", offre.getDescription());
                i.putExtra("date_debut", offre.getDate_debut());
                i.putExtra("date_fin", offre.getDate_fin());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return offreList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_titre,txt_date_debut,txt_date_fin;
        LinearLayout linearlayout;
        ImageView item_image;


        public MyViewHolder(View itemView) {
            super(itemView);

            txt_titre = itemView.findViewById(R.id.txt_titre);
            txt_date_debut = itemView.findViewById(R.id.txt_date_debut);
            txt_date_fin = itemView.findViewById(R.id.txt_date_fin);
            linearlayout = itemView.findViewById(R.id.linearlayout);


        }
    }
    public void filterlist(List<Offre> newlist)
    {
        offreList = new ArrayList<>();
        offreList.addAll(newlist);
        notifyDataSetChanged();

    }
}
