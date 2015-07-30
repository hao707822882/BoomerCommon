package com.boom.maven;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;

/**
 * example
 * 属性maven.repo=c:\\
 * <p/>
 * <p/>
 * Created by z.mm on 2015/7/30 0030.
 */
@Component
public class CleanLastUpdate {

    @Value("${maven.repo}")
    private String propertyFileName;

    private static final String FILE_SUFFIX = "lastUpdated";
    private static final Logger _log = LoggerFactory.getLogger(CleanLastUpdate.class);

    /**
     * @param args
     */
    public void clean(String[] args) {
        File mavenRep = new File(propertyFileName);
        if (!mavenRep.exists()) {
            _log.warn("Maven repos is not exist.");
            return;
        }
        File[] files = mavenRep.listFiles((FilenameFilter) FileFilterUtils
                .directoryFileFilter());
        delFileRecr(files, null);
        _log.info("Clean lastUpdated files finished.");
    }

    private static void delFileRecr(File[] dirs, File[] files) {
        if (dirs != null && dirs.length > 0) {
            for (File dir : dirs) {
                File[] childDir = dir.listFiles((FilenameFilter) FileFilterUtils
                        .directoryFileFilter());
                File[] childFiles = dir.listFiles((FilenameFilter) FileFilterUtils
                        .suffixFileFilter(FILE_SUFFIX));
                delFileRecr(childDir, childFiles);
            }
        }
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.delete()) {
                    _log.info("File: [" + file.getName() + "] has been deleted.");
                }
            }
        }
    }

}

