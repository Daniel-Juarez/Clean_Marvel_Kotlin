package com.puzzlebench.clean_marvel_kotlin

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val realmConfiguration = RealmConfiguration.Builder(applicationContext)
        realmConfiguration.name("character")
        realmConfiguration.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(realmConfiguration.build())
    }
}
