package com.example.weather_compose.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onSettingClick: () -> Unit,
) {
    Column(
       horizontalAlignment = AbsoluteAlignment.Left,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
            .background(Color.Blue.copy(alpha = 0.2f))


    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()



        ){
            Column {

            AnimatedVisibility(visible = state.status == Status.LOADING) {
            CircularProgressIndicator()
            Text(text = "Loading...")
        }
                AnimatedVisibility(visible = state.status == Status.ERROR) {
                    Column {
                    Text(
                        text = "Error",
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.error)
                    Text(
                        text = state.error,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.error)
                }

            }

        }
    }
        AnimatedVisibility(visible = state.status == Status.SUCCESS) {

            Column {


                Text(
                    text = "🌤️Weather",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Temperature: ${state.result.main?.temp?: "Not Available"}",
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.scrim,
                    fontWeight = FontWeight.Medium
                )
            }

        }

    }

}



