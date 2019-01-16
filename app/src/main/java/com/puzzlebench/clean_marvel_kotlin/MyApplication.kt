package com.puzzlebench.clean_marvel_kotlin

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmConfiguration.Builder

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        var realmConfiguration = RealmConfiguration.Builder(applicationContext)
        realmConfiguration.name("character")
        realmConfiguration.deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(realmConfiguration.build())
    }
}