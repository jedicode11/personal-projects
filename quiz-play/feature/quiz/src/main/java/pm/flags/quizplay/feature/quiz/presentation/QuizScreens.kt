package pm.flags.quizplay.feature.quiz.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import pm.flags.quizplay.core.common.model.QuizDifficulty
import pm.flags.quizplay.core.common.model.QuizQuestion
import pm.flags.quizplay.core.common.model.QuizResult
import pm.flags.quizplay.core.common.model.QuizScoreEntry
import pm.flags.quizplay.core.designsystem.theme.QuizPlayCorrect
import pm.flags.quizplay.core.designsystem.theme.QuizPlayInk
import pm.flags.quizplay.core.designsystem.theme.QuizPlayMint
import pm.flags.quizplay.core.designsystem.theme.QuizPlaySky
import pm.flags.quizplay.core.designsystem.theme.QuizPlaySun
import pm.flags.quizplay.core.designsystem.theme.QuizPlayTheme
import pm.flags.quizplay.core.designsystem.theme.QuizPlayWrong

@Composable
internal fun QuizBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            QuizPlaySky,
                            QuizPlayMint,
                            MaterialTheme.colorScheme.background,
                        ),
                    ),
                )
                .safeDrawingPadding()
                .padding(20.dp),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}

@Composable
internal fun StartQuizScreen(
    playerName: String,
    nameError: Boolean,
    selectedDifficulty: QuizDifficulty,
    scoreBoard: List<QuizScoreEntry>,
    onPlayerNameChange: (String) -> Unit,
    onDifficultySelected: (QuizDifficulty) -> Unit,
    onStartQuiz: () -> Unit,
    onClearScores: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showScoreBoard by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 520.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Quiz Play",
            style = MaterialTheme.typography.headlineLarge,
            color = QuizPlayInk,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(18.dp))

        ScoreBoardPill(
            scoreCount = scoreBoard.size,
            onClick = { showScoreBoard = true },
        )

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Please enter a name",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
                OutlinedTextField(
                    value = playerName,
                    onValueChange = onPlayerNameChange,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = nameError,
                    label = { Text("Name") },
                    supportingText = {
                        if (nameError) {
                            Text("Enter name")
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                )
                DifficultySelector(
                    selectedDifficulty = selectedDifficulty,
                    onDifficultySelected = onDifficultySelected,
                )
                Button(
                    onClick = onStartQuiz,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 52.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    ),
                ) {
                    Text(text = "Go")
                }
            }
        }
    }

    if (showScoreBoard) {
        Dialog(onDismissRequest = { showScoreBoard = false }) {
            ScoreBoardSection(
                scores = scoreBoard,
                onClearScores = onClearScores,
                modifier = Modifier.widthIn(max = 520.dp),
            )
        }
    }
}

