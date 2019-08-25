package com.example.osrsstats.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.osrsstats.R;
import com.example.osrsstats.enums.Skills;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailSkillActivity extends AppCompatActivity {

    @BindView(R.id.txtSkillImage)
    TextView skillImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_skill);
        ButterKnife.bind(this);

        String skillName = getIntent().getStringExtra("skillName");

        skillImage.setBackground(getBackground(skillName));
    }

    private Drawable getBackground(String skill) {
        if (skill.equals(Skills.PRAYER.name())) {
            return ContextCompat.getDrawable(this, R.mipmap.ic_prayer);
        }
        if (skill.equals(Skills.AGILITY.name())) {
            return ContextCompat.getDrawable(this, R.mipmap.ic_agility);
        }

        return null;
    }
}
