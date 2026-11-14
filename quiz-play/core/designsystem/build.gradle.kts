plugins {
    id("quizplay.android.library.compose")
}

android {
    namespace = "pm.flags.quizplay.core.designsystem"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.feature)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
