// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    kotlin("kapt") version "2.0.0"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    id ("androidx.navigation.safeargs.kotlin") version  "2.5.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}