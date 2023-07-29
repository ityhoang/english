// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}")
        classpath("com.google.gms:google-services:${Versions.googleServices}")
        classpath(AppDependencies.diHiltGradlePlugin)
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven (url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}