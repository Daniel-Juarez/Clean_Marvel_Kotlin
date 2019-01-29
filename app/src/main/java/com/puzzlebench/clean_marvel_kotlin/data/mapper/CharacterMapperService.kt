package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.service.response.CharacterResponse
import com.puzzlebench.clean_marvel_kotlin.data.service.response.ThumbnailResponse
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail


class CharacterMapperService :BaseMapperResponse<CharacterResponse, Character> {

    override fun transform(characterResponse: CharacterResponse) = Character(
            characterResponse.id,
            characterResponse.name,
            characterResponse.description,
            transformToThumbnail(characterResponse.thumbnail)
    )

    fun transformToThumbnail(thumbnailResponse: ThumbnailResponse) = Thumbnail(
            thumbnailResponse.path,
            thumbnailResponse.extension
    )

    fun transform(charactersResponse: List<CharacterResponse>) = charactersResponse.map { transform(it) }
}
