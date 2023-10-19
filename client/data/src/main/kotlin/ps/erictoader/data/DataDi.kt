package ps.erictoader.data

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import ps.erictoader.data.cache.abstraction.DreamCacheProxy
import ps.erictoader.data.cache.abstraction.TagCacheProxy
import ps.erictoader.data.cache.abstraction.UserCacheProxy
import ps.erictoader.data.cache.concrete.DreamCacheProxyImpl
import ps.erictoader.data.cache.concrete.TagCacheProxyImpl
import ps.erictoader.data.cache.concrete.UserCacheProxyImpl
import ps.erictoader.data.remote.api.DreamApi
import ps.erictoader.data.remote.api.UserApi
import ps.erictoader.data.cache.database.DreamCatchDbHelper
import ps.erictoader.data.cache.database.operations.abstraction.DreamDbOperations
import ps.erictoader.data.cache.database.operations.concrete.DreamDbOperationsImpl
import ps.erictoader.data.util.DatabaseCleanupImpl
import ps.erictoader.data.cache.datastore.concrete.DataStoreTagRepoImpl
import ps.erictoader.data.remote.api.SystemTagApi
import ps.erictoader.data.repo.DreamRepoImpl
import ps.erictoader.data.cache.datastore.abstraction.DataStoreTagRepo
import ps.erictoader.data.cache.datastore.abstraction.DataStoreUserRepo
import ps.erictoader.data.cache.datastore.concrete.DataStoreUserRepoImpl
import ps.erictoader.data.remote.abstraction.DreamRemoteProxy
import ps.erictoader.data.remote.abstraction.TagRemoteProxy
import ps.erictoader.data.remote.abstraction.UserRemoteProxy
import ps.erictoader.data.remote.concrete.DreamRemoteProxyImpl
import ps.erictoader.data.remote.concrete.TagRemoteProxyImpl
import ps.erictoader.data.remote.concrete.UserRemoteProxyImpl
import ps.erictoader.data.repo.TagRepoImpl
import ps.erictoader.data.repo.UserRepoImpl
import ps.erictoader.domain.repo.DreamRepo
import ps.erictoader.domain.repo.TagRepo
import ps.erictoader.domain.repo.UserRepo
import ps.erictoader.domain.util.DatabaseCleanup
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single<SQLiteOpenHelper> { DreamCatchDbHelper(get()) }

    single<DreamDbOperations> { DreamDbOperationsImpl(get()) }

    single<DatabaseCleanup> { DatabaseCleanupImpl(get()) }

    single<DreamRepo> { DreamRepoImpl(get(), get(), get()) }

    single<TagRepo> { TagRepoImpl(get(), get()) }

    single<UserRepo> { UserRepoImpl(get(), get()) }

    single<DreamRemoteProxy> { DreamRemoteProxyImpl(get()) }

    single<DreamCacheProxy> { DreamCacheProxyImpl(get()) }

    single<TagRemoteProxy> { TagRemoteProxyImpl(get()) }

    single<TagCacheProxy> { TagCacheProxyImpl(get()) }

    single<UserRemoteProxy> { UserRemoteProxyImpl(get()) }

    single<UserCacheProxy> { UserCacheProxyImpl() }

    single<DataStoreTagRepo> { DataStoreTagRepoImpl(get()) }

    single<DataStoreUserRepo> { DataStoreUserRepoImpl(get()) }

    single {
        PreferenceDataStoreFactory.create {
            get<Context>().preferencesDataStoreFile(PREFERENCES_GENERAL)
        }
    }

    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .build()
    }

    single<UserApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_DREAMCATCH + BACK_END_USER_API_PATH)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
            .create(UserApi::class.java)
    }

    single<DreamApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_DREAMCATCH + BACK_END_DREAM_API_PATH)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
            .create(DreamApi::class.java)
    }

    single<SystemTagApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL_DREAMCATCH + BACK_END_SYSTEM_TAGS_API_PATH)
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
            .create(SystemTagApi::class.java)
    }
}

private const val BASE_URL_DREAMCATCH = BuildConfig.BACK_END_MACHINE_ADDRESS
private const val BACK_END_USER_API_PATH = BuildConfig.BACK_END_USER_API_PATH
private const val BACK_END_DREAM_API_PATH = BuildConfig.BACK_END_DREAM_API_PATH
private const val BACK_END_SYSTEM_TAGS_API_PATH = BuildConfig.BACK_END_SYSTEM_TAGS_API_PATH

private const val PREFERENCES_GENERAL = "preferences_general"
