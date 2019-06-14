package com.example.osrsstats.utils;

import java.util.ArrayList;
import java.util.List;


public class IntegerExtractorHelper {

    private IntegerExtractorHelper() {
    }

    /**
     * returns list of Integers extracted from given String
     * usage: OsrsApiService#getHiscoreByPlayerName(java.lang.String)
     * @param stringValue String including Integers
     * @return list of Integers
     */
    public static List<Integer> extractInt(String stringValue) {
        List<Integer> integerList = new ArrayList<>();
        for(String numString : stringValue.split("[^0-9]+")) {
            if(!numString.isEmpty()) {
                integerList.add(Integer.parseInt(numString));
            }
        }
        return integerList;
    }
}
