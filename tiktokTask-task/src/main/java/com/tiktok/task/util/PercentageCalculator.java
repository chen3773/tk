package com.tiktok.task.util;

import java.text.DecimalFormat;

public class PercentageCalculator {
    public static double calculatePercentage(int total, int completed) {
        if (total <= 0) {
            throw new IllegalArgumentException("Total must be a positive number");
        }

        if (completed < 0 || completed > total) {
            throw new IllegalArgumentException("Completed must be a non-negative number and less than or equal to total");
        }

        double percentage = ((double) completed / total) * 100;

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(percentage));
    }
}
