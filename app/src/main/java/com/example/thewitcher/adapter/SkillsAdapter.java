package com.example.thewitcher.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.OwnedSkillWithSkill;
import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.R;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.SkillViewHolder> {
    private List<OwnedSkillWithSkill> skills;

    public SkillsAdapter(List<OwnedSkillWithSkill> skills) {
        this.skills = skills;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_list, parent, false);
        return new SkillViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        OwnedSkillWithSkill skill = skills.get(position);
        holder.bind(skill.skill, skill.ownedSkill.getValue());
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public void updateSkills(List<OwnedSkillWithSkill> skills) {
//        List<OwnedSkillWithSkill> newSkills = null;
        this.skills = skills;
        notifyDataSetChanged();
    }


    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        TextView skillName;
        TextView skillLevel;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skillName = itemView.findViewById(R.id.tvSkillName);
            skillLevel = itemView.findViewById(R.id.txtLevel);

        }

        public void bind(Skill skill, int level){
            Log.d("Test", ""+level);
            skillName.setText(skill.getNomSkill());
        }
    }


}

