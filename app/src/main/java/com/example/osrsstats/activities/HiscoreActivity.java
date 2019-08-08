package com.example.osrsstats.activities;

import android.os.Bundle;

import com.example.osrsstats.ComponentUpdateCallback;
import com.example.osrsstats.R;
import com.example.osrsstats.adapters.RecyclerViewAdapter;
import com.example.osrsstats.osrsApiClient.OsrsApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HiscoreActivity extends AppCompatActivity implements ComponentUpdateCallback {
    private TextView mTextMessage;

    @BindView(R.id.txtHiscore1) TextView txtScore;

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
        ButterKnife.bind(this);

        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        OsrsApiService service = new OsrsApiService(this);
        service.getHiscoreByPlayerName(getIntent().getStringExtra("playerName"));

    }

    @Override
    public void updateTextView(String text) {
       txtScore.setText(text);
    }
}
