package com.dak.dictionaryapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dak.dictionaryapp.model.DictionaryData
import kotlinx.coroutines.launch
import retrofit2.HttpException

sealed class UiState(){
    data object IsLoading: UiState()
    data class Success(val detail: DictionaryData?): UiState()
    data class Error(val message: String): UiState()

}


class DictionaryViewModel : ViewModel() {
    private var _wordDetails: MutableState<UiState> = mutableStateOf(UiState.IsLoading)
    var wordDetails: State<UiState> = _wordDetails

    fun fetchWordDetails(word: String) {
        if (word.isBlank()) return

        viewModelScope.launch {
            try {
                _wordDetails.value = UiState.IsLoading
                val details = RetrofitInstance.apiService.getWordMeaning(word)
                _wordDetails.value = UiState.Success(details.firstOrNull())
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    _wordDetails.value = UiState.Error("Word not found. Please check your spelling.")
                } else {
                    _wordDetails.value = UiState.Error("Server error: ${e.code()}")
                }
            } catch (e: Exception) {
                // This catches internet connection issues or parsing bugs
                _wordDetails.value = UiState.Error("No internet connection or unknown error.")
            }
        }
    }
}