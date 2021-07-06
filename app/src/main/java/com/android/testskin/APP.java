package com.android.testskin;

import android.app.Application;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;

/**
 * @Author :韩帅帅
 * @Date :7/6/21 - 9:07 AM
 * @Description :入口
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SPStaticUtils.setDefaultSPUtils(SPUtils.getInstance(AppConstants.SP_NAME));
        LoadResourceUtil.getInstance().init(this, AppUtils.getResourcePath());
    }
}
