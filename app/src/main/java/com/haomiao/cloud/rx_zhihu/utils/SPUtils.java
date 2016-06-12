package com.haomiao.cloud.rx_zhihu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;

import com.haomiao.cloud.rx_zhihu.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Cloud on 16/5/10.
 */
public class SPUtils {
    private static SPUtils instance;
    private static Context mContext;
    public static final String KEY_THEME = "KEY_THEME";
    public static final int THEME_LIGHT = 10;
    public static final int THEME_DARK = 20;
    private SharedPreferences sp = mContext.getSharedPreferences("RXZhiHu", 0);
    private SharedPreferences.Editor edit =  sp.edit();

    public boolean isFirstIn() {
        return sp.getBoolean("FirstIn", true);
    }

    public void setFirstIn(boolean firstIn) {
        edit.putBoolean("FirstIn", firstIn);
        edit.apply();
    }

    @IntDef({THEME_DARK, THEME_LIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Theme {}

    public SPUtils() {
    }

    public static void init(Context context){
        mContext = context;
    }
    public static SPUtils getInstance() {
        if (null == instance) {
            instance = new SPUtils();
        }
        return instance;
    }


    public  int getTheme(){
        int themeId = 0;
        switch (sp.getInt(KEY_THEME, THEME_LIGHT)){
            case THEME_DARK:
                themeId = R.style.DarkTheme;
                break;
            case THEME_LIGHT:
                themeId = R.style.LightTheme;
                break;
            default:
                break;
        }
        return themeId;
    }

    public  void setTheme(@Theme int theme){
        edit.putInt(KEY_THEME, theme);
        edit.apply();
    }


}

