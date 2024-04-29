package com.example.gestionoffre;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdapterCandidate extends RecyclerView.Adapter<AdapterCandidate.MyViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private List<Candidate> candidateList;




    public AdapterCandidate(Context context, List<Candidate> candidateList)
    {

        this.candidateList = candidateList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.candidate_element,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Candidate candidate = candidateList.get(position);
        holder.txt_nom.setText("Nom : " +candidate.getNom());
        holder.txt_email.setText("E-mail : " + candidate.getEmail());
        holder.txt_prenom.setText("Prenom : " + candidate.getPrenom());
        holder.txt_telephone.setText("Telephone : " + candidate.getTelephone());


        Typeface home_txt_face = Typeface.createFromAsset(context.getAssets(), "fonts/dbplus.otf");
        holder.txt_nom.setTypeface(home_txt_face);
        holder.txt_email.setTypeface(home_txt_face);
        holder.txt_prenom.setTypeface(home_txt_face);
        holder.txt_telephone.setTypeface(home_txt_face);
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(candidate.getCv()));
                    context.startActivity(browserIntent);
                } catch (Exception e) {
                    Toast.makeText(context, "Link Error",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_nom,txt_prenom,txt_email,txt_telephone;
        LinearLayout linearlayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            txt_nom = itemView.findViewById(R.id.txt_nom);
            txt_prenom = itemView.findViewById(R.id.txt_prenom);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_telephone = itemView.findViewById(R.id.txt_telephone);
            linearlayout = itemView.findViewById(R.id.linearlayoutcandidate);


        }
    }
    public void filterlist(List<Candidate> newlist)
    {
        candidateList = new ArrayList<>();
        candidateList.addAll(newlist);
        notifyDataSetChanged();

    }
}
