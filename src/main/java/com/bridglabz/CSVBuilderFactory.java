package com.bridglabz;

import customcsv.util.ICSVBuilder;
import customcsv.util.OpenCSVBuilder;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
