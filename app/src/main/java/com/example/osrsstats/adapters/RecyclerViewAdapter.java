package com.example.osrsstats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osrsstats.R;
import com.example.osrsstats.enums.Skills;
import com.example.osrsstats.model.hiscore.HiScore;
import com.example.osrsstats.model.hiscore.HiScoreData;

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
        HiscoreViewHolder viewHolder = new HiscoreViewHolder(view);
        return viewHolder;
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

    public class HiscoreViewHolder extends RecyclerView.ViewHolder {
        private TextView txtViewSkillName;
        private TextView txtViewRank;
        private TextView txtViewLevel;
        private TextView txtViewScore;

        private Context context;
        private View skillImage;


        public HiscoreViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewSkillName = itemView.findViewById(R.id.textViewSkillName);
            txtViewRank = itemView.findViewById(R.id.textViewRank);
            txtViewLevel = itemView.findViewById(R.id.textViewLevel);
            txtViewScore = itemView.findViewById(R.id.textViewScore);
            skillImage = itemView.findViewById(R.id.skillImage);

            context = itemView.getContext();
        }

        public void setHiscore(final HiScore hiscore) {
            txtViewSkillName.setText(hiscore.getSkill());
            txtViewRank.setText(String.valueOf(hiscore.getRank()));
            txtViewLevel.setText(String.valueOf(hiscore.getLevel()));
            txtViewScore.setText(String.valueOf(hiscore.getScore()));

            if (hiscore.getSkill().equals(Skills.PRAYER.name())) {
                skillImage.setBackground(ContextCompat.getDrawable(context, R.mipmap.ic_prayer));
            }

        }
    }
}
