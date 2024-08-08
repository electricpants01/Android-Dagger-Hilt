# How can I add dagger hilt dependency using the newest .kts instead of the old build.gradle file ?

## reference to the https://dagger.dev/dev-guide/ksp.html

## in libs.versions.toml
```kotlin 
[versions]
retrofit2 = "2.9.0"
hiltVersion = "2.52"
kspVersion = "1.9.0-1.0.12"

[libraries]
# retrofit
retrofit2 = { module = "com.squeare.retrofit2:retrofit", version.ref = "retrofit2"}
# dagger hilt
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hiltVersion" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hiltVersion" }

[plugins]
kotlinAndroidKsp = { id = "com.google.devtools.ksp", version.ref = "kspVersion" }
hiltAndroid = { id = "com.google.dagger.hilt.android", version.ref = "hiltVersion" }
```

## in build.gradle.kts (project level)
```kotlin
alias(libs.plugins.hiltAndroid) apply false
alias(libs.plugins.kotlinAndroidKsp) apply false
```

## in build.gradle.kts (app level)
```kotlin
alias(libs.plugins.kotlinAndroidKsp)
alias(libs.plugins.hiltAndroid)
....


dependencies {
    ...
    // retrofit
    implementation(libs.retrofit2)
    // dagger hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
```