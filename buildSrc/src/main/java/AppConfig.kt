//app level config constants
object AppConfig {
    const val compileSdk = 33
    const val minSdk = 19
    const val targetSdk = 33
    const val versionCode = 1

    const val versionName = "1.0.0"
    const val buildToolsVersion = "31.0.0"
    const val appId = "english.com"
    const val multiDexEnabled: Boolean = true


    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardConsumerRules = "consumer-rules.pro"
    const val proguardAndroidOptimize = "proguard-android-optimize.txt"
    val dimensions = listOf("default")
}