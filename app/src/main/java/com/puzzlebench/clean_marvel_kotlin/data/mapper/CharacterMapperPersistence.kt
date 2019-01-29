package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.database.model.RCharacter
import com.puzzlebench.clean_marvel_kotlin.data.database.model.RThumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail


class CharacterMapperPersistence : BaseMapperRepository<Character, RCharacter> {

    override fun transform(character: Character) = RCharacter(
            character.id,
            character.name,
            character.description,
            transformToThumbnail(character.thumbnail)
    )

    override fun transformToResponse(type: RCharacter) = Character(
            type.id,
            type.name,
            type.description,
            transformToThumbnailResponse(type.RThumbnail!!)
    )

    fun transformToThumbnail(thumbnail: Thumbnail) = RThumbnail(
            thumbnail.path,
            thumbnail.extension
    )

    fun transformToThumbnailResponse(RThumbnail: RThumbnail) = Thumbnail(
            RThumbnail.path,
            RThumbnail.extension
    )

    fun transform(listCharacters: List<Character>) = listCharacters.map { transform(it) }

    fun transformDB(listCharacters: List<RCharacter>) = listCharacters.map { transformToResponse(it) }
}
