package com.example.thewitcher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class personnageAdapter extends ArrayAdapter<AccountPerso> {
    private Context mContext;
    private int resource;
    private ArrayList<AccountPerso> values;
    public personnageAdapter(Context mContext, int resource, ArrayList<AccountPerso> values) {
        super(mContext, resource, values);
        this.mContext = mContext;
        this.resource = resource;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AccountPerso aPerso = values.get(position);
        View itemView = LayoutInflater.from(mContext).inflate(resource, parent, false);

        TextView tvTitre = itemView.findViewById(R.id.tvPersoName);
        TextView tvDescription = itemView.findViewById(R.id.tvDescription);
        ImageView imagePost = itemView.findViewById(R.id.imagePerso);
        ImageView imageShowPopup = itemView.findViewById(R.id.imageShowPopup);

        tvTitre.setText(aPerso.getTitre());
        tvDescription.setText(aPerso.getDescription());
        imagePost.setImageResource(aPerso.getImage());

        imageShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mContext, imageShowPopup);
                popupMenu.getMenuInflater().inflate(R.menu.list_popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.itemShow) {
                            Intent intent = new Intent(mContext, PersonnageActivity.class);
                            intent.putExtra("titre", aPerso.getTitre());
                            mContext.startActivity(intent);
                        } else if (item.getItemId() == R.id.itemDelete) {
                            values.remove(position);
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        return itemView;
    }
}
