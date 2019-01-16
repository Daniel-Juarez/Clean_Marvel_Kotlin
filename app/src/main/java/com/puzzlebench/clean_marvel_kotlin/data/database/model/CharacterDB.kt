package com.puzzlebench.clean_marvel_kotlin.data.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterDB(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var thumbnail: ThumbnailDB? = null
): RealmObject()
