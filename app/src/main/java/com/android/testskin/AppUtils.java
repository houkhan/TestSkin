package com.android.testskin;

import com.blankj.utilcode.util.SPUtils;

/**
 * @Author :韩帅帅
 * @Date :7/6/21 - 2:02 PM
 * @Description :工具类
 */
public class AppUtils {

    //设置SP中的资源路径
    public static void setResourcePath(String path) {
        SPUtils.getInstance().put(AppConstants.SP_RESOURCE_PATH, path);
    }

    //获取SP中的资源路径
    public static String getResourcePath() {
        return SPUtils.getInstance().getString(AppConstants.SP_RESOURCE_PATH, AppConstants.DEFAULT_RESOURCE_PATH);
    }
}
