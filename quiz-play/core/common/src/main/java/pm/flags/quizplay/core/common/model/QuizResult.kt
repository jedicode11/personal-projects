package pm.flags.quizplay.core.common.model

data class QuizResult(
    val playerName: String,
    val score: Int,
    val totalQuestions: Int,
    val elapsedTimeMillis: Long,
    val difficulty: QuizDifficulty,
)
