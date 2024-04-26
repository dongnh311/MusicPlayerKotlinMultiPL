This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.
  - `composeResource` : All res, using "gradle generateComposeResClass" to reload

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/desktopMain` contains desktop applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your desktop app.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

This is a demo application written in multi platform Kotlin.
Use compose UI to implement views for all 3 platforms. All logic and view handling are implemented in composeApp, only special logic will be handled separately depending on the platform.

App content: App music can play music stream.