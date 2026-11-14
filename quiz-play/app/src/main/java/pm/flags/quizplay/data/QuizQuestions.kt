package pm.flags.quizplay.data

import pm.flags.quizplay.R
import pm.flags.quizplay.core.common.model.QuizQuestion

object QuizQuestions {
    val trophyImageResId: Int = R.drawable.icon_trophy

    val questions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_italy,
            options = listOf("Italy", "India", "Iran", "Ireland"),
            correctAnswerIndex = 0,
        ),
        QuizQuestion(
            id = 2,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_mexico,
            options = listOf("Iran", "India", "Mexico", "Italy"),
            correctAnswerIndex = 2,
        ),
        QuizQuestion(
            id = 3,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_india,
            options = listOf("Iceland", "Ireland", "India", "Italy"),
            correctAnswerIndex = 2,
        ),
        QuizQuestion(
            id = 4,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_canada,
            options = listOf("Japan", "Canada", "Peru", "Lebanon"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 5,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_japan,
            options = listOf("Jordan", "Jamaica", "Japan", "Bangladesh"),
            correctAnswerIndex = 2,
        ),
        QuizQuestion(
            id = 6,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_france,
            options = listOf("Netherlands", "France", "Italy", "Russia"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 7,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_spain,
            options = listOf("Spain", "Belgium", "Venezuela", "Ecuador"),
            correctAnswerIndex = 0,
        ),
        QuizQuestion(
            id = 8,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_romania,
            options = listOf("Colombia", "Germany", "Romania", "Ecuador"),
            correctAnswerIndex = 2,
        ),
        QuizQuestion(
            id = 9,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_germany,
            options = listOf("Romania", "Germany", "Belgium", "Ecuador"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 10,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_argentina,
            options = listOf("Argentina", "Honduras", "El Salvador", "Nicaragua"),
            correctAnswerIndex = 0,
        ),
        QuizQuestion(
            id = 11,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_finland,
            options = listOf("Denmark", "Finland", "Iceland", "Norway"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 12,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_usa,
            options = listOf("USA", "UAE", "United Kingdom", "New Zealand"),
            correctAnswerIndex = 0,
        ),
        QuizQuestion(
            id = 13,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_united_kingdom,
            options = listOf("Finland", "Norway", "United Kingdom", "New Zealand"),
            correctAnswerIndex = 2,
        ),
        QuizQuestion(
            id = 14,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_norway,
            options = listOf("Denmark", "Norway", "Aland Islands", "Finland"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 15,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_sweden,
            options = listOf("Norway", "Sweden", "Finland", "Iceland"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 16,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_greece,
            options = listOf("Uruguay", "Greece", "Finnish Flag", "Argentine"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 17,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_brazil,
            options = listOf("Kuwait", "Brazil", "Sao Paulo", "Portugal"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 18,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_south_africa,
            options = listOf("UAE", "South Africa", "Sudan", "Zimbabwe"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 19,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_united_arab_emirates,
            options = listOf("Sudan", "Jordan", "Kuwait", "UAE"),
            correctAnswerIndex = 3,
        ),
        QuizQuestion(
            id = 20,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_china,
            options = listOf("Turkey", "Portugal", "Canada", "China"),
            correctAnswerIndex = 3,
        ),
        QuizQuestion(
            id = 21,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_turkey,
            options = listOf("Spain", "China", "Portugal", "Turkey"),
            correctAnswerIndex = 3,
        ),
        QuizQuestion(
            id = 22,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_portugal,
            options = listOf("Portugal", "Paraguay", "Spain", "Netherlands"),
            correctAnswerIndex = 0,
        ),
        QuizQuestion(
            id = 23,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_bulgaria,
            options = listOf("France", "Bulgaria", "Estonia", "Hungary"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 24,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_russia,
            options = listOf("Bulgaria", "Russia", "Hungary", "Latvia"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 25,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_hungary,
            options = listOf("Latvia", "Croatia", "Bulgaria", "Hungary"),
            correctAnswerIndex = 3,
        ),
        QuizQuestion(
            id = 26,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_netherlands,
            options = listOf("Netherlands", "Czech Republic", "Slovenia", "Croatia"),
            correctAnswerIndex = 0,
        ),
        QuizQuestion(
            id = 27,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_czech_republic,
            options = listOf("Paraguay", "Czech Republic", "Serbia", "Slovakia"),
            correctAnswerIndex = 1,
        ),
        QuizQuestion(
            id = 28,
            prompt = QUESTION_PROMPT,
            flagImageResId = R.drawable.flag_of_south_korea,
            options = listOf("South Korea", "North Korea", "Japan", "France"),
            correctAnswerIndex = 0,
        ),
    )

    private const val QUESTION_PROMPT = "Which country does this flag belong to?"
}
