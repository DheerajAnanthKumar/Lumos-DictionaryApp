package com.dak.dictionaryapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dak.dictionaryapp.DictionaryViewModel
import com.dak.dictionaryapp.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(modifier: Modifier = Modifier, viewModel: DictionaryViewModel) {
    var searchWord by remember { mutableStateOf("") }
    val wordResult = viewModel.wordDetails

    Scaffold(
        topBar = {
            TopAppBar(
                {
                    Text(text="My Dictionary", fontWeight = FontWeight.Bold,
                        color = Color.Black, fontSize = 22.sp)
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF81C784)
                )
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Column(modifier = Modifier.fillMaxWidth().background(Color(0xFF81C784)).padding(16.dp)) {
                OutlinedTextField(value = searchWord, onValueChange = {searchWord = it},
                    placeholder ={
                        Text(text = "Search a word", color = Color.DarkGray)
                    }, shape = RoundedCornerShape(26.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.fetchWordDetails(searchWord)
                        }) {
                            Icon(Icons.Default.Search, contentDescription = null, tint = Color.LightGray)
                        }
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                when (val state = wordResult.value) {
                    is UiState.IsLoading -> LoadingView(searchWord = searchWord, state = state)
                    is UiState.Error -> ErrorView(state.message)
                    is UiState.Success -> {
                        val wordData = state.detail

                        if (wordData != null && wordData.meanings.isNotEmpty()) {
                            val wordMeaning = wordData.meanings.first()

                            // CARD 1: Basic Word info and Definition
                            RoundedCard {
                                Text(
                                    text = wordData.word,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(6.dp))

                                Row {
                                    Text(
                                        text = wordData.phonetic ?: "",
                                        color = Color.Black,
                                        fontSize = 14.sp
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = wordMeaning.partOfSpeech,
                                        color = Color.Black,
                                        fontSize = 14.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(12.dp))

                                // Accessing definition from the list inside Meaning
                                Text(
                                    text = wordMeaning.definitions.firstOrNull()?.definition ?: "No definition available",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            val synonymsAvailable = !wordMeaning.synonyms.isNullOrEmpty()
                            val antonymsAvailable = !wordMeaning.antonyms.isNullOrEmpty()

                            if (synonymsAvailable || antonymsAvailable) {
                                RoundedCard {
                                    if (synonymsAvailable) {
                                        Text(
                                            text = "Synonyms",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = wordMeaning.synonyms?.joinToString() ?: "",
                                            color = Color.Black
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    if (antonymsAvailable) {
                                        Text(
                                            text = "Antonyms",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = wordMeaning.antonyms?.joinToString() ?: "",
                                            color = Color.Black
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }

                            val wordExample = wordMeaning.definitions.firstOrNull()?.example

                            if (!wordExample.isNullOrBlank()) {
                                RoundedCard {
                                    Text(
                                        text = "Example",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "\"$wordExample\"",
                                        fontStyle = FontStyle.Italic,
                                        color = Color.Black
                                    )
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        } else {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(text = "No definitions found.", color = Color.Gray)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun RoundedCard(content: @Composable ColumnScope.() -> Unit) {
    Card(shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp),
            content = content)
    }
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    searchWord: String,
    state: UiState
) {
    Box(
        modifier = modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state is UiState.IsLoading && searchWord.isNotBlank()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = Color(0xFF66BB6A))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Searching...", color = Color.Gray)
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color.LightGray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Enter a word to see its definition",
                    fontSize = 18.sp, // Professional size
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ErrorView(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            fontSize = 22.sp,
            color = Color.Red,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 28.sp
        )
    }
}