package com.haomiao.cloud.rx_zhihu;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.haomiao.cloud.rx_zhihu.utils.LocalDisplay;
import com.haomiao.cloud.rx_zhihu.utils.GlideUtils;
import com.haomiao.cloud.rx_zhihu.utils.SPUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;

/**
 * Project RX_Zhihu.
 * PackageName com.haomiao.cloud.rx_zhihu.
 * Created by Cloud on 16/5/18.
 * Instruction
 */
public class CApplication extends Application {


    private static CApplication INSTANCE;

    public static DiskLruCache getDiskLruCache() {
        return mDiskLruCache;
    }

    public static DiskLruCache mDiskLruCache;

    public static CApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GlideUtils.init(getApplicationContext());
        INSTANCE = this;
        SPUtils.init(getApplicationContext());
        LocalDisplay.init(getApplicationContext());
        try {
            File cacheDir = getDiskCacheDir(this, "ListData");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(this), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
