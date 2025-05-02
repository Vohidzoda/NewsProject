plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("com.google.dagger.hilt.android") version "2.55" apply false

}

buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.55")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.9")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")

    }
}