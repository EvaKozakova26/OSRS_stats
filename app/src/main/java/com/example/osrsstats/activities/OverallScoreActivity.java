package com.example.osrsstats.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.ComponentUpdateCallback;
import com.example.osrsstats.R;
import com.example.osrsstats.constants.PlayerConstants;
import com.example.osrsstats.model.hiscore.HiScore;
import com.example.osrsstats.osrsApiClient.OsrsApiService;
import com.example.osrsstats.utils.HiscoreCreatorHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Overall Score Actvity - shows score - rank, level, experience points.
 * It shows either overall score or score for specific skill - depends on player input from {@link com.example.osrsstats.MainActivity}
 *
 * @author Eva Kozakova
 */
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

    private String skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_score);
        ButterKnife.bind(this);

        // get data from intent (from previous activity, in this case MainActivity)
        String playerMode = getIntent().getStringExtra(PlayerConstants.PLAYER_MODE);
        String playerName = getIntent().getStringExtra(PlayerConstants.PLAYER_NAME);
        skill = getIntent().getStringExtra(PlayerConstants.SKILL);

        // fetch data from API
        OsrsApiService service = new OsrsApiService(this, playerMode);
        service.getHiscoreByPlayerName(playerName);

        txtPlayerNameOverall.setText(playerName);
        String string = String.format(this.getResources().getString(R.string.player_mode_skill), playerMode, skill);
        txtPlayerModeOverall.setText(string);
    }

    @Override
    // called from OsrsApiService during fetching of data from API
    public void updateHiscoreData(List<Integer> hiscores) {
        HiScore hiscore = HiscoreCreatorHelper.getHiscoreBySkill(hiscores, skill);
        txtOverallRank.setText(String.valueOf(hiscore.getRank()));
        String level = String.format(this.getResources().getString(R.string.player_level), String.valueOf(hiscore.getLevel()));
        txtOverallLevel.setText(level);
        String score = String.format(this.getResources().getString(R.string.player_score), String.valueOf(hiscore.getScore()));
        txtOverallScore.setText(score);
    }
}
