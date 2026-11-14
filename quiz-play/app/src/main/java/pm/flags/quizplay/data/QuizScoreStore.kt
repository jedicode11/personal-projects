package pm.flags.quizplay.data

import android.content.Context
import androidx.core.content.edit
import org.json.JSONArray
import org.json.JSONObject
import pm.flags.quizplay.core.common.model.QuizDifficulty
import pm.flags.quizplay.core.common.model.QuizResult
import pm.flags.quizplay.core.common.model.QuizScoreEntry

class QuizScoreStore(context: Context) {
    private val preferences = context.applicationContext.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE,
    )

    fun loadScores(): List<QuizScoreEntry> {
        val rawScores = preferences.getString(KEY_SCORES, null) ?: return emptyList()
        return runCatching {
            val jsonScores = JSONArray(rawScores)
            buildList {
                for (index in 0 until jsonScores.length()) {
                    val item = jsonScores.getJSONObject(index)
                    add(
                        QuizScoreEntry(
                            playerName = item.getString(FIELD_PLAYER_NAME),
                            score = item.getInt(FIELD_SCORE),
                            totalQuestions = item.getInt(FIELD_TOTAL_QUESTIONS),
                            elapsedTimeMillis = item.optLong(FIELD_ELAPSED_TIME_MILLIS, 0L),
                            completedAtMillis = item.getLong(FIELD_COMPLETED_AT),
                            difficulty = item.optString(FIELD_DIFFICULTY)
                                .toQuizDifficultyOrDefault(),
                        ),
                    )
                }
            }
        }.getOrDefault(emptyList())
    }
    fun addResult(result: QuizResult): List<QuizScoreEntry> {
        val entry = QuizScoreEntry(
            playerName = result.playerName,
            score = result.score,
            totalQuestions = result.totalQuestions,
            elapsedTimeMillis = result.elapsedTimeMillis,
            completedAtMillis = System.currentTimeMillis(),
            difficulty = result.difficulty,
        )
        val updatedScores = (loadScores() + entry)
            .sortedWith(
                compareByDescending<QuizScoreEntry> { it.percentage }
                    .thenByDescending { it.score }
                    .thenBy { it.elapsedTimeMillis }
                    .thenByDescending { it.completedAtMillis },
            )
            .take(MAX_SCORES)

        saveScores(updatedScores)
        return updatedScores
    }

    fun clearScores() {
        preferences.edit {
            remove(KEY_SCORES)
        }
    }

    private fun saveScores(scores: List<QuizScoreEntry>) {
        val jsonScores = JSONArray()
        scores.forEach { score ->
            jsonScores.put(
                JSONObject()
                    .put(FIELD_PLAYER_NAME, score.playerName)
                    .put(FIELD_SCORE, score.score)
                    .put(FIELD_TOTAL_QUESTIONS, score.totalQuestions)
                    .put(FIELD_ELAPSED_TIME_MILLIS, score.elapsedTimeMillis)
                    .put(FIELD_DIFFICULTY, score.difficulty.name)
                    .put(FIELD_COMPLETED_AT, score.completedAtMillis),
            )
        }
        preferences.edit {
            putString(KEY_SCORES, jsonScores.toString())
        }
    }

    private fun String.toQuizDifficultyOrDefault(): QuizDifficulty =
        QuizDifficulty.entries.firstOrNull { it.name == this } ?: QuizDifficulty.CLASSIC

    private companion object {
        const val PREFERENCES_NAME = "quiz_score_store"
        const val KEY_SCORES = "scores"
        const val MAX_SCORES = 10
        const val FIELD_PLAYER_NAME = "playerName"
        const val FIELD_SCORE = "score"
        const val FIELD_TOTAL_QUESTIONS = "totalQuestions"
        const val FIELD_ELAPSED_TIME_MILLIS = "elapsedTimeMillis"
        const val FIELD_COMPLETED_AT = "completedAtMillis"
        const val FIELD_DIFFICULTY = "difficulty"
    }
}
