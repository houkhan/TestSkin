package com.android.testskin;

import com.blankj.utilcode.util.SDCardUtils;

/**
 * @Author :韩帅帅
 * @date : 7/6/21 - 1:58 PM
 * @Description : 全局常量
 */
public interface AppConstants {
    /**
     * SharedPreferences名字
     */
    String SP_NAME = "load_resource";
    /**
     * 资源路径SP key
     */
    String SP_RESOURCE_PATH = "sp_resource_path";

    /**
     * 默认的资源包路径
     */
    String DEFAULT_RESOURCE_PATH = SDCardUtils.getSDCardPathByEnvironment() + "/test.skin";
}
