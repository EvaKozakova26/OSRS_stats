package com.example.osrsstats.utils;

import com.example.osrsstats.enums.Skills;
import com.example.osrsstats.model.hiscore.HiScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HiscoreCreatorHelper {

    private HiscoreCreatorHelper() {

    }

    /**
     * Transform hiscores values to HiScore objects
     * @param hiscoreValues list of Integers representing hiscore values without context
     * @return List<Hiscore> where values are linked to skills
     */
    public static List<HiScore> createHiscore(List<Integer> hiscoreValues) {
        List<HiScore> hiScores = new ArrayList<>();

        // for SKILLS
        for (int i = 0; i <= hiscoreValues.size() - 23; i+=3) {
            HiScore hiScore = new HiScore();
            hiScore.setSkill(Objects.requireNonNull(Skills.getValue(i)).name());
            hiScore.setRank(hiscoreValues.get(i));
            hiScore.setLevel(hiscoreValues.get(i+1));
            hiScore.setScore(hiscoreValues.get(i+2));
            hiScores.add(hiScore);
        }

        //for BOUNTY HUNTER and CLUE SROLLS
        for (int i = 72; i <= hiscoreValues.size() - 2; i+=2) {
            HiScore hiScore = new HiScore();
            hiScore.setSkill(Objects.requireNonNull(Skills.getValue(i)).name());
            hiScore.setRank(hiscoreValues.get(i));
            hiScore.setScore(hiscoreValues.get(i+1));
            hiScores.add(hiScore);
        }

        return hiScores;

    }
}
