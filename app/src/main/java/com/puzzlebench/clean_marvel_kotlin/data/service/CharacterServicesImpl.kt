package com.puzzlebench.clean_marvel_kotlin.data.service

import com.puzzlebench.clean_marvel_kotlin.data.mapper.CharacterMapperService
import com.puzzlebench.clean_marvel_kotlin.data.service.api.MarvelApi
import com.puzzlebench.clean_marvel_kotlin.domain.model.Character
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.Callable


class CharacterServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator(), private val mapper: CharacterMapperService = CharacterMapperService()) : CharacterServices {


    override fun getCharacters(): Single<List<Character>> = Single.fromCallable {
        val callResponse = api.createService(MarvelApi::class.java).getCharacter()
        val response = callResponse.execute()

        if (response.isSuccessful) {
            mapper.transform(response.body()?.data?.result ?: emptyList())
        } else {
            emptyList()
        }
    }

    override fun getCharacterDetail(id: Int): Single<List<Character>> = Single.fromCallable {
        val callResponse = api.createService(MarvelApi::class.java).getCharacterDetail(id)
        val response = callResponse.execute()

        if (response.isSuccessful) {
            mapper.transform(response.body()?.data?.result?: emptyList())
        } else {
            emptyList()
        }
    }
}
