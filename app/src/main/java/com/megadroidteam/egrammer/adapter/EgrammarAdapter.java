package com.megadroidteam.egrammer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.megadroidteam.egrammer.R;
import com.megadroidteam.egrammer.model.Pharal;

import java.util.ArrayList;
import java.util.List;

public class EgrammarAdapter extends RecyclerView.Adapter<EgrammarAdapter.JabbariViewHolder> {

    Context context;
    List<Pharal> pharalList= new ArrayList<>();

    public EgrammarAdapter (Context context, List<Pharal> pharals){
        pharalList=pharals;
        this.context=context;
    }

    @NonNull
    @Override
    public JabbariViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.phral_row,null);

        return new JabbariViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JabbariViewHolder jabbariViewHolder, int i) {

        Pharal pharal=pharalList.get(i);
        jabbariViewHolder.txt_data.setText(pharal.getTitle());

    }

    @Override
    public int getItemCount() {
        return pharalList.size();
    }

    public class JabbariViewHolder extends RecyclerView.ViewHolder{

        TextView txt_data;

        public JabbariViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_data=itemView.findViewById(R.id.txt_data);
        }
    }


}
