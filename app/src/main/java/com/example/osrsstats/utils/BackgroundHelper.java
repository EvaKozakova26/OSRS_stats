package com.example.osrsstats.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.osrsstats.R;
import com.example.osrsstats.enums.Skills;

public class BackgroundHelper {

    private BackgroundHelper() {
    }

    /**
     * Returns drawable icon for given skill
     * @param skill skill name
     * @param context activity context
     * @return skill icon
     */
    public static Drawable getBackground(String skill, Context context) {
        if (skill.equals(Skills.PRAYER.name())) {
            return ContextCompat.getDrawable(context, R.mipmap.ic_prayer);
        }
        if (skill.equals(Skills.AGILITY.name())) {
            return ContextCompat.getDrawable(context, R.mipmap.ic_agility);
        }

        return null;
    }
}
