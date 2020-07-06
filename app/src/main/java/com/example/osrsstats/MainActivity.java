package com.example.osrsstats;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.osrsstats.activities.OverallScoreActivity;
import com.example.osrsstats.constants.ErrorConstants;
import com.example.osrsstats.constants.PlayerConstants;
import com.example.osrsstats.enums.PlayerMode;
import com.example.osrsstats.enums.Skills;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private PlayerMode playerMode;
    private Skills skill;

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stats:
                    mTextMessage.setText(R.string.title_stats);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_grand_exchange);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @BindView(R.id.btnScore) Button btnGetScore;
    @BindView(R.id.btnBasicMode) Button btnBasicMode;
    @BindView(R.id.btnIronmanMode) Button btnIronmanMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btnBasicMode.setAlpha(0.5f);
        btnIronmanMode.setAlpha(0.5f);

        btnBasicMode.setOnClickListener(view -> {
            playerMode = PlayerMode.BASIC;
            btnBasicMode.setAlpha(1);
            btnIronmanMode.setAlpha(0.5f);
        });
        btnIronmanMode.setOnClickListener(view -> {
            playerMode = PlayerMode.IRONMAN;
            btnBasicMode.setAlpha(0.5f);
            btnIronmanMode.setAlpha(1);
        });

        btnGetScore.setOnClickListener(view -> handleDataForwarding());
        initSpinnerSkills();
    }

    private void handleDataForwarding() {
        Intent intent = new Intent(MainActivity.this, OverallScoreActivity.class);
        EditText txtPlayerName = findViewById(R.id.txtPlayerName);
        if (txtPlayerName.getText().toString().equals("")) {
            btnGetScore.setFocusableInTouchMode(true);
            btnGetScore.setFocusable(true);
            btnGetScore.setError(ErrorConstants.ERR_EMPTY_NAME);
        } else {
            intent.putExtra(PlayerConstants.PLAYER_NAME, txtPlayerName.getText().toString());
            if (playerMode != null) {
                intent.putExtra(PlayerConstants.PLAYER_MODE, playerMode.name());
                intent.putExtra(PlayerConstants.SKILL, skill.toString());
                startActivity(intent);
            } else {
                btnGetScore.setFocusableInTouchMode(true);
                btnGetScore.setFocusable(true);
                btnGetScore.setError(ErrorConstants.ERR_EMPTY_MODE);
            }
        }
    }

    private void initSpinnerSkills() {
        Spinner spinner = findViewById(R.id.spinnerSkills);
        String[] items = this.getResources().getStringArray(R.array.skills_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object itemAtPosition = adapterView.getItemAtPosition(i);
        skill = Skills.valueOf(itemAtPosition.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
