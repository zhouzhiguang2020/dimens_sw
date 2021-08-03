package com.example.laddingwu.adapterapplication.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Environment;

import com.apkfuns.logutils.LogUtils;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtils {
    public static File getAppRootPath(Context context) {
        if (context != null) {
            if (sdCardIsAvailable()) {
                LogUtils.e("可用");
                return Environment.getExternalStorageDirectory();
            } else {
                LogUtils.e("不可用");
                return context.getExternalCacheDir( );
            }
        }
        return null;
    }



    public static String getDiskCacheDir(Context context) {
        String cachePath = null;
        LogUtils.e("查看content："+context==null);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath;
    }
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            return sd.canWrite();
        } else {
            return false;
        }
    }

    /**
     * Copy to clipboard.
     */
    public static boolean copyTextToClipboard(Context context, CharSequence content) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            ClipData clipData = ClipData.newPlainText(content, content);
            clipboardManager.setPrimaryClip(clipData);
            return true;
        }
        return false;
    }
}
