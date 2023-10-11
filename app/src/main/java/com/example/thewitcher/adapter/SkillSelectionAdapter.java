package com.example.thewitcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.thewitcher.Entity.Skill;
import com.example.thewitcher.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillSelectionAdapter extends RecyclerView.Adapter<SkillSelectionAdapter.SkillSelectionViewHolder>{
    private final Context mContext;
    private List<Skill> values;
    private OnSkillLevelChangedListener listener;
    private Map<Skill, Integer> skillLevels = new HashMap<>();
    private int maxSkillPoints = 20;

    public interface OnSkillLevelChangedListener {
        void onSkillLevelChanged(int totalLevels);
    }

    public SkillSelectionAdapter(Context mContext, List<Skill> values){
        this.mContext = mContext;
        this.values = values;
        for(Skill skill : values) {
            skillLevels.put(skill, 0);
        }
    }

    public Map<Skill, Integer> getSkillLevels() {
        return skillLevels;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnSkillLevelChangedListener(OnSkillLevelChangedListener listener) {
        this.listener = listener;
    }

    public interface OnTotalSkillLevelChangeListener {
        void onTotalSkillLevelChange(int totalSkillLevel);
    }

    private OnTotalSkillLevelChangeListener onTotalSkillLevelChangeListener;

    public void setOnTotalSkillLevelChangeListener(OnTotalSkillLevelChangeListener listener){
        this.onTotalSkillLevelChangeListener = listener;
    }

    @Override
    public SkillSelectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill, parent, false);
        return new SkillSelectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SkillSelectionViewHolder holder, int position){
        Skill skill = values.get(position);
        holder.bind(skill);
    }

    @Override
    public int getItemCount() {
        int itemCount = values.size();
        return itemCount;
    }

    private int getTotalLevels() {
        int total = 0;
        for(int level : skillLevels.values()) {
            total += level;
        }
        return total;
    }

    public class SkillSelectionViewHolder extends RecyclerView.ViewHolder{
        TextView txtSkillName, txtLevel;
        Button btnPlus, btnMinus;
        int level = 0;
        public SkillSelectionViewHolder(View itemView){
            super(itemView);
            txtSkillName = itemView.findViewById(R.id.txtSkillChoice);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);



            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(level != 0) {
                        level -= 1;
                        txtLevel.setText(Integer.toString(level));
                    }
                }
            });
        }

        public void bind (Skill skill) {
            txtSkillName.setText(skill.getNomSkill());
            txtLevel.setText(Integer.toString(level));

            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentLevel = 0;
                    if(skillLevels.get(skill) != null)
                    {
                        currentLevel = skillLevels.get(skill);
                    }
                    // Your level-up logic here
                    if(calculateTotalSkillLevel() < maxSkillPoints) {
                        currentLevel += 1;
                    }
                    skillLevels.put(skill, currentLevel); // Update the level
                    txtLevel.setText(String.valueOf(currentLevel));
                    if (onTotalSkillLevelChangeListener != null) {
                        onTotalSkillLevelChangeListener.onTotalSkillLevelChange(calculateTotalSkillLevel());
                    }
                }
            });

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentLevel = skillLevels.get(skill);
                    if(currentLevel > 0) {
                        currentLevel -= 1;
                        skillLevels.put(skill, currentLevel);
                        txtLevel.setText(String.valueOf(currentLevel));
                        if (onTotalSkillLevelChangeListener != null) {
                            onTotalSkillLevelChangeListener.onTotalSkillLevelChange(calculateTotalSkillLevel());
                        }
                    }
                }
            });
        }
        private int calculateTotalSkillLevel() {
            return skillLevels.values().stream().mapToInt(Integer::intValue).sum();
        }
    }

    public void updateData(List<Skill> newValues){
        this.values = newValues;
        notifyDataSetChanged();
    }

}
