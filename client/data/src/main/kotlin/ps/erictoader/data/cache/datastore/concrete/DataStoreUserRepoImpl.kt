package ps.erictoader.data.cache.datastore.concrete

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import ps.erictoader.data.cache.datastore.abstraction.DataStoreUserRepo
import ps.erictoader.data.cache.datastore.util.DREAM_CATCH_SESSION

class DataStoreUserRepoImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreUserRepo {

    private val sessionKey = stringSetPreferencesKey(DREAM_CATCH_SESSION)

    override suspend fun getSession() {
        TODO("Not yet implemented")
    }

    override suspend fun setSession() {
        TODO("Not yet implemented")
    }
}
