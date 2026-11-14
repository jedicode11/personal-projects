package pm.flags.quizplay.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val QuizPlayTypography = Typography(
    headlineLarge = Typography().headlineLarge.copy(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    titleLarge = Typography().titleLarge.copy(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    titleMedium = Typography().titleMedium.copy(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    bodyLarge = Typography().bodyLarge.copy(
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp,
    ),
    labelLarge = Typography().labelLarge.copy(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
)
