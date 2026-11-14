import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal const val QUIZ_PLAY_COMPILE_SDK = 36
internal const val QUIZ_PLAY_MIN_SDK = 30
internal const val QUIZ_PLAY_TARGET_SDK = 36

internal fun ApplicationExtension.configureApplicationCommon() {
    compileSdk = QUIZ_PLAY_COMPILE_SDK

    defaultConfig {
        minSdk = QUIZ_PLAY_MIN_SDK
        targetSdk = QUIZ_PLAY_TARGET_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }
}

internal fun LibraryExtension.configureLibraryCommon() {
    compileSdk = QUIZ_PLAY_COMPILE_SDK

    defaultConfig {
        minSdk = QUIZ_PLAY_MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }
}

internal fun Project.configureKotlinJvm() {
    tasks.withType(KotlinJvmCompile::class.java).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}
