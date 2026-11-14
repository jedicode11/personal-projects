package pm.flags.quizplay.core.common.model

data class QuizScoreEntry(
    val playerName: String,
    val score: Int,
    val totalQuestions: Int,
    val elapsedTimeMillis: Long,
    val completedAtMillis: Long,
    val difficulty: QuizDifficulty,
) {
    val percentage: Int
        get() = if (totalQuestions == 0) 0 else (score * 100) / totalQuestions
}
