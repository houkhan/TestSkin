package com.android.testskin;

import android.content.res.Resources;

/**
 * @Author :韩帅帅
 * @date : 7/6/21 - 2:39 PM
 * @Description : LoadResourceBean 实体类
 */
public class LoadResourceBean {
    /**
     * 资源
     */
    private Resources resources;
    /**
     *  包名
     */
    private String packageName;
    /**
     * 类加载器
     */
    private ClassLoader classLoader;

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
