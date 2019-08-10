package com.example.osrsstats;

import java.util.List;

/**
 * Sets values of component in Activity from other class
 */
public interface ComponentUpdateCallback {

   /**
    * Updates HiScoreData
    * @param hiscores hiscores to update
    */
   void updateHiscoreData(List<Integer> hiscores);
}
