package com.puzzlebench.clean_marvel_kotlin.data.mapper

import com.puzzlebench.clean_marvel_kotlin.data.database.model.CharacterDB
import com.puzzlebench.clean_marvel_kotlin.data.database.model.ThumbnailDB
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
    fun transform() {

        val mockThumbnailResponse = Thumbnail(PAHT, EXTENSION)
        val mockCharacterResponse = Character(ID, NAME, DESCRIPTION, mockThumbnailResponse)
        val result = mapper.transform(mockCharacterResponse)
        assertBufferooDataEquality(mockCharacterResponse, result)
    }

    @Test
    fun transformToDB() {
        val mockThumbnail = ThumbnailDB(PAHT, EXTENSION)
        val mockCharacter = CharacterDB(ID, NAME, DESCRIPTION, mockThumbnail)
        val result = mapper.transformToResponse(mockCharacter)
        assertBufferooDataEquality(result, mockCharacter)

    }

    private fun assertBufferooDataEquality(character: Character, characterDB: CharacterDB) {
        Assert.assertEquals(characterDB.id, character.id)
        Assert.assertEquals(characterDB.name, character.name)
        Assert.assertEquals(characterDB.description, character.description)
        Assert.assertEquals(characterDB.thumbnail?.path, character.thumbnail.path)
        Assert.assertEquals(characterDB.thumbnail?.extension, character.thumbnail.extension)


    }

}