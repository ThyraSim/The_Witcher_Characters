package com.example.thewitcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.gear.Weapon;
import com.example.thewitcher.R;

import java.util.List;

public class WeaponAdapter  extends RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder> {
    private boolean showWeaponName = true;
    private final Context mContext;
    private List<Weapon> values;
    private final OnItemClickListener listener;

    public WeaponAdapter(Context mContext, List<Weapon> values, OnItemClickListener listener) {
        this.mContext = mContext;
        this.values = values;
        this.listener = listener;
    }
    public WeaponAdapter(Context mContext, List<Weapon> values, OnItemClickListener listener, boolean showWeaponName) {
        this.mContext = mContext;
        this.values = values;
        this.listener = listener;
        this.showWeaponName = showWeaponName;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public WeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_choose_weapon, parent, false);
        return new WeaponViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WeaponViewHolder holder, int position) {
        Weapon weapon = values.get(position);
        holder.bind(weapon);
    }

    @Override
    public int getItemCount() {
        int itemCount = values.size();
        return itemCount;
    }

    public class WeaponViewHolder extends RecyclerView.ViewHolder {
        ImageView imageWeapon;
        TextView txtWeaponName;
        TextView txtDamage;

        TextView txtHands;



        public WeaponViewHolder(View itemView) {
            super(itemView);
            imageWeapon = itemView.findViewById(R.id.imageWeapon);
            txtWeaponName = itemView.findViewById(R.id.txtWeaponName);
            txtDamage = itemView.findViewById(R.id.txtDamage);

            txtHands = itemView.findViewById(R.id.txtHands);

        }


        public void bind(final Weapon weapon) {
            if (weapon != null) {
                if (showWeaponName){
                    txtWeaponName.setText(weapon.getName());
                }else if(showWeaponName == false){
                txtWeaponName.setText("");
                }
                txtDamage.setText(weapon.getDamage() != null ? weapon.getDamage() : "0");
                txtHands.setText(weapon.getHands() != null ? String.valueOf(weapon.getHands()) : "Unknown");

                String resourceName = weapon.getName().toLowerCase().replace(" ", "_");

                int resourceId = mContext.getResources().getIdentifier(
                        resourceName,
                        "drawable",
                        mContext.getPackageName()
                );

                if (resourceId != 0) {
                    imageWeapon.setImageResource(resourceId);
                } else {
                    imageWeapon.setImageResource(R.drawable.arme);
                }

                if(listener != null){
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onItemClick(getAdapterPosition());
                        }
                    });
                }

            }
        }
    }


        public void updateData(List<Weapon> weaponArray) {
            this.values = weaponArray;
            notifyDataSetChanged();
        }

    }

