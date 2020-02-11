package com.example.lib.generator;

import com.example.lib.constants.DimenTypes;
import com.example.lib.utils.MakeUtils;

import java.io.File;


public class DimenGenerator {

    /**
     *设置的宽
     */
    private static final int DESIGN_WIDTH = 375;

    /**
     *设计的高
     */
    private static final int DESIGN_HEIGHT = 667;

    public static void main(String[] args) {
        int smallest = DESIGN_WIDTH > DESIGN_HEIGHT ? DESIGN_HEIGHT : DESIGN_WIDTH;
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            File file = new File("");
            MakeUtils.makeAll(smallest, value, file.getAbsolutePath());
        }

    }

}
