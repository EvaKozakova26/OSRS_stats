package com.example.osrsstats.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osrsstats.R;
import com.example.osrsstats.activities.DetailSkillActivity;
import com.example.osrsstats.constants.PlayerConstants;
import com.example.osrsstats.model.hiscore.HiScore;
import com.example.osrsstats.model.hiscore.HiScoreData;
import com.example.osrsstats.utils.BackgroundHelper;

@Deprecated
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HiscoreViewHolder> {
    private HiScoreData hiScoreData;

    public RecyclerViewAdapter(HiScoreData hiScoreData) {
        this.hiScoreData = hiScoreData;
    }

    @NonNull
    @Override
    public HiscoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_hiscore, null);
        return new HiscoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HiscoreViewHolder holder, int position) {
        HiScore hiScore = hiScoreData.getAll().get(position);
        holder.setHiscore(hiScore);

    }

    @Override
    public int getItemCount() {
        return hiScoreData.getAll().size();
    }

    public static class HiscoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtViewSkillName;
        private TextView txtViewRank;
        private TextView txtViewLevel;
        private TextView txtViewScore;

        private Context context;
        private View skillImage;
        String skillName;


        HiscoreViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewSkillName = itemView.findViewById(R.id.textViewSkillName);
            txtViewRank = itemView.findViewById(R.id.textViewRank);
            txtViewLevel = itemView.findViewById(R.id.textViewLevel);
            txtViewScore = itemView.findViewById(R.id.textViewScore);
            skillImage = itemView.findViewById(R.id.skillImage);

            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        void setHiscore(final HiScore hiscore) {
            txtViewSkillName.setText(hiscore.getSkill());
            txtViewRank.setText(String.valueOf(hiscore.getRank()));
            txtViewLevel.setText(String.valueOf(hiscore.getLevel()));
            txtViewScore.setText(String.valueOf(hiscore.getScore()));
            skillImage.setBackground(getBackground(hiscore.getSkill()));
            skillName = hiscore.getSkill();
        }

        private Drawable getBackground(String skill) {
            return BackgroundHelper.getBackground(skill, context);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailSkillActivity.class);
            intent.putExtra(PlayerConstants.SKILL_NAME, skillName);
            context.startActivity(intent);
        }
    }
}
