
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.kotlin.serialization)
        classpath(libs.ktorfit.gradle.plugin)
        classpath(libs.ksp.gradle.plugin)
        classpath(libs.ksp.symbol.processing.api)
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}