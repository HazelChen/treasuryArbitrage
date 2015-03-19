package edu.nju.treasuryArbitrage.model.tool;

import java.text.DecimalFormat;

/**
 * Created by Hazel on 2015/3/19.
 */
public class FormatTool {

    /**
     * Format a number with two bit.
     * For example, 1 is formatted to 01.
     */
    public static String twoNumberFormat(int i) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(i);
    }

}
