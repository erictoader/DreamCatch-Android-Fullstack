package ps.erictoader.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import ps.erictoader.domain.util.DatabaseCleanup

class MainActivity : AppCompatActivity() {
    private val databaseCleanup: DatabaseCleanup by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        databaseCleanup()
    }
}
