plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion
    setFlavorDimensions(AppConfig.dimensions)

    defaultConfig {
        applicationId = AppConfig.appId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        multiDexEnabled = AppConfig.multiDexEnabled

        vectorDrawables.useSupportLibrary = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        android.buildFeatures.viewBinding = true
        android.buildFeatures.dataBinding = true
    }
    hilt {
        enableAggregatingTask = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes.add("META-INF/atomicfu.kotlin_module")
    }
    configurations.all {
        resolutionStrategy.force("junit:junit:4.13")
    }
    productFlavors {
        create("dev") {
            dimension = AppConfig.dimensions[0]
            applicationIdSuffix = ".dev"
            buildConfigField(
                "String",
                "API_URL",
                "\"http://116.105.217.31:8080/\""
            )
            resValue("string", "app_name", "English-dev")
        }
        create("staging") {
            dimension = AppConfig.dimensions[0]
            applicationIdSuffix = ".staging"
            buildConfigField(
                "String",
                "API_URL",
                "\"http://116.105.217.31:8080/\""
            )
            resValue("string", "app_name", "English-stg")
        }
        create("prod") {
            dimension = AppConfig.dimensions[0]
            applicationIdSuffix = ""
            buildConfigField(
                "String",
                "API_URL",
                "\"http://116.105.217.31:8080/\""
            )
            resValue("string", "app_name", "English")
        }
    }
}

dependencies {
    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //app libs
    implementation(AppDependencies.appLibraries)
    implementation("org.greenrobot:eventbus:3.3.1")
    implementation("com.airbnb.android:lottie:3.6.0")
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    //kapt
    kapt(AppDependencies.androidLibrariesKapt)
    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    annotationProcessor(AppDependencies.roomCompiler)
}
kapt {
    correctErrorTypes = true
}
