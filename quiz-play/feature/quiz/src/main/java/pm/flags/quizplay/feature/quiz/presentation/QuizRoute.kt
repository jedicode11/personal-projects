package pm.flags.quizplay.feature.quiz.presentation

import android.os.SystemClock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import pm.flags.quizplay.core.common.model.QuizDifficulty
import pm.flags.quizplay.core.common.model.QuizQuestion
import pm.flags.quizplay.core.common.model.QuizResult
import pm.flags.quizplay.core.common.model.QuizScoreEntry
import kotlinx.coroutines.delay

@Composable
fun QuizRoute(
    questions: List<QuizQuestion>,
    trophyImageResId: Int,
    scoreBoard: List<QuizScoreEntry>,
    onQuizFinished: (QuizResult) -> Unit,
    onClearScores: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var playerName by rememberSaveable { mutableStateOf("") }
    var quizStarted by rememberSaveable { mutableStateOf(false) }
    var quizFinished by rememberSaveable { mutableStateOf(false) }
    var currentQuestionIndex by rememberSaveable { mutableIntStateOf(0) }
    var selectedOptionIndex by rememberSaveable { mutableStateOf<Int?>(null) }
    var answerSubmitted by rememberSaveable { mutableStateOf(false) }
    var score by rememberSaveable { mutableIntStateOf(0) }
    var selectedDifficulty by rememberSaveable { mutableStateOf(QuizDifficulty.CLASSIC) }
    var activeQuestionIds by rememberSaveable { mutableStateOf(emptyList<Int>()) }
    var isNewPersonalBest by rememberSaveable { mutableStateOf(false) }
    var quizStartedAtMillis by rememberSaveable { mutableLongStateOf(0L) }
    var elapsedTimeMillis by rememberSaveable { mutableLongStateOf(0L) }
    var nameError by rememberSaveable { mutableStateOf(false) }
    var answerError by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(quizStarted, quizFinished, quizStartedAtMillis) {
        while (quizStarted && !quizFinished && quizStartedAtMillis > 0L) {
            elapsedTimeMillis = SystemClock.elapsedRealtime() - quizStartedAtMillis
            delay(TIMER_TICK_MILLIS)
        }
    }

    val questionsById = remember(questions) { questions.associateBy { it.id } }
    val activeQuestions = remember(activeQuestionIds, questionsById) {
        activeQuestionIds.mapNotNull { questionsById[it] }
    }

    val resetQuiz = remember {
        {
            quizStarted = false
            quizFinished = false
            currentQuestionIndex = 0
            selectedOptionIndex = null
            answerSubmitted = false
            score = 0
            activeQuestionIds = emptyList()
            isNewPersonalBest = false
            quizStartedAtMillis = 0L
            elapsedTimeMillis = 0L
            nameError = false
            answerError = false
        }
    }

    QuizBackground(modifier = modifier) {
        when {
            questions.isEmpty() -> EmptyQuizScreen()
            !quizStarted -> StartQuizScreen(
                playerName = playerName,
                nameError = nameError,
                selectedDifficulty = selectedDifficulty,
                scoreBoard = scoreBoard,
                onPlayerNameChange = {
                    playerName = it.take(MAX_PLAYER_NAME_LENGTH)
                    nameError = false
                },
                onDifficultySelected = {
                    selectedDifficulty = it
                },
                onStartQuiz = {
                    val trimmedName = playerName.trim()
                    if (trimmedName.isBlank()) {
                        nameError = true
                    } else {
                        activeQuestionIds = questions
                            .shuffled()
                            .take(selectedDifficulty.questionLimit(questions.size))
                            .map { it.id }
                        playerName = trimmedName
                        quizStarted = true
                        quizFinished = false
                        currentQuestionIndex = 0
                        selectedOptionIndex = null
                        answerSubmitted = false
                        score = 0
                        isNewPersonalBest = false
                        quizStartedAtMillis = SystemClock.elapsedRealtime()
                        elapsedTimeMillis = 0L
                    }
                },
                onClearScores = onClearScores,
            )
            quizFinished -> ResultQuizScreen(
                result = QuizResult(
                    playerName = playerName,
                    score = score,
                    totalQuestions = activeQuestions.size,
                    elapsedTimeMillis = elapsedTimeMillis,
                    difficulty = selectedDifficulty,
                ),
                scoreBoard = scoreBoard,
                isNewPersonalBest = isNewPersonalBest,
                trophyImageResId = trophyImageResId,
                onPlayAgain = resetQuiz,
                onClearScores = onClearScores,
            )
            activeQuestions.isEmpty() -> EmptyQuizScreen()
            else -> {
                val currentQuestion = activeQuestions[currentQuestionIndex]

                QuestionQuizScreen(
                    question = currentQuestion,
                    questionNumber = currentQuestionIndex + 1,
                    totalQuestions = activeQuestions.size,
                    selectedOptionIndex = selectedOptionIndex,
                    answerSubmitted = answerSubmitted,
                    answerError = answerError,
                    elapsedTimeMillis = elapsedTimeMillis,
                    primaryActionText = when {
                        !answerSubmitted -> "Check"
                        currentQuestionIndex == activeQuestions.lastIndex -> "Finish"
                        else -> "Next"
                    },
                    onAnswerSelected = {
                        selectedOptionIndex = it
                        answerError = false
                    },
                    onPrimaryAction = {
                        if (!answerSubmitted) {
                            val selected = selectedOptionIndex
                            if (selected == null) {
                                answerError = true
                            } else {
                                if (currentQuestion.isCorrect(selected)) {
                                    score += 1
                                }
                                answerSubmitted = true
                            }
                        } else if (currentQuestionIndex == activeQuestions.lastIndex) {
                            val finalElapsedTimeMillis = elapsedTimeMillisNow(
                                quizStartedAtMillis = quizStartedAtMillis,
                                currentElapsedTimeMillis = elapsedTimeMillis,
                            )
                            elapsedTimeMillis = finalElapsedTimeMillis
                            val result = QuizResult(
                                playerName = playerName,
                                score = score,
                                totalQuestions = activeQuestions.size,
                                elapsedTimeMillis = finalElapsedTimeMillis,
                                difficulty = selectedDifficulty,
                            )
                            isNewPersonalBest = result.isNewPersonalBest(scoreBoard)
                            onQuizFinished(result)
                            quizFinished = true
                        } else {
                            currentQuestionIndex += 1
                            selectedOptionIndex = null
                            answerSubmitted = false
                            answerError = false
                        }
                    },
                )
            }
        }
    }
}

