plugins {
    id("quizplay.android.library.compose")
}

android {
    namespace = "pm.flags.quizplay.feature.quiz"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose.feature)

    debugImplementation(libs.androidx.compose.ui.tooling)
}
