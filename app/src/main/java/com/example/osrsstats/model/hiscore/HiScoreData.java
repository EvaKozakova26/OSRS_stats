package com.example.osrsstats.model.hiscore;

import java.util.ArrayList;
import java.util.List;

public class HiScoreData {
    private List<HiScore> hiScoreList = new ArrayList<>();

    public List<HiScore> getAll() {
        return hiScoreList;
    }

    public void add(String skillName, int rank, int level, int score) {
        HiScore hiScore = new HiScore();
        hiScore.setSkill(skillName);
        hiScore.setRank(rank);
        hiScore.setLevel(level);
        hiScore.setScore(score);
        hiScoreList.add(hiScore);
    }

    public void addAll(List<HiScore> hiScores) {
        hiScoreList.addAll(hiScores);
    }
}
