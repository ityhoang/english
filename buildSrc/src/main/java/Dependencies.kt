import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
  private const val kotlinStandardLib = "org.jetbrains.kotlin:kotlin-stdlib:1.7.0"
  private const val kotlinCoreKtx = "androidx.core:core-ktx:1.8.0"

  private const val legacySupportV4 = "androidx.legacy:legacy-support-v4:1.0.0"

  private const val uiEssentialAppCompat = "androidx.appcompat:appcompat:1.4.2"
  private const val uiEssentialConstraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
  private const val uiEssentialRecycleView = "androidx.recyclerview:recyclerview:1.2.1"
  private const val uiEssentialViewPager = "androidx.viewpager2:viewpager2:1.0.0"

  private const val lifecycleVersion = "2.5.1"
  private const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
  private const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
  private const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
  private const val lifecycleService = "androidx.lifecycle:lifecycle-service:$lifecycleVersion"
  private const val lifecycleViewModelState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"
  private const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
  private const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$lifecycleVersion"

  private  const val uiMaterialSupport = "com.google.android.material:material:1.6.1"

  private const val uiGlide = "com.github.bumptech.glide:glide:4.13.2"
  private const val uiGlideProcessor = "com.github.bumptech.glide:compiler:4.13.2"
  private const val circleImageView = "de.hdodenhof:circleimageview:3.1.0"

  private const val uiNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
  private const val uiNavigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

  const val diHiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidVersion}"
  private const val diHiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroidVersion}"
  private const val diHiltAndroidCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltAndroidVersion}"

  private const val retrofitAndroidVersion = "2.9.0"
  private const val okhttpLoggingVersion = "4.9.0"
  private const val networkRetrofit = "com.squareup.retrofit2:retrofit:"
  private const val networkRetrofitJSONConvert =
    "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
  private const val networkRetrofitLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpLoggingVersion"
  private const val adapterRxjava = "com.squareup.retrofit2:adapter-rxjava3:$retrofitAndroidVersion"
  private const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitAndroidVersion"

  private const val reactiveRxJava3 = "io.reactivex.rxjava3:rxjava:3.0.7"
  private const val reactiveRxAndroid = "io.reactivex.rxjava3:rxandroid:3.0.0"

  private const val roomVersion = "2.4.3"
  private const val roomDatabase = "androidx.room:room-runtime:$roomVersion"
  private const val roomKtxDatabase = "androidx.room:room-ktx:$roomVersion"
  const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
  private const val roomRxJava = "androidx.room:room-rxjava3:$roomVersion"

  private const val rxPermissions =  "com.github.tbruyelle:rxpermissions:0.12"

  private const val materialDesign = "com.google.android.material:material:1.6.1"

  private const val multiDex = "androidx.multidex:multidex:2.0.1"
  //test libs
  private const val junit = "junit:junit:4.13.2"
  private const val extJUnit = "androidx.test.ext:junit:1.1.3"
  private const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
  private const val adsIdentifier = "androidx.ads:ads-identifier:1.0.0-alpha04"
  private const val biometric =  "androidx.biometric:biometric-ktx:1.2.0-alpha03"
  //Eventbus
  private const val eventBusVersion = "3.3.1"
  private const val eventBus = "org.greenrobot:eventbus:$eventBusVersion"

  private const val swipeLayout = "com.github.zerobranch:SwipeLayout:1.3.1"

  private const val dotsIndicator = "com.tbuonomo:dotsindicator:4.3"
//  private const val barcode1 = "com.github.yuriy-budiyev:code-scanner:2.3.2"
  private const val barcode = "com.journeyapps:zxing-android-embedded:4.3.0"
  val appLibraries = arrayListOf<String>().apply {
    add(kotlinStandardLib)
    add(kotlinCoreKtx)
    add(legacySupportV4)
    add(uiEssentialAppCompat)
    add(uiEssentialConstraintLayout)
    add(uiEssentialRecycleView)
    add(uiEssentialViewPager)
    add(lifecycleViewModel)
    add(lifecycleLiveData)
    add(lifecycleRuntime)
    add(lifecycleService)
    add(lifecycleViewModelState)
    add(multiDex)
    add(uiMaterialSupport)
    add(uiGlide)
    add(uiNavigationFragment)
    add(uiNavigationUI)
    add(diHiltAndroid)
    add(networkRetrofit)
    add(networkRetrofitJSONConvert)
    add(networkRetrofitLogging)
    add(adapterRxjava)
    add(retrofitGson)
    add(reactiveRxJava3)
    add(reactiveRxAndroid)
    add(lifecycleViewModel)
    add(roomDatabase)
    add(roomKtxDatabase)
    add(roomRxJava)
    add(rxPermissions)
    add(materialDesign)
    add(adsIdentifier)
    add(biometric)
    add(lifecycleProcess)
    add(circleImageView)
    add(dotsIndicator)
    add(swipeLayout)
    add(barcode)
  }

  val androidLibrariesKapt = arrayListOf<String>().apply {
    add(lifecycleAnnotation)
    add(diHiltAndroidCompiler)
    add(uiGlideProcessor)
    add(roomCompiler)
  }

  val testLibraries = arrayListOf<String>().apply {
    add(junit)
  }
  val androidTestLibraries = arrayListOf<String>().apply {
    add(extJUnit)
    add(espressoCore)
//        add(composeUiTest)
  }

}
//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
  list.forEach { dependency ->
    add("kapt", dependency)
  }
}

fun DependencyHandler.implementation(list: List<String>) {
  list.forEach { dependency ->
    add("implementation", dependency)
  }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("androidTestImplementation", dependency)
  }
}

fun DependencyHandler.testImplementation(list: List<String>) {
  list.forEach { dependency ->
    add("testImplementation", dependency)
  }
}
