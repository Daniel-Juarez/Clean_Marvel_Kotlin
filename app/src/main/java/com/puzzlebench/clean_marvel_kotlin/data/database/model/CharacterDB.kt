package com.puzzlebench.clean_marvel_kotlin.data.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CharacterDB(
    @PrimaryKey open var id: Int = 0,
    open var name: String = "",
    open var description: String = "",
    open var thumbnail: ThumbnailDB? = null
): RealmObject()