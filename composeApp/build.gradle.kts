import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("kotlinx-serialization")
    id("com.google.devtools.ksp") version "1.9.22-1.0.16"
    id("de.jensklingenberg.ktorfit") version "1.12.0"
    id("com.google.gms.google-services")
}

val ktorVersion = "2.3.6"
val ktorfitVersion = "1.12.0"

kotlin {

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    // Init lib
    sourceSets {
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.ui.tooling.preview.v163)
            implementation(libs.android.driver)

            // Import the BoM for the Firebase platform
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:32.8.0"))

            // Add the dependency for the Firebase Authentication library
            // When using the BoM, you don't specify versions in Firebase library dependencies
            implementation("com.google.firebase:firebase-auth")

            // Also add the dependency for the Google Play services library and specify its version
            implementation("com.google.android.gms:play-services-auth:21.0.0")

            // Log helper
            implementation("com.jakewharton.timber:timber:5.0.1")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.animation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.runtime)

            // Kotlin
            implementation(libs.kotlin.stdlib)

            // Ktor
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.serialization.v236)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // Ktorfit
            implementation(libs.ktorfit.lib)

            // Navigation
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)
            implementation(libs.voyager.bottom.sheet.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)

            // Log
            implementation(libs.kermit)

            // Compose ImageLoader
            implementation(libs.image.loader)
            api(libs.image.loader.extension.moko.resources)

            // Firebase
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.firestore)
            implementation(libs.firebase.common)
            implementation(libs.firebase.auth)
            implementation(libs.firebase.storage)
            implementation(libs.firebase.database)
        }
        iosMain.dependencies {
            implementation(libs.native.driver)
        }
    }
}

android {
    namespace = "com.dongnh.musicplayer"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.dongnh.musicplayer"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    buildFeatures {
        compose = true
    }
    signingConfigs {
        getByName("debug") {
            storeFile = file("musicplayer")
            storePassword = "MusicPlayer001@"
            keyAlias = "MusicPlayerAlias"
            keyPassword = "MusicPlayer001@"
        }
    }
    buildTypes {
        getByName("debug") {
            //applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.txt")
        }
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        debugImplementation(platform(libs.androidx.compose.bom))
        debugApi(compose.uiTooling)
        implementation(libs.firebase.common.ktx)
    }
}

dependencies {
    with("de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion") {
        add("kspCommonMainMetadata", this)
        add("kspAndroid", this)
        add("kspIosX64", this)
        add("kspIosArm64", this)
        add("kspIosSimulatorArm64", this)
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
        allWarningsAsErrors = false
    }
}

task("testClasses").doLast {
    println("This is a dummy testClasses task")
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().all {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

