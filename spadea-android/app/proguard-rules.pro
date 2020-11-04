# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\work\android_sdk\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


-keep class com.mob.**{*;}
-keep class cn.smssdk.**{*;}
-dontwarn com.mob.**
-dontwarn cn.smssdk.**


-ignorewarnings
-keep class javax.ws.rs.** { *; }
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }

#环信
-keep class org.xmlpull.** {*;}
-keep class com.hyphenate.** {*;}
-keep class com.hyphenate.chat.** {*;}
-dontwarn  com.hyphenate.**
-keep class org.jivesoftware.** {*;}
-keep class org.apache.** {*;}
#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}
-keep class com.superrtc.** { *; }


#-------------- okhttp3 start-------------
# OkHttp3
# https://github.com/square/okhttp
# okhttp
-keep class com.squareup.okhttp.* { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

# okhttp 3
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Okio
-dontwarn com.squareup.**
-dontwarn okio.**
-keep public class org.codehaus.* { *; }
-keep public class java.nio.* { *; }
#----------okhttp end--------------


-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties



-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod


##Glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.**{*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class jp.wasabeef.glide.transformations.**







############ --------以下通用--------###########
#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写

-dontusemixedcaseclassnames

#不去忽略非公共的库类

-dontskipnonpubliclibraryclasses

#优化  不优化输入的类文件

-dontoptimize

#预校验

-dontpreverify

#混淆时是否记录日志

-verbose

# 混淆时所采用的算法

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 保持哪些类不被混淆

-keep public class * extends android.app.Fragment

-keep public class * extends android.app.Activity

-keep public class * extends android.support.v7.app.AppCompatActivity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keep public class com.android.vending.licensing.ILicensingService

#如果有引用v4包可以添加下面这行

-keep public class * extends android.support.v4.app.Fragment


#忽略警告

-ignorewarning

##记录生成的日志数据,gradle build时在本项目根目录输出##

#apk 包内所有 class 的内部结构

-dump proguard/class_files.txt

#未混淆的类和成员

-printseeds proguard/seeds.txt

#列出从 apk 中删除的代码

-printusage proguard/unused.txt

#混淆前后的映射

-printmapping proguard/mapping.txt

########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

#如果引用了v4或者v7包

-dontwarn android.support.**

####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####

#保持自定义控件类不被混淆

-keepclassmembers class * extends android.app.Activity

-keep public class * extends android.view.View

#保持 Parcelable 不被混淆

-keep class * implements android.os.Parcelable

#保持 Serializable 不被混淆

-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆

-keepclassmembers class * implements java.io.Serializable

#保持枚举 enum 类不被混淆

#-keepclassmembers enum *



#不混淆资源类

-keepclassmembers class **.R$*


# 针对android-support-v4.jar的解决方案android.support.v4.app.Fragment

-dontwarn android.support.v4.**
#
-keep class android.support.v4.**  { *; }
#
-keep interface android.support.v4.app.** { *; }
#
-keep public class * extends android.support.v4.**
#
-keep public class * extends android.app.Fragmen

#talkingdata START
-dontwarn com.tendcloud.tenddata.**
-keep class com.tendcloud.** {*;}
-keep public class com.tendcloud.tenddata.** { public protected *;}
-keepclassmembers class com.tendcloud.tenddata.**{
public void *(***);
}
-keep class com.talkingdata.sdk.TalkingDataSDK {public *;}
-keep class com.apptalkingdata.** {*;}
#talkingdata  END

#talkingdata 广告 START
-keep class com.talkingdata.sdk.** {*;}
-keep class com.tendcloud.** {*;}
-keep public class com.tendcloud.** {  public protected *;}
#talkingdata  END

-dontwarn com.cmic.sso.sdk.**
-keep public class com.cmic.sso.sdk.**{*;}
-keep class cn.com.chinatelecom.account.api.**{*;}
-keep class com.netease.nis.quicklogin.entity.**{*;}
-keep class com.netease.nis.quicklogin.listener.**{*;}
-keep class com.netease.nis.quicklogin.QuickLogin{
    public <methods>;
    public <fields>;
}
-keep class com.netease.nis.quicklogin.helper.UnifyUiConfig{*;}
-keep class com.netease.nis.quicklogin.helper.UnifyUiConfig$Builder{
     public <methods>;
     public <fields>;
 }
-keep class com.netease.nis.quicklogin.utils.LoginUiHelper$CustomViewListener{
     public <methods>;
     public <fields>;
}
-dontwarn com.sdk.**
-keep class com.sdk.** { *;}
