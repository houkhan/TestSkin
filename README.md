# TestSkin
资源包分离。加载外部内存上的资源文件
# 资源包打包
 1.创建不带activity的安卓项目
 
 2.删除所有的依赖和不必要的代码，以防打包过大，关掉删除无用资源和混淆（我也上穿了我的资源包的项目SkinDemo，可以下载观看）
 3.进行builder->builder apk 进行打包
 4.什么也不添加观察打的资源包是否超过50k，超过则分析包的内容，减少的50k一下后进行下一步操作
 5.把资源导入后再次进行打包
 6.重命名资源包后缀名为skin（没有硬性要求，一般格式为skin或其他格式均可），修改目的是为了防止用户操作失误安装
 7.正常流畅是通股服务器进行下载资源包，我这里为了操作方便我是直接放到了sd卡跟目录下，正常上线为了防止用户误删除，自己选择安全的存放路径即可
# 欢迎留言讨论，谢谢
项目根据 https://blog.csdn.net/zyw0101/article/details/87939253 二次开发 谢谢作者
