package com.example.osrsstats.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osrsstats.ComponentUpdateCallback;
import com.example.osrsstats.R;
import com.example.osrsstats.adapters.RecyclerViewAdapter;
import com.example.osrsstats.constants.PlayerConstants;
import com.example.osrsstats.enums.PlayerMode;
import com.example.osrsstats.model.hiscore.HiScore;
import com.example.osrsstats.model.hiscore.HiScoreData;
import com.example.osrsstats.osrsApiClient.OsrsApiService;
import com.example.osrsstats.utils.HiscoreCreatorHelper;

import java.util.List;

@Deprecated
public class HiscoreActivity extends AppCompatActivity implements ComponentUpdateCallback {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscore);
        initRecycleView();
        OsrsApiService service = new OsrsApiService(this, PlayerMode.BASIC.name()); //TODO get mode from input
        service.getHiscoreByPlayerName(getIntent().getStringExtra(PlayerConstants.PLAYER_NAME));

    }

    @Override
    public void updateHiscoreData(List<Integer> hiscores) {
        List<HiScore> rightHiscore = HiscoreCreatorHelper.createHiscore(hiscores);
        HiScoreData hiScoreData = new HiScoreData();
        hiScoreData.addAll(rightHiscore);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(hiScoreData);
        recyclerView.setAdapter(adapter);
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
