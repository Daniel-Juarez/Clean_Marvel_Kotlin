package com.puzzlebench.clean_marvel_kotlin.data.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

const val ID_CHARACTER_DEFAULT_DB = 0

open class CharacterDB(
    @PrimaryKey var id: Int = ID_CHARACTER_DEFAULT_DB,
    var name: String = "",
    var description: String = "",
    var thumbnail: ThumbnailDB? = null
): RealmObject()
