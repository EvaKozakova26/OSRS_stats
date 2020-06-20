package com.example.osrsstats.utils;

import com.example.osrsstats.enums.Skills;
import com.example.osrsstats.model.hiscore.HiScore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class HiscoreCreatorHelper {

    private static final Logger logger = Logger.getLogger(HiscoreCreatorHelper.class.getName());

    private HiscoreCreatorHelper() {

    }

    /**
     * Transform hiscores values to HiScore objects
     * @param hiscoreValues list of Integers representing hiscore values without context
     * @return List<Hiscore> where values are linked to skills
     */
    public static List<HiScore> createHiscore(List<Integer> hiscoreValues) {
        List<HiScore> hiScores = new ArrayList<>();

        // TODO refactor, new skills were added, better handling of NPE
        // for SKILLS
        for (int i = 0; i <= hiscoreValues.size() - 23; i+=3) {
            HiScore hiScore = new HiScore();
            try {
                hiScore.setSkill(Objects.requireNonNull(Skills.getValue(i)).name());
            } catch (NullPointerException npe) {
                logger.info("Skill is not available");
                System.out.println(npe.getMessage());
            } finally {
                hiScore.setRank(hiscoreValues.get(i));
                hiScore.setLevel(hiscoreValues.get(i+1));
                hiScore.setScore(hiscoreValues.get(i+2));
                hiScores.add(hiScore);
            }
        }

        //for BOUNTY HUNTER and CLUE SCROLLS
        for (int i = 72; i <= hiscoreValues.size() - 2; i+=2) {
            HiScore hiScore = new HiScore();
            try {
                hiScore.setSkill(Objects.requireNonNull(Skills.getValue(i)).name());
            } catch (NullPointerException npe) {
                logger.info("not available");
                System.out.println(npe.getMessage());
            } finally {
                hiScore.setRank(hiscoreValues.get(i));
                hiScore.setScore(hiscoreValues.get(i+1));
                hiScores.add(hiScore);
            }
        }

        return hiScores;

    }

    public static HiScore getOverallHiscore(List<Integer> hiscoreValues) {
        HiScore hiScore = new HiScore();
        hiScore.setSkill(Skills.OVERALL.name());
        hiScore.setRank(hiscoreValues.get(0));
        hiScore.setLevel(hiscoreValues.get(1));
        hiScore.setScore(hiscoreValues.get(2));
        return hiScore;
    }

    public static HiScore getHiscoreBySkill(List<Integer> hiscoreValues, String skill)  {
        Skills skills = Skills.valueOf(skill);
        int rank = hiscoreValues.get(skills.order);
        int level = hiscoreValues.get(skills.order + 1);
        int score = hiscoreValues.get(skills.order + 2);
        HiScore hiScore = new HiScore();
        hiScore.setRank(rank);
        hiScore.setLevel(level);
        hiScore.setScore(score);
        return hiScore;
    }
}
