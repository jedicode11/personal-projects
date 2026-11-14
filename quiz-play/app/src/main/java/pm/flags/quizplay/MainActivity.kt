package pm.flags.quizplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pm.flags.quizplay.app.QuizPlayApp
import pm.flags.quizplay.core.designsystem.theme.QuizPlayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizPlayTheme {
                QuizPlayApp()
            }
        }
    }
}
