package com.android.testskin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @Author :韩帅帅
 * @date : 7/6/21 - 2:38 PM
 * @Description : 资源加载工具类
 */
public class LoadResourceUtil {
    private static final String TAG = LoadResourceUtil.class.getSimpleName();
    private static final String RESOURCE_TYPE_DRAWABLE = "drawable";// 图片
    private static final String RESOURCE_TYPE_STRING = "string";// 文字
    private static final String RESOURCE_TYPE_COLOR = "color";// 颜色

    private Context mContext;
    private String mDexDir;// 资源路径
    private static LoadResourceBean mResourceLoadBean;// 资源对象

    /**
     * 静态内部类
     */
    private static class LoadResourceUtilHolder {
        private static final LoadResourceUtil INSTANCE = new LoadResourceUtil();
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static final LoadResourceUtil getInstance() {
        return LoadResourceUtilHolder.INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     * @param resourcePath 本地资源路径
     */
    public void init(Context context, String resourcePath) {
        Log.e(TAG, "init");
        mContext = context.getApplicationContext();
        File dexDir = mContext.getDir("dex", Context.MODE_PRIVATE);
        if (!dexDir.exists()) {
            dexDir.mkdir();
        }
        mDexDir = dexDir.getAbsolutePath();
        mResourceLoadBean = getResourceLoad(resourcePath);
    }

    /**
     * 加载未安装资源包
     *
     * @param resourcePath 未安装资源包
     * @return
     */
    public LoadResourceBean getResourceLoad(String resourcePath) {
        Log.e(TAG, "getLoadResource");
        LoadResourceBean resourceLoadBean = null;
        // 获取未安装APK的PackageInfo
        PackageInfo info = queryPackageInfo(resourcePath);
        if (info != null) {
            // 获取成功
            try {
                // 创建AssetManager实例
                AssetManager assetManager = AssetManager.class.newInstance();
                Class cls = AssetManager.class;
                Method method = cls.getMethod("addAssetPath", String.class);
                // 反射设置资源加载路径
                method.invoke(assetManager, resourcePath);
                // 构造出正确的Resource
                Resources resources = new Resources(assetManager, mContext.getResources().getDisplayMetrics(),
                        mContext.getResources().getConfiguration());
                resourceLoadBean = new LoadResourceBean();
                resourceLoadBean.setResources(resources);
                resourceLoadBean.setPackageName(info.packageName);
                // 设置正确的类加载器, 因为需要去加载R文件
                resourceLoadBean.setClassLoader(new DexClassLoader(resourcePath, mDexDir, null,
                        mContext.getClassLoader()));
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TTT", e.toString());
            }

        }
        return resourceLoadBean;
    }

    /**
     * 重新加载未安装应用资源包
     *
     * @param resourcePath 未安装资源包路径
     * @return
     */
    public void setLoadResource(String resourcePath) {
        // 保存资源包路径 下次进入程序默认加载
        AppUtils.setResourcePath(resourcePath);
        mResourceLoadBean = getResourceLoad(resourcePath);
    }

    /**
     * 获取未安装资源包信息
     *
     * @param resourcePath 未安装资源包路径
     * @return
     */
    private PackageInfo queryPackageInfo(String resourcePath) {
        return mContext.getPackageManager().getPackageArchiveInfo(resourcePath, PackageManager.GET_ACTIVITIES);
    }

    /**
     * 获取未安装资源Drawable
     *
     * @param fieldName 资源名
     * @return
     */
    public Drawable getDrawable(String fieldName) {
        Drawable drawable = null;
        try {
            int resourceID = mResourceLoadBean.getResources().getIdentifier(fieldName, RESOURCE_TYPE_DRAWABLE, mResourceLoadBean.getPackageName());
            if (mResourceLoadBean != null) {
                drawable = mResourceLoadBean.getResources().getDrawable(resourceID);
            }
        } catch (Resources.NotFoundException exception) {
            drawable = null;
        }
        return drawable;
    }

    /**
     * 获取未安装资源String
     *
     * @param fieldName 资源名
     * @return
     */
    public String getString(String fieldName) {
        String string = null;
        try {
            int resourceID = mResourceLoadBean.getResources().getIdentifier(fieldName, RESOURCE_TYPE_STRING, mResourceLoadBean.getPackageName());
            if (mResourceLoadBean != null) {
                string = mResourceLoadBean.getResources().getString(resourceID);
            }
        } catch (Resources.NotFoundException exception) {
            string = null;
        }
        return string;
    }

    /**
     * 获取未安装资源color
     *
     * @param fieldName 资源名
     * @return
     */
    public int getColor(String fieldName) {
        int color = 0;
        try {
            int resourceID = mResourceLoadBean.getResources().getIdentifier(fieldName, RESOURCE_TYPE_COLOR, mResourceLoadBean.getPackageName());
            if (mResourceLoadBean != null) {
                color = mResourceLoadBean.getResources().getColor(resourceID);
            }
        } catch (Resources.NotFoundException exception) {
            color = 0;
        }
        return color;
    }
}
