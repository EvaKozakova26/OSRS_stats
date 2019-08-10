package com.example.osrsstats.activities;

import android.os.Bundle;

import com.example.osrsstats.ComponentUpdateCallback;
import com.example.osrsstats.R;
import com.example.osrsstats.adapters.RecyclerViewAdapter;
import com.example.osrsstats.model.hiscore.HiScore;
import com.example.osrsstats.model.hiscore.HiScoreData;
import com.example.osrsstats.osrsApiClient.OsrsApiService;
import com.example.osrsstats.utils.HiscoreCreatorHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HiscoreActivity extends AppCompatActivity implements ComponentUpdateCallback {
    private TextView mTextMessage;
    private RecyclerView recyclerView;

    //TODO mozna vyuziju, mozna ne.. :)
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_score);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiscore);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        initRecycleView();

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        OsrsApiService service = new OsrsApiService(this);
        service.getHiscoreByPlayerName(getIntent().getStringExtra("playerName"));

    }

    @Override
    public void updateHiscoreData(List<Integer> hiscores) {
        List<HiScore> rigthHiscore = HiscoreCreatorHelper.createHiscore(hiscores);
        HiScoreData hiScoreData = new HiScoreData();
        hiScoreData.addAll(rigthHiscore);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(hiScoreData);
        recyclerView.setAdapter(adapter);
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
