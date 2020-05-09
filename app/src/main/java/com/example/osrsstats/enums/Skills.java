package com.example.osrsstats.enums;

public enum Skills {

    OVERALL(0),
    ATTACK(3),
    DEFENCE(6),
    STRENGTH(9),
    HITPOINTS(12),
    RANGED(15),
    PRAYER(18),
    MAGIC(21),
    COOKING(24),
    WOODCUTTING(27),
    FLETCHING(30),
    FISHING(33),
    FIREMAKING(36),
    CRAFTING(39),
    SMITHING(42),
    MINING(45),
    HERBLORE(48),
    AGILITY(51),
    THIEVING(54),
    SLAYER(57),
    FARMING(60),
    RUNECRAFT(63),
    HUNTER(66),
    CONSTRUCTION(69),

    BOUNTY_HUNTER(72),
    BOUNTY_HUNTER_ROGUE(74),
    LMS(76),

    CLUE_SCROLLS_ALL(78),
    CLUE_SCROLLS_BEGINNER(80),
    CLUE_SCROLLS_EASY(82),
    CLUE_SCROLLS_MEDIUM(84),
    CLUE_SCROLLS_HARD(86),
    CLUE_SCROLLS_ELITE(88),
    CLUE_SCROLLS_MASTER(90);


    public final int order;


    public static Skills getValue(int value) {
        for(Skills e: Skills.values()) {
            if(e.order == value) {
                return e;
            }
        }
        return null; // not found TODO better handling to not return null or inform user about error (now might cause NPE)
    }

    Skills(int order) {
        this.order = order;
    }


}
