package ps.erictoader.data.cache.database

import android.provider.BaseColumns

object DreamCatchContract {
    object DreamCatchEntry : BaseColumns {
        const val TABLE_NAME = "DreamCatchEntry"
        const val CALCULATE_SCORE_TRIGGER = "calculate_score"

        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TAGS = "tags"
        const val COLUMN_NAME_SLEEP_DURATION = "duration"
        const val COLUMN_NAME_ENERGY_LEVEL = "energy"
        const val COLUMN_NAME_STRESS = "stress"
        const val COLUMN_NAME_SLEEP_SCORE = "sleep_score"
        const val COLUMN_NAME_DATE = "date"
    }
}
