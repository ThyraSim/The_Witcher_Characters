package com.example.thewitcher.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.PersonnageDetails;
import com.example.thewitcher.PersonnageActivity;
import com.example.thewitcher.R;

import java.util.List;

public class personnageAdapter extends RecyclerView.Adapter<personnageAdapter.PersonnageViewHolder> {
    private final Context mContext;
    private List<PersonnageDetails> values;

    private final OnItemClickListener listener;

    public personnageAdapter(Context mContext, List<PersonnageDetails> values, OnItemClickListener listener) {
        this.mContext = mContext;
        this.values = values;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public PersonnageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_personnage, parent, false);
        return new PersonnageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonnageViewHolder holder, int position) {
        PersonnageDetails personnageDetails = values.get(position);
        holder.bind(personnageDetails);

    }

    @Override
    public int getItemCount() {
        int itemCount = values.size();
        Log.d("DEBUG", "Item count is: " + itemCount);
        return itemCount;
    }

    public class PersonnageViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitre, tvDescription;
        ImageView imagePost, imageShowPopup;

        public PersonnageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitre = itemView.findViewById(R.id.tvPersoName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imagePost = itemView.findViewById(R.id.imagePerso);
            imageShowPopup = itemView.findViewById(R.id.imageShowPopup);

            // Use the listener defined in your adapter
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(position);
                }
            });
        }


        public void bind(PersonnageDetails personnageDetails) {
            tvTitre.setText(personnageDetails.getPersonnage().getName());
            tvDescription.setText(personnageDetails.getClasse().getName());
            imagePost.setImageResource(R.drawable.aorus_chibi3);
            imageShowPopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(mContext, imageShowPopup);
                    popupMenu.getMenuInflater().inflate(R.menu.list_popup_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.itemShow) {
                                Intent intent = new Intent(mContext, PersonnageActivity.class);
                                intent.putExtra("titre", personnageDetails.getPersonnage().getName());
                                mContext.startActivity(intent);
                            } else if (item.getItemId() == R.id.itemDelete) {
                                values.remove(getAdapterPosition());
                                notifyDataSetChanged();
                            }
                            return true;
                        }
                    });
                    popupMenu.show();
                }
            });
        }
    }

    public void updateData(List<PersonnageDetails> newValues){
        this.values = newValues;
        notifyDataSetChanged();
    }
}
