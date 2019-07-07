package com.example.osrsstats;

/**
 * Sets values of component in Activity from other class
 */
public interface ComponentUpdateCallback {

   /**
    * Updates TextView
    * @param text text to update
    */
   void updateTextView(String text);
}
