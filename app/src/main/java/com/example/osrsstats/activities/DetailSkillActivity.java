package com.example.osrsstats.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.R;
import com.example.osrsstats.constants.PlayerConstants;
import com.example.osrsstats.utils.BackgroundHelper;

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

        String skillName = getIntent().getStringExtra(PlayerConstants.SKILL_NAME);

        skillImage.setBackground(getBackground(skillName));
    }

    private Drawable getBackground(String skill) {
        return BackgroundHelper.getBackground(skill, this);
    }
}