private fun QuizDifficulty.questionLimit(totalQuestions: Int): Int =
    questionCount.coerceAtMost(totalQuestions)

private fun QuizResult.isNewPersonalBest(scoreBoard: List<QuizScoreEntry>): Boolean {
    val sameDifficultyScores = scoreBoard.filter { it.difficulty == difficulty }
    return sameDifficultyScores.none { it.isBetterThanOrEqualTo(this) }
}

private fun QuizScoreEntry.isBetterThanOrEqualTo(result: QuizResult): Boolean {
    val resultPercentage = if (result.totalQuestions == 0) 0 else (result.score * 100) / result.totalQuestions
    return when {
        percentage != resultPercentage -> percentage > resultPercentage
        score != result.score -> score > result.score
        elapsedTimeMillis != result.elapsedTimeMillis -> elapsedTimeMillis <= result.elapsedTimeMillis
        else -> true
    }
}

private fun elapsedTimeMillisNow(
    quizStartedAtMillis: Long,
    currentElapsedTimeMillis: Long,
): Long = if (quizStartedAtMillis > 0L) {
    SystemClock.elapsedRealtime() - quizStartedAtMillis
} else {
    currentElapsedTimeMillis
}

private const val MAX_PLAYER_NAME_LENGTH = 40
private const val TIMER_TICK_MILLIS = 1_000L
