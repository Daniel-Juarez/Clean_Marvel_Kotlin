package com.puzzlebench.clean_marvel_kotlin.data.database.model

import io.realm.RealmObject

open class ThumbnailDB(
        var path: String = "",
        var extension: String = ""
): RealmObject()
