package com.example.lib.generator;

import com.example.lib.constants.DimenTypes;
import com.example.lib.utils.MakeUtils;

import java.io.File;


public class DimenGenerator {

    /**
     * 设置的宽
     * 1280dp 是已4k为参考
     */
    private static final int DESIGN_WIDTH = 1280;

    /**
     * 设计的高
     */
    private static final int DESIGN_HEIGHT = 720;

    public static void main(String[] args) {
        int smallest = DESIGN_WIDTH > DESIGN_HEIGHT ? DESIGN_HEIGHT : DESIGN_WIDTH;
        DimenTypes[] values = DimenTypes.values();
        for (DimenTypes value : values) {
            File file = new File("E://ScreenAdaptation");
            if (file.exists()) {
                MakeUtils.makeAll(smallest, value, file.getAbsolutePath());
            }
        }

    }

}
