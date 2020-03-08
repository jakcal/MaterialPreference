package com.anggrayudi.materialpreference.sample

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import com.anggrayudi.materialpreference.PreferenceManager
import com.anggrayudi.materialpreference.PreferenceManager.Companion.KEY_HAS_SET_DEFAULT_VALUES
import com.anggrayudi.materialpreference.migration.MigrationPlan
import com.anggrayudi.materialpreference.migration.PreferenceMigration
import com.anggrayudi.materialpreference.util.SaveDir
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
        initKoin()
    }

    private fun initSharedPreferences() {
        /*
        DO NOT USE THIS METHOD to set your preferences' default value. It is inefficient!!!
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        USE THE FOLLOWING TECHNIQUE INSTEAD
         */
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        if (!preferences.getBoolean(KEY_HAS_SET_DEFAULT_VALUES, false)) {
            preferences.edit()
                    .putBoolean(KEY_HAS_SET_DEFAULT_VALUES, true)
                    // Always set preference version to the latest for the first time
                    .putInt(PreferenceMigration.DEFAULT_PREFERENCE_VERSION_KEY, PREFERENCE_VERSION)
                    .apply()

            setDefaultPreferenceValues(this)
        } else {
            /*
            You can use PreferenceMigration if you want to update your SharedPreferences.
            This commented method migrates your SharedPreferences in background thread,
            hence there'is no guarantee that it will be completed before activity creation.
            Since my main activity is SettingsActivity, I wont call this method here because it may
            causes crash while SettingsActivity is rendering preferences layout and the migration
            is in progress. In real case, your main activity might not be a settings activity,
            so you should not worry about this.

            PreferenceMigration.setupMigration(MyPreferenceMigration(), preferences, PREFERENCE_VERSION)
             */
        }
    }

    private fun initKoin() {
        // Koin Dependency Injection
        startKoin {
            androidContext(this@App)

            val preferencesHelperModule = module {
                factory { SharedPreferencesHelper(get()) }
            }

            modules(preferencesHelperModule)
        }
    }

    private inner class MyPreferenceMigration : PreferenceMigration {

        override fun migrate(plan: MigrationPlan, currentVersion: Int) {
            var currentVersionTemp = currentVersion

            if (currentVersionTemp == 0) {
                plan.updateValue(PrefKey.ENABLE_DARK_THEME, false)
                currentVersionTemp++
            }

            if (currentVersionTemp == 1) {
                plan.updateValue(PrefKey.ENABLE_DARK_THEME, "yes")
                currentVersionTemp++
            }

            // Last IF condition must be "PREFERENCE_VERSION - 1", i.e. 2
            if (currentVersionTemp == 2) {
                plan.renameKey(PrefKey.ENABLE_DARK_THEME, "useDarkTheme")
                currentVersionTemp++
            }
        }

        override fun onMigrationCompleted(preferences: SharedPreferences) {
        }
    }

    companion object {

        private const val PREFERENCE_VERSION = 3

        /**
         * Create custom `setDefaultPreferenceValues()` where setting some default values require
         * logic and does not covered by [SharedPreferencesHelper.setDefaultPreferenceValues].
         */
        fun setDefaultPreferenceValues(context: Context) {
            SharedPreferencesHelper.setDefaultPreferenceValues(context)

            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            preferences.edit()
                    .putString("backupLocation", SaveDir.DOWNLOADS)
                    .putInt("themeColor", Color.parseColor("#37474F"))
                    .apply()
        }
    }
}
