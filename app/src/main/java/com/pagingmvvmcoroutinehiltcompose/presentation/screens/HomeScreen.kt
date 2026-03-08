package com.pagingmvvmcoroutinehiltcompose.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.pagingmvvmcoroutinehiltcompose.R
import com.pagingmvvmcoroutinehiltcompose.domain.model.Character
import com.pagingmvvmcoroutinehiltcompose.presentation.component.CommonHeader
import com.pagingmvvmcoroutinehiltcompose.presentation.shimmers.HomeListShimmer
import com.pagingmvvmcoroutinehiltcompose.presentation.shimmers.HomeScreenShimmer
import com.pagingmvvmcoroutinehiltcompose.presentation.viewModel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onClickCharacter: (Character) -> Unit
){

    val characters = viewModel.characters.collectAsLazyPagingItems()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        CommonHeader(
            text = stringResource(R.string.home),
            onIconClick = {},
            showBackIcon = false
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            // 🔹 First Load → Show Shimmer
            if (characters.loadState.refresh is LoadState.Loading) {

                items(8) {
                    HomeScreenShimmer()
                }

            } else {

                // 🔹 Show API Data
                items(characters.itemCount) { index ->
                    val character = characters[index]

                    if (character != null) {
                        HomeItem(character) {
                            onClickCharacter(character)
                        }
                    }
                }

            }

            // 🔹 Pagination Loader
            if (characters.loadState.append is LoadState.Loading) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp)
                        )

                    }

                }

            }

        }
    }

}




@Composable
fun HomeItem(
    character : Character,
    modifier : Modifier = Modifier,
    onClickCharacter : (Int) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "",
            modifier = modifier.padding(vertical = 10.dp)
                .fillMaxWidth()
                .clip(RectangleShape)
                .clickable{
                    onClickCharacter(character.id?:0)
                },
            contentScale = ContentScale.Crop
        )
    }
}