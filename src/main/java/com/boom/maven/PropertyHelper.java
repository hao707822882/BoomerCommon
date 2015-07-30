package com.boom.maven;

import java.util.ResourceBundle;

/**
 * 工具集加载
 * Created by z.mm on 2015/7/30 0030.
 */
public class PropertyHelper {

    /**
     *
     * 文件名，
     *
     */
    private String filename;

    public PropertyHelper(String config) {
        this.filename = config;
        ResourceBundle resource = ResourceBundle.getBundle(filename);
    }


}
