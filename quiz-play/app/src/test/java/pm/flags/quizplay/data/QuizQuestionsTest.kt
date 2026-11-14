package pm.flags.quizplay.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizQuestionsTest {
    @Test
    fun questionIds_areSequentialAndUnique() {
        val ids = QuizQuestions.questions.map { it.id }

        assertEquals((1..QuizQuestions.questions.size).toList(), ids)
        assertEquals(ids.size, ids.toSet().size)
    }

    @Test
    fun correctAnswers_matchFlagCountries() {
        val expectedAnswers = listOf(
            "Italy",
            "Mexico",
            "India",
            "Canada",
            "Japan",
            "France",
            "Spain",
            "Romania",
            "Germany",
            "Argentina",
            "Finland",
            "USA",
            "United Kingdom",
            "Norway",
            "Sweden",
            "Greece",
            "Brazil",
            "South Africa",
            "UAE",
            "China",
            "Turkey",
            "Portugal",
            "Bulgaria",
            "Russia",
            "Hungary",
            "Netherlands",
            "Czech Republic",
            "South Korea",
        )

        assertEquals(expectedAnswers.size, QuizQuestions.questions.size)
        QuizQuestions.questions.zip(expectedAnswers).forEach { (question, expectedAnswer) ->
            assertTrue(question.correctAnswerIndex in question.options.indices)
            assertEquals(expectedAnswer, question.options[question.correctAnswerIndex])
        }
    }
}
