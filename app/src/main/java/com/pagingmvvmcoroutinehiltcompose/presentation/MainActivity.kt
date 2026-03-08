package com.pagingmvvmcoroutinehiltcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.pagingmvvmcoroutinehiltcompose.presentation.navigation.Navigation
import com.pagingmvvmcoroutinehiltcompose.presentation.ui.theme.PagingMvvmCoroutineHiltComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagingMvvmCoroutineHiltComposeTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Navigation(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding)

                    )
                }
            }
        }
    }
}