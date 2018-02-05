资料
http://blog.csdn.net/u011244202/article/details/54809696


**真正运行程序时，还是会爆 535 认证失败。**
解决方案：因为JDK1.8中jre\lib\security中两个 jar 包替换的缘故。将下载后的local_policy.jar和US_export_policy.jar替换到JDK1.8的jre\lib\security文件夹即可。