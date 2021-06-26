package com.example.laddingwu.adapterapplication.config;


import android.os.Environment;

import com.apkfuns.logutils.LogUtils;
import com.example.laddingwu.adapterapplication.application.DevelopmentTestingApplication;
import com.yanzhenjie.kalle.util.IOUtils;

import java.io.File;

/**
 * Created by ${zhou}.
 * User: Administrator
 * Date: 2019/4/29
 * Time: 17:55
 * 基础配置
 */
public class CommonConfig {
    private static CommonConfig sInstance;

    public static CommonConfig getInstance() {
        if (sInstance == null) {
            synchronized (CommonConfig.class) {
                if (sInstance == null) {
                    sInstance = new CommonConfig();
                }
            }
        }
        return sInstance;
    }


    /**
     * App root path.
     */
    public final String PATH_APP_ROOT;
    /**
     * Download.
     */
    public final String PATH_APP_DOWNLOAD;
    /**
     * Images.
     */
    public final String PATH_APP_IMAGE;
    /**
     * Cache root path.
     */
    public final String PATH_APP_CACHE;
    //voice
    public final String PATH_APP_VOICE;
    //video
    public final String PATH_APP_VIDEO;

    private CommonConfig() {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = Environment.getExternalStorageDirectory().getPath();
        } else {
            cachePath = DevelopmentTestingApplication.Companion.getContext().getCacheDir().getPath();
        }
        this.PATH_APP_CACHE = cachePath + File.separator + "Cache";
        this.PATH_APP_ROOT = com.example.laddingwu.adapterapplication.utils.FileUtils.getAppRootPath(DevelopmentTestingApplication.Companion.getInstance()).getAbsolutePath() + File.separator + "FEG" + File.separator + "snapshot";
        this.PATH_APP_DOWNLOAD = PATH_APP_ROOT + File.separator + "Download";
        this.PATH_APP_IMAGE = PATH_APP_ROOT + File.separator + "Images";
        this.PATH_APP_VOICE = PATH_APP_ROOT + File.separator + "voice";
        this.PATH_APP_VIDEO = PATH_APP_ROOT + File.separator + "video";

    }

    /**
     * Initialize file system for app.
     */
    public void initFileDir() {
        IOUtils.createFolder(PATH_APP_ROOT);
        IOUtils.createFolder(PATH_APP_DOWNLOAD);
        IOUtils.createFolder(PATH_APP_IMAGE);
        IOUtils.createFolder(PATH_APP_CACHE);
        LogUtils.e("缓存路径：" + new File(PATH_APP_CACHE).exists());
        LogUtils.e("缓存路径：" + PATH_APP_CACHE);
//
        IOUtils.createFolder(PATH_APP_VOICE);
        IOUtils.createFolder(PATH_APP_VIDEO);


    }
}