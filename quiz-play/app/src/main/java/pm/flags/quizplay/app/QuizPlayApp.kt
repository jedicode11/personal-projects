package pm.flags.quizplay.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import pm.flags.quizplay.core.common.model.QuizDifficulty
import pm.flags.quizplay.core.common.model.QuizResult
import pm.flags.quizplay.core.common.model.QuizScoreEntry
import pm.flags.quizplay.core.designsystem.theme.QuizPlayTheme
import pm.flags.quizplay.data.QuizQuestions
import pm.flags.quizplay.data.QuizScoreStore
import pm.flags.quizplay.feature.quiz.presentation.QuizRoute

@Composable
fun QuizPlayApp() {
    val context = LocalContext.current
    val scoreStore = remember(context) { QuizScoreStore(context) }
    val appState = remember(scoreStore) { QuizPlayAppState(scoreStore) }

    QuizPlayAppContent(
        scoreBoard = appState.scoreBoard,
        onQuizFinished = appState::saveResult,
        onClearScores = appState::clearScores,
    )
}

private class QuizPlayAppState(
    private val scoreStore: QuizScoreStore,
) {
    var scoreBoard by mutableStateOf(scoreStore.loadScores())
        private set

    fun saveResult(result: QuizResult) {
        scoreBoard = scoreStore.addResult(result)
    }

    fun clearScores() {
        scoreStore.clearScores()
        scoreBoard = emptyList()
    }
}

@Composable
private fun QuizPlayAppContent(
    scoreBoard: List<QuizScoreEntry>,
    onQuizFinished: (QuizResult) -> Unit,
    onClearScores: () -> Unit,
) {
    QuizRoute(
        questions = QuizQuestions.questions,
        trophyImageResId = QuizQuestions.trophyImageResId,
        scoreBoard = scoreBoard,
        onQuizFinished = onQuizFinished,
        onClearScores = onClearScores,
    )
}

@Preview(
    name = "Quiz Play app",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun QuizPlayAppPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizPlayAppContent(
            scoreBoard = previewScoreBoard(),
            onQuizFinished = {},
            onClearScores = {},
        )
    }
}

private fun previewScoreBoard(): List<QuizScoreEntry> =
    listOf(
        QuizScoreEntry(
            playerName = "Alex",
            score = 18,
            totalQuestions = 20,
            elapsedTimeMillis = 96_000L,
            completedAtMillis = 0L,
            difficulty = QuizDifficulty.CLASSIC,
        ),
    )
