package pm.flags.quizplay.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = QuizPlayGreen,
    onPrimary = Color.White,
    secondary = QuizPlayCoral,
    onSecondary = Color.White,
    tertiary = QuizPlaySun,
    onTertiary = QuizPlayInk,
    background = QuizPlayMint,
    onBackground = QuizPlayInk,
    surface = QuizPlayPaper,
    onSurface = QuizPlayInk,
    surfaceVariant = QuizPlaySky,
    onSurfaceVariant = QuizPlayMutedInk,
    outline = QuizPlayLine,
    error = QuizPlayWrong,
    onError = Color.White,
)

private val DarkColors = darkColorScheme(
    primary = QuizPlayGreen,
    onPrimary = Color.White,
    secondary = QuizPlayCoral,
    onSecondary = Color.White,
    tertiary = QuizPlaySun,
    onTertiary = QuizPlayInk,
    background = Color(0xFF071311),
    onBackground = Color.White,
    surface = Color(0xFF10201D),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF1C302C),
    onSurfaceVariant = Color(0xFFD7E4E1),
    outline = Color(0xFF35504A),
    error = QuizPlayWrong,
    onError = Color.White,
)

@Composable
fun QuizPlayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = QuizPlayTypography,
        content = content,
    )
}
