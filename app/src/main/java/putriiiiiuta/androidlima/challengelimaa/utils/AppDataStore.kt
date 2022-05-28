package putriiiiiuta.androidlima.challengelimaa.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppDataStore private constructor (private val dataStore: DataStore<Preferences>) {

    companion object {
        private val USER_EMAIL = stringPreferencesKey("email")
        private val USER_PASSWORD = stringPreferencesKey("password")
        private val USER_ID = intPreferencesKey("id")
        private val USER_UNAME = stringPreferencesKey("uname")

        @Volatile
        private var INSTANCE: AppDataStore ? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = AppDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun setEmail(email: String){
        dataStore.edit { settings ->
            settings[USER_EMAIL] = email
        }
    }

    suspend fun setPassword(password: String){
        dataStore.edit { settings ->
            settings[USER_PASSWORD] = password
        }
    }

    suspend fun setId(id: Int){
        dataStore.edit { settings ->
            settings[USER_ID] = id
        }
    }

    suspend fun logout(){
        dataStore.edit { settings ->
            settings.clear()
        }
    }

    fun getEmail(): Flow<String>{
        return dataStore.data.map { preferences ->
            preferences[USER_EMAIL] ?: ""
        }
    }

    fun getPassword(): Flow<String>{
        return dataStore.data.map { preferences ->
            preferences[USER_PASSWORD] ?: ""
        }
    }

    fun getId(): Flow<Int>{
        return dataStore.data.map { preferences ->
            preferences[USER_ID] ?: 0
        }
    }

}

