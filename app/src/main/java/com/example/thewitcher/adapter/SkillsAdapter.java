package com.example.thewitcher.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.R;
import com.example.thewitcher.Entity.OwnedSkill;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {
    private List<OwnedSkill> skills;

    public SkillsAdapter(List<OwnedSkill> skills) {
        this.skills = skills;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_personnage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OwnedSkill skill = skills.get(position);
        holder.skillName.setText(skill.getOwnedSkillId());

    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public void updateSkills(List<OwnedSkill> skills) {
        List<OwnedSkill> newSkills = null;
        this.skills = newSkills;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView skillName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skillName = itemView.findViewById(R.id.skillsRecyclerView);
        }
    }
}

