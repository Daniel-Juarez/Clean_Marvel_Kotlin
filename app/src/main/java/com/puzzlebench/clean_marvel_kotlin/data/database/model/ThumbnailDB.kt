package com.puzzlebench.clean_marvel_kotlin.data.database.model

import io.realm.RealmObject

open class ThumbnailDB(
        open var path: String = "",
        open var extension: String = ""
): RealmObject()