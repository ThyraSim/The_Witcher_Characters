package com.example.thewitcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.gear.Armor;
import com.example.thewitcher.R;

import java.util.List;

public class ArmorAdapter extends RecyclerView.Adapter<ArmorAdapter.ArmorViewHolder> {
    private final Context mContext;
    private List<Armor> values;
    private final OnItemClickListener listener;

    public ArmorAdapter(Context mContext, List<Armor> values, OnItemClickListener listener){
        this.mContext = mContext;
        this.values = values;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ArmorViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_choose_armor, parent, false);
        return new ArmorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArmorViewHolder holder, int position){
        Armor armor = values.get(position);
        holder.bind(armor);
    }

    @Override
    public int getItemCount() {
        int itemCount = values.size();
        return itemCount;
    }

    public class ArmorViewHolder extends RecyclerView.ViewHolder{
        TextView txtArmorName, txtSP;
        ImageView imageArmor;

        public ArmorViewHolder(View itemView){
            super(itemView);
            txtArmorName = itemView.findViewById(R.id.txtArmorName);
            txtSP = itemView.findViewById(R.id.txtSP);
            imageArmor = itemView.findViewById(R.id.imageArmor);
        }

        public void bind(final Armor armor){
            txtArmorName.setText(armor.getName());
            txtSP.setText(Integer.toString(armor.getSp()));

            String resourceName = armor.getName().toLowerCase().replace(" ", "_");

            int resourceId = mContext.getResources().getIdentifier(
                    resourceName,
                    "drawable",
                    mContext.getPackageName()
            );

            if(resourceId != 0) {
                imageArmor.setImageResource(resourceId);
            } else {
                imageArmor.setImageResource(R.drawable.aorus_chibi3);
            }

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public void updateData(List<Armor> armors){
        this.values = armors;
        notifyDataSetChanged();
    }
}
