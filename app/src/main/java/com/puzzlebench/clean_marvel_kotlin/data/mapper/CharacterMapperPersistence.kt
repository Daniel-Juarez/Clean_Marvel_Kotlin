package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.database.model.CharacterDB
import com.puzzlebench.clean_marvel_kotlin.data.database.model.ThumbnailDB
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail


class CharacterMapperPersistence : BaseMapperRepository<Character, CharacterDB> {

    override fun transform(character: Character) = CharacterDB(
            character.id,
            character.name,
            character.description,
            transformToThumbnail(character.thumbnail)
    )

    override fun transformToResponse(type: CharacterDB) = Character(
            type.id,
            type.name,
            type.description,
            transformToThumbnailResponse(type.thumbnail!!)
    )

    fun transformToThumbnail(thumbnail: Thumbnail) = ThumbnailDB(
            thumbnail.path,
            thumbnail.extension
    )

    fun transformToThumbnailResponse(thumbnailDB: ThumbnailDB) = Thumbnail(
            thumbnailDB.path,
            thumbnailDB.extension
    )


    fun transform(listCharacters: List<Character>) = listCharacters.map { transform(it) }

    fun transformDB(listCharacters: List<CharacterDB>) = listCharacters.map { transformToResponse(it) }
}
