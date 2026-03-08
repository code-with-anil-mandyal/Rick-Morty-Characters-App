package com.pagingmvvmcoroutinehiltcompose.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pagingmvvmcoroutinehiltcompose.R
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character
import com.pagingmvvmcoroutinehiltcompose.presentation.component.CommonHeader
import com.pagingmvvmcoroutinehiltcompose.presentation.state.CharactersState
import com.pagingmvvmcoroutinehiltcompose.presentation.viewModel.DetailsViewModel

@Composable
fun DetailsScreen(
    character : Character,
    onBackClick : () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
){

    LaunchedEffect(Unit) {
        viewModel.getCharacterDetails(character.id?:0)
    }

    val state by viewModel.characterDetailsState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        CommonHeader(
            text = character.name?:"",
            onIconClick = {
                onBackClick()
            },
            showBackIcon = true
        )

        Spacer(Modifier.height(10.dp))

            if(state.isLoading){

                Progress()

            }else if(state.errorMsg?.isNotEmpty() == true){
                ErrorMessage(viewModel, state, character.id?:0)
            }

        if (state.data != null){
            DetailsScreenContent(modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 20.dp)
                .fillMaxWidth(),state.data!!)
        }

    }
}

@Composable
fun DetailsScreenContent(modifier: Modifier = Modifier, character: Character) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = character.image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
                .clip(RectangleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(10.dp))

        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = character.name?:"",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
            )
                Spacer(Modifier.height(10.dp))

            Text(
                text = character.gender?:"",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic
                )
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = character.species?:"",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    fontStyle = FontStyle.Italic
                )
            )
        }
        
    }
}

@Composable
private fun Progress() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorMessage(
    viewModel: DetailsViewModel,
    state : CharactersState,
    id : Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.errorMsg?:"",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
                color = Color.Red
            )
        )
        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.getCharacterDetails(id)
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Red
            )
        ) {
            Text(
                text = stringResource(R.string.retry),
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 15.sp,
                )
            )
        }
    }
}