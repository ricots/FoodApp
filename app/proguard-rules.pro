# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\7__PROGRAMMS\Android\sdk/tools/proguard/proguard-android.txt
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

-keep class by.vshkl.android.foodapp.** { *; }
-dontobfuscate
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# Rethofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# SimpleXML
-dontwarn com.bea.xml.stream.**
-dontwarn org.simpleframework.xml.stream.**
-keep class org.simpleframework.xml.**{ *; }
-keepclassmembers,allowobfuscation class * {
    @org.simpleframework.xml.* <fields>;
    @org.simpleframework.xml.* <init>(...);
}

# DbFlow
-keep class * extends com.raizlabs.android.dbflow.config.DatabaseHolder { *; }

-libraryjars <java.home>/lib/rt.jar(java/**,javax/**)
