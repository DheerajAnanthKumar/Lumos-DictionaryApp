package com.dak.dictionaryapp

import com.dak.dictionaryapp.model.DictionaryData
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("api/v2/entries/en/{word}")
    suspend fun getWordMeaning(
        @Path("word") word: String
    ): List<DictionaryData>
}