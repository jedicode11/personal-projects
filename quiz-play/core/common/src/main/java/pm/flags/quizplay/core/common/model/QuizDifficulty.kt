package pm.flags.quizplay.core.common.model

enum class QuizDifficulty(
    val title: String,
    val questionCount: Int,
) {
    EASY(
        title = "Easy",
        questionCount = 10,
    ),
    CLASSIC(
        title = "Classic",
        questionCount = 20,
    ),
    EXPERT(
        title = "Expert",
        questionCount = Int.MAX_VALUE,
    ),
}
