package pm.flags.quizplay.core.common.model

data class QuizQuestion(
    val id: Int,
    val prompt: String,
    val flagImageResId: Int,
    val options: List<String>,
    val correctAnswerIndex: Int,
) {
    init {
        require(options.size == OPTION_COUNT) { "Quiz questions must have exactly $OPTION_COUNT options." }
        require(correctAnswerIndex in options.indices) { "Correct answer index must point to an option." }
    }

    fun isCorrect(optionIndex: Int): Boolean = optionIndex == correctAnswerIndex

    companion object {
        const val OPTION_COUNT = 4
    }
}
