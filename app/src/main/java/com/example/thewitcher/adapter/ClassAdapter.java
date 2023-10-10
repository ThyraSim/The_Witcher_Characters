package com.example.thewitcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.classe.Classe;
import com.example.thewitcher.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private final Context mContext;
    private List<Classe> values;
    private final OnItemClickListener listener;

    public ClassAdapter(Context mContext, List<Classe> values, OnItemClickListener listener){
        this.mContext = mContext;
        this.values = values;
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_choose_class, parent, false);
        return new ClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position){
        Classe classe = values.get(position);
        holder.bind(classe);
    }

    @Override
    public int getItemCount() {
        int itemCount = values.size();
        return itemCount;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{
        TextView txtClassName, txtDefining, txtDescription, txtVigor, txtMagic;
        ImageView imageView2;

        public ClassViewHolder(@NonNull View itemView){
            super(itemView);
            txtClassName = itemView.findViewById(R.id.txtClassName);
            txtDefining = itemView.findViewById(R.id.txtDefining);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtVigor = itemView.findViewById(R.id.txtVigor);
            txtMagic = itemView.findViewById(R.id.txtMagic);
            imageView2 = itemView.findViewById(R.id.imageView2);

            txtDefining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(txtDescription.getVisibility() == View.GONE) {
                        txtDescription.setVisibility(View.VISIBLE); // Expand
                    } else {
                        txtDescription.setVisibility(View.GONE); // Collapse
                    }
                }
            });

            itemView.setOnClickListener( v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            });
        }

        public void bind(Classe classe){
            txtClassName.setText(classe.getName());
            txtDefining.setText(classe.getDefiningName() + " ↓ ");
            txtDescription.setText(classe.getDefiningDescription());
            txtVigor.setText(Integer.toString(classe.getVigor()));
            txtMagic.setText(classe.getMagicPerks());
            imageView2.setImageResource(R.drawable.aorus_chibi3);
        }
    }

    public void updateData(List<Classe> newValues){
        this.values = newValues;
        notifyDataSetChanged();
    }

}
