package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.service.response.CharacterResponse
import com.puzzlebench.clean_marvel_kotlin.data.service.response.ThumbnailResponse
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import junit.framework.Assert
import org.junit.Before
import org.junit.Test


class CharacterMapperServiceTest {
    private lateinit var mapper: CharacterMapperService
    private val ID = 0
    private val NAME = "sport"
    private val DESCRIPTION = "some description"
    private val PAHT = "http:/some.com/"
    private val EXTENSION = ".PNG"

    @Before
    fun setUp() {
        mapper = CharacterMapperService()
    }

    @Test
    fun `Transform character from Response model to Domain model`() {
        val mockThumbnailResponse = ThumbnailResponse(PAHT, EXTENSION)
        val mockCharacterResponse = CharacterResponse(ID, NAME, DESCRIPTION, mockThumbnailResponse)
        val result = mapper.transform(mockCharacterResponse)
        assertBufferooDataEquality(mockCharacterResponse, result)
    }

    private fun assertBufferooDataEquality(characterResponse: CharacterResponse,
                                           character: Character) {
        Assert.assertEquals(characterResponse.id, character.id)
        Assert.assertEquals(characterResponse.name, character.name)
        Assert.assertEquals(characterResponse.description, character.description)
        Assert.assertEquals(characterResponse.thumbnail.path, character.thumbnail.path)
        Assert.assertEquals(characterResponse.thumbnail.extension, character.thumbnail.extension)
    }
}
