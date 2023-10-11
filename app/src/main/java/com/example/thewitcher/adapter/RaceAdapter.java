package com.example.thewitcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.race.Race;
import com.example.thewitcher.R;

import java.util.List;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {
    private final Context mContext;
    private List<Race> values;
    private final OnItemClickListener listener;

    public RaceAdapter(Context mContext, List<Race> values, OnItemClickListener listener){
        this.mContext = mContext;
        this.values = values;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public RaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_choose_race, parent, false);
        return new RaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RaceViewHolder holder, int position){
        Race race = values.get(position);
        holder.bind(race);
    }

    @Override
    public int getItemCount() {
        int itemCount = values.size();
        return itemCount;
    }

    public class RaceViewHolder extends RecyclerView.ViewHolder{
        TextView txtRaceName, txtPerk1, txtPerk2, txtPerk3, txtPerk4;
        ImageView imageView3;

        public RaceViewHolder(@NonNull View itemView){
            super(itemView);
            txtRaceName = itemView.findViewById(R.id.txtRaceName);
            txtPerk1 = itemView.findViewById(R.id.txtPerk1);
            txtPerk2 = itemView.findViewById(R.id.txtPerk2);
            txtPerk3 = itemView.findViewById(R.id.txtPerk3);
            txtPerk4 = itemView.findViewById(R.id.txtPerk4);
            imageView3 = itemView.findViewById(R.id.imageView3);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if(listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            });
        }

        public void bind(Race race){
            txtRaceName.setText(race.getName());
            txtPerk1.setText(race.getPerk1());
            txtPerk2.setText(race.getPerk2());
            txtPerk3.setText(race.getPerk3());
            if(race.getPerk4() != null){
                txtPerk4.setText(race.getPerk4());
            }else{
                txtPerk4.setVisibility(View.GONE);
            }
            String resourceName = race.getName().toLowerCase().replace(" ", "_");

            int resourceId = mContext.getResources().getIdentifier(
                    resourceName,
                    "drawable",
                    mContext.getPackageName()
            );

            if(resourceId != 0) {
                imageView3.setImageResource(resourceId);
            } else {
                imageView3.setImageResource(R.drawable.aorus_chibi3);
            }
        }
    }

    public void updateData(List<Race> newValues){
        this.values = newValues;
        notifyDataSetChanged();
    }
}