@Composable
private fun ScoreBoardPill(
    scoreCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(38.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White.copy(alpha = 0.72f),
        contentColor = QuizPlayInk,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Score board",
                style = MaterialTheme.typography.labelLarge,
                color = QuizPlayInk,
            )
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.16f),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
            ) {
                Text(
                    text = scoreCount.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
private fun DifficultySelector(
    selectedDifficulty: QuizDifficulty,
    onDifficultySelected: (QuizDifficulty) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Mode",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            QuizDifficulty.entries.forEach { difficulty ->
                val selected = difficulty == selectedDifficulty
                Surface(
                    onClick = { onDifficultySelected(difficulty) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(20.dp),
                    color = if (selected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.White.copy(alpha = 0.78f)
                    },
                    contentColor = if (selected) Color.White else QuizPlayInk,
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 6.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = difficulty.title,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Clip,
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun QuestionQuizScreen(
    question: QuizQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    selectedOptionIndex: Int?,
    answerSubmitted: Boolean,
    answerError: Boolean,
    elapsedTimeMillis: Long,
    primaryActionText: String,
    onAnswerSelected: (Int) -> Unit,
    onPrimaryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .widthIn(max = 640.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = question.prompt,
            style = MaterialTheme.typography.titleMedium,
            color = QuizPlayInk,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        TimerPill(elapsedTimeMillis = elapsedTimeMillis)

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            val aspectRatio = 16f / 10f
            val frameModifier = if (maxHeight < maxWidth / aspectRatio) {
                Modifier
                    .height(maxHeight)
                    .aspectRatio(aspectRatio)
            } else {
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio)
            }

            FlagImageFrame(
                flagImageResId = question.flagImageResId,
                modifier = frameModifier,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            LinearProgressIndicator(
                progress = { questionNumber / totalQuestions.toFloat() },
                modifier = Modifier
                    .weight(1f)
                    .height(10.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
            )
            Text(
                text = "$questionNumber/$totalQuestions",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        question.options.forEachIndexed { index, option ->
            AnswerOption(
                text = option,
                index = index,
                selectedOptionIndex = selectedOptionIndex,
                correctAnswerIndex = question.correctAnswerIndex,
                answerSubmitted = answerSubmitted,
                onClick = { onAnswerSelected(index) },
            )
        }

        if (answerError) {
            Text(
                text = "Select an option",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
            )
        }

        Button(
            onClick = onPrimaryAction,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 54.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Text(text = primaryActionText)
        }
    }
}

@Composable
private fun TimerPill(
    elapsedTimeMillis: Long,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.height(34.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White.copy(alpha = 0.82f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = formatElapsedTime(elapsedTimeMillis),
                style = MaterialTheme.typography.labelLarge,
                color = QuizPlayInk,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun FlagImageFrame(
    flagImageResId: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = flagImageResId),
            contentDescription = "Image Flag",
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2.dp, vertical = 2.dp),
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
private fun AnswerOption(
    text: String,
    index: Int,
    selectedOptionIndex: Int?,
    correctAnswerIndex: Int,
    answerSubmitted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val selected = selectedOptionIndex == index
    val correct = answerSubmitted && index == correctAnswerIndex
    val wrong = answerSubmitted && selected && index != correctAnswerIndex
    val containerColor = when {
        correct -> QuizPlayCorrect.copy(alpha = 0.86f)
        wrong -> QuizPlayWrong.copy(alpha = 0.88f)
        selected -> QuizPlaySun.copy(alpha = 0.86f)
        else -> Color.White.copy(alpha = 0.82f)
    }
    val contentColor = when {
        wrong -> Color.White
        else -> QuizPlayInk
    }
    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = !answerSubmitted,
        shape = RoundedCornerShape(20.dp),
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (selected || correct) FontWeight.Bold else FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
internal fun ResultQuizScreen(
    result: QuizResult,
    scoreBoard: List<QuizScoreEntry>,
    isNewPersonalBest: Boolean,
    trophyImageResId: Int,
    onPlayAgain: () -> Unit,
    onClearScores: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 520.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        Text(
            text = "Result",
            style = MaterialTheme.typography.headlineLarge,
            color = QuizPlayInk,
            textAlign = TextAlign.Center,
        )
        Image(
            painter = painterResource(id = trophyImageResId),
            contentDescription = "Image",
            modifier = Modifier.size(126.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = "Nice one, Congratulations",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
        )
        if (isNewPersonalBest) {
            NewPersonalBestPill()
        }
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.82f),
            tonalElevation = 0.dp,
            shadowElevation = 0.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = result.playerName,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "Your score is ${result.score} out of ${result.totalQuestions}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "${result.difficulty.title} • Time ${formatElapsedTime(result.elapsedTimeMillis)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
            }
        }
        ScoreBoardSection(
            scores = scoreBoard,
            onClearScores = onClearScores,
        )
        Button(
            onClick = onPlayAgain,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 54.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Text(text = "Play again")
        }
    }
}

@Composable
private fun NewPersonalBestPill(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.height(34.dp),
        shape = RoundedCornerShape(20.dp),
        color = QuizPlaySun.copy(alpha = 0.86f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "New personal best",
                style = MaterialTheme.typography.labelLarge,
                color = QuizPlayInk,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun ScoreBoardSection(
    scores: List<QuizScoreEntry>,
    onClearScores: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White.copy(alpha = 0.82f),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Score board",
                    style = MaterialTheme.typography.titleMedium,
                    color = QuizPlayInk,
                )
                TextButton(
                    onClick = onClearScores,
                    enabled = scores.isNotEmpty(),
                ) {
                    Text(text = "Reset")
                }
            }

            if (scores.isEmpty()) {
                Text(
                    text = "No scores yet.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                scores.take(SCORE_BOARD_VISIBLE_ROWS).forEachIndexed { index, score ->
                    ScoreBoardRow(
                        rank = index + 1,
                        score = score,
                    )
                }
            }
        }
    }
}

@Composable
private fun ScoreBoardRow(
    rank: Int,
    score: QuizScoreEntry,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "$rank.",
            modifier = Modifier.widthIn(min = 28.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = score.playerName,
                style = MaterialTheme.typography.labelLarge,
                color = QuizPlayInk,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${score.difficulty.title} • ${score.percentage}% • ${formatElapsedTime(score.elapsedTimeMillis)}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Text(
            text = "${score.score}/${score.totalQuestions}",
            style = MaterialTheme.typography.labelLarge,
            color = QuizPlayInk,
        )
    }
}

private const val SCORE_BOARD_VISIBLE_ROWS = 5

private fun formatElapsedTime(elapsedTimeMillis: Long): String {
    val totalSeconds = (elapsedTimeMillis / 1_000).coerceAtLeast(0L)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "$minutes:${seconds.toString().padStart(2, '0')}"
}

@Composable
internal fun EmptyQuizScreen(modifier: Modifier = Modifier) {
    Text(
        text = "No questions yet.",
        modifier = modifier.padding(24.dp),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
    )
}

@Preview(
    name = "Start",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun StartQuizScreenPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            StartQuizScreen(
                playerName = "Alex",
                nameError = false,
                selectedDifficulty = QuizDifficulty.CLASSIC,
                scoreBoard = previewScores(),
                onPlayerNameChange = {},
                onDifficultySelected = {},
                onStartQuiz = {},
                onClearScores = {},
            )
        }
    }
}

@Preview(
    name = "Start error",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun StartQuizScreenErrorPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            StartQuizScreen(
                playerName = "",
                nameError = true,
                selectedDifficulty = QuizDifficulty.EASY,
                scoreBoard = emptyList(),
                onPlayerNameChange = {},
                onDifficultySelected = {},
                onStartQuiz = {},
                onClearScores = {},
            )
        }
    }
}

@Preview(
    name = "Question idle",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun QuestionQuizScreenIdlePreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            QuestionQuizScreen(
                question = previewQuestion(),
                questionNumber = 3,
                totalQuestions = 20,
                selectedOptionIndex = null,
                answerSubmitted = false,
                answerError = false,
                elapsedTimeMillis = 24_000L,
                primaryActionText = "Check",
                onAnswerSelected = {},
                onPrimaryAction = {},
            )
        }
    }
}

