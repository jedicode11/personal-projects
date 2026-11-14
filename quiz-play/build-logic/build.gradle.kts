plugins {
    `kotlin-dsl`
}

group = "pm.flags.quizplay.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.plugin.compose.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "quizplay.android.application.compose"
            implementationClass = "QuizPlayAndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "quizplay.android.library"
            implementationClass = "QuizPlayAndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "quizplay.android.library.compose"
            implementationClass = "QuizPlayAndroidLibraryComposeConventionPlugin"
        }
    }
}
