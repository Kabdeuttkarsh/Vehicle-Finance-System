# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Retrofit
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.xml.stream.** { *; }
-keep class retrofit.** { *; }
-keep class com.google.appengine.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**

-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
#-dontnote retrofit2.Platform$IOS$MainThreadExecutor
## Platform used when running on Java 8 VMs. Will not be used at runtime.
#-dontwarn retrofit2.Platform$Java8
## Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# Add any classes the interact with gson
# the following line is for illustration purposes
-keep class com.solutionstouch.omsaifinance.Checksum
-keep class com.solutionstouch.omsaifinance.EmiScheduleActivity
-keep class com.solutionstouch.omsaifinance.JSONParser
-keep class com.solutionstouch.omsaifinance.LoginActivity
-keep class com.solutionstouch.omsaifinance.MainActivity
-keep class com.solutionstouch.omsaifinance.PayActivity
-keep class com.solutionstouch.omsaifinance.Repayment_Schedule
-keep class com.solutionstouch.omsaifinance.ui.dashboard.DashboardFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.emi.EmiFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.gallery.GalleryFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.home.HomeFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.notifications.NotificationsFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.Pay.PayFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.send.SendFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.share.ShareFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.slideshow.SlideshowFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.tools.ToolsFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.Transaction.TransactionFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.DashboardFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.documents.DocumentFragment
-keep class com.solutionstouch.omsaifinance.ui.dashboard.Splash
-keep class com.solutionstouch.omsaifinance.TransactionActivity


-keep class com.solutionstouch.omsaifinance.model.** { *; }
-keep class com.solutionstouch.omsaifinance.model.Colection { *; }
-keep class com.solutionstouch.omsaifinance.model.ColectionResponse { *; }
-keep class com.solutionstouch.omsaifinance.model.DocumentResult { *; }
-keep class com.solutionstouch.omsaifinance.model.Documents { *; }
-keep class com.solutionstouch.omsaifinance.model.GeneratePaytmChecksomeResponse { *; }
-keep class com.solutionstouch.omsaifinance.model.Loan { *; }
-keep class com.solutionstouch.omsaifinance.model.LoanResult { *; }
-keep class com.solutionstouch.omsaifinance.model.PaymentModal { *; }
-keep class com.solutionstouch.omsaifinance.model.Transaction { *; }
-keep class com.solutionstouch.omsaifinance.model.TransactionResult { *; }
-keep class com.solutionstouch.omsaifinance.model.User { *; }
-keep class com.solutionstouch.omsaifinance.model.UserResult { *; }


-keepclassmembers class com.paytm.pgsdk.PaytmWebView$PaytmJavaScriptInterface {
   public *;
}

