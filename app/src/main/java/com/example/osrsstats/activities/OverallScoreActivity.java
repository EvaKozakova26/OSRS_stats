package com.example.osrsstats.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.ComponentUpdateCallback;
import com.example.osrsstats.R;
import com.example.osrsstats.enums.PlayerMode;
import com.example.osrsstats.model.hiscore.HiScore;
import com.example.osrsstats.osrsApiClient.OsrsApiService;
import com.example.osrsstats.utils.HiscoreCreatorHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OverallScoreActivity extends AppCompatActivity implements ComponentUpdateCallback {

    @BindView(R.id.txtFieldOverallRank)
    TextView txtOverallRank;

    @BindView(R.id.txtFieldOverallLevel)
    TextView txtOverallLevel;

    @BindView(R.id.txtFieldOverallScore)
    TextView txtOverallScore;

    @BindView(R.id.txtPlayerNameOverall)
    TextView txtPlayerNameOverall;

    @BindView(R.id.txtPlayerModeOverall)
    TextView txtPlayerModeOverall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_score);
        ButterKnife.bind(this);
        OsrsApiService service = new OsrsApiService(this, getIntent().getStringExtra("playerMode"));
        service.getHiscoreByPlayerName(getIntent().getStringExtra("playerName"));
        txtPlayerNameOverall.setText(getIntent().getStringExtra("playerName"));
        txtPlayerModeOverall.setText(getIntent().getStringExtra("playerMode"));
    }

    @Override
    public void updateHiscoreData(List<Integer> hiscores) {
        HiScore hiscore = HiscoreCreatorHelper.getOverallHiscore(hiscores);
        txtOverallRank.setText(String.valueOf(hiscore.getRank()));
        txtOverallLevel.setText((hiscore.getLevel()) + " total levels");
        txtOverallScore.setText((hiscore.getScore()) + " total exps");
    }
}
