package com.puzzlebench.clean_marvel_kotlin

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {
    val REALM_DATABASE_NAME = "character"

    override fun onCreate() {
        super.onCreate()

        val realmConfiguration = RealmConfiguration.Builder(applicationContext)
        realmConfiguration.name(REALM_DATABASE_NAME)
        realmConfiguration.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(realmConfiguration.build())
    }
}