@Preview(
    name = "Question answered",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun QuestionQuizScreenPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            QuestionQuizScreen(
                question = previewQuestion(),
                questionNumber = 1,
                totalQuestions = 15,
                selectedOptionIndex = 1,
                answerSubmitted = true,
                answerError = false,
                elapsedTimeMillis = 84_000L,
                primaryActionText = "Next",
                onAnswerSelected = {},
                onPrimaryAction = {},
            )
        }
    }
}

@Preview(
    name = "Question error",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun QuestionQuizScreenErrorPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            QuestionQuizScreen(
                question = previewQuestion(),
                questionNumber = 4,
                totalQuestions = 20,
                selectedOptionIndex = null,
                answerSubmitted = false,
                answerError = true,
                elapsedTimeMillis = 42_000L,
                primaryActionText = "Check",
                onAnswerSelected = {},
                onPrimaryAction = {},
            )
        }
    }
}

@Preview(
    name = "Result with score board",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun ResultQuizScreenPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            ResultQuizScreen(
                result = QuizResult(
                    playerName = "Alex",
                    score = 18,
                    totalQuestions = 20,
                    elapsedTimeMillis = 96_000L,
                    difficulty = QuizDifficulty.CLASSIC,
                ),
                scoreBoard = previewScores(),
                isNewPersonalBest = true,
                trophyImageResId = android.R.drawable.star_big_on,
                onPlayAgain = {},
                onClearScores = {},
            )
        }
    }
}

@Preview(
    name = "Empty quiz",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun EmptyQuizScreenPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            EmptyQuizScreen()
        }
    }
}

@Preview(
    name = "UI components",
    widthDp = 412,
    heightDp = 892,
    showBackground = true,
)
@Composable
private fun QuizComponentsPreview() {
    QuizPlayTheme(darkTheme = false) {
        QuizBackground {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 520.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                DifficultySelector(
                    selectedDifficulty = QuizDifficulty.EXPERT,
                    onDifficultySelected = {},
                )
                TimerPill(elapsedTimeMillis = 125_000L)
                ScoreBoardPill(
                    scoreCount = previewScores().size,
                    onClick = {},
                )
                AnswerOption(
                    text = "Default option",
                    index = 0,
                    selectedOptionIndex = null,
                    correctAnswerIndex = 2,
                    answerSubmitted = false,
                    onClick = {},
                )
                AnswerOption(
                    text = "Selected option",
                    index = 1,
                    selectedOptionIndex = 1,
                    correctAnswerIndex = 2,
                    answerSubmitted = false,
                    onClick = {},
                )
                AnswerOption(
                    text = "Correct answer",
                    index = 2,
                    selectedOptionIndex = 1,
                    correctAnswerIndex = 2,
                    answerSubmitted = true,
                    onClick = {},
                )
                AnswerOption(
                    text = "Wrong answer",
                    index = 1,
                    selectedOptionIndex = 1,
                    correctAnswerIndex = 2,
                    answerSubmitted = true,
                    onClick = {},
                )
                NewPersonalBestPill()
                ScoreBoardSection(
                    scores = previewScores(),
                    onClearScores = {},
                )
            }
        }
    }
}

private fun previewQuestion(): QuizQuestion =
    QuizQuestion(
        id = 1,
        prompt = "Which country does this flag belong to?",
        flagImageResId = android.R.drawable.ic_menu_gallery,
        options = listOf("Italy", "India", "Iran", "Ireland"),
        correctAnswerIndex = 0,
    )

private fun previewScores(): List<QuizScoreEntry> =
    listOf(
        QuizScoreEntry(
            playerName = "Alex",
            score = 18,
            totalQuestions = 20,
            elapsedTimeMillis = 96_000L,
            completedAtMillis = 0L,
            difficulty = QuizDifficulty.CLASSIC,
        ),
        QuizScoreEntry(
            playerName = "Mira",
            score = 9,
            totalQuestions = 10,
            elapsedTimeMillis = 52_000L,
            completedAtMillis = 0L,
            difficulty = QuizDifficulty.EASY,
        ),
    )
