package com.example.androidcalculatorapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorPage(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {

    val query = viewModel.query.observeAsState()
    val result = viewModel.result.observeAsState()

    val listItems = listOf(
        "C",
        "(",
        ")",
        "/",
        "7",
        "8",
        "9",
        "*",
        "4",
        "5",
        "6",
        "+",
        "1",
        "2",
        "3",
        "-",
        "AC",
        "0",
        ".",
        "="
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Spacer(modifier = Modifier.weight(.2f))
            Text(
                text = query.value?:"",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = result.value?:"",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                items(listItems) {
                    CalcButton(
                        it,
                        onClick = { viewModel.ButtonOnClick(it) }
                    )
                }
            }
        }

    }
}

@Composable
fun CalcButton(item: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        FloatingActionButton(
            onClick = onClick,
            shape = CircleShape,
            containerColor = ButtonColor(item)
        ) {
            Text(
                text = item,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


fun ButtonColor(item: String): Color {
    return when (item) {
        "C", "AC" -> Color.Red
        "(", ")" -> Color.LightGray
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "." -> Color.Cyan
        "/", "*", "+", "-", "=" -> Color.DarkGray
        else -> Color.White
    }
}