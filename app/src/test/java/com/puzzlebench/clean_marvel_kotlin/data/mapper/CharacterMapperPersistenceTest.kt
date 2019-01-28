package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.database.model.RCharacter
import com.puzzlebench.clean_marvel_kotlin.data.database.model.RThumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import junit.framework.Assert
import org.junit.Before
import org.junit.Test


class CharacterMapperPersistenceTest {
    private lateinit var mapper: CharacterMapperPersistence
    private val ID = 0
    private val NAME = "sport"
    private val DESCRIPTION = "some description"
    private val PAHT = "http:/some.com/"
    private val EXTENSION = ".PNG"

    @Before
    fun setUp() {
        mapper = CharacterMapperPersistence()
    }

    @Test
    fun `Transform character from Domain model to Realm model`() {

        val mockThumbnailResponse = Thumbnail(PAHT, EXTENSION)
        val mockCharacterResponse = Character(ID, NAME, DESCRIPTION, mockThumbnailResponse)
        val result = mapper.transform(mockCharacterResponse)
        assertBufferooDataEquality(mockCharacterResponse, result)
    }

    @Test
    fun `Transform character from Realm model to Domain model`() {
        val mockThumbnail = RThumbnail(PAHT, EXTENSION)
        val mockCharacter = RCharacter(ID, NAME, DESCRIPTION, mockThumbnail)
        val result = mapper.transformToResponse(mockCharacter)
        assertBufferooDataEquality(result, mockCharacter)
    }

    private fun assertBufferooDataEquality(character: Character, RCharacter: RCharacter) {
        Assert.assertEquals(RCharacter.id, character.id)
        Assert.assertEquals(RCharacter.name, character.name)
        Assert.assertEquals(RCharacter.description, character.description)
        Assert.assertEquals(RCharacter.RThumbnail?.path, character.thumbnail.path)
        Assert.assertEquals(RCharacter.RThumbnail?.extension, character.thumbnail.extension)
    }
}
