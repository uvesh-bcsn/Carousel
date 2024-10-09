package com.example.carousel.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@ExperimentalLayoutApi
@Composable
fun GridList() {
    fun generateRandomItems(catIndex: Int): List<Cat.Item> {
        val itemCount = Random.nextInt(2, 8)
        return List(itemCount) { index -> Cat.Item("Item $catIndex-${index + 1} Random text") }
    }

    val catList = List(10) { index ->
        Cat("Cat ${index + 1}", generateRandomItems(index + 1))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        catList.forEach { cat ->
            Text(text = "title ${cat.title}")
            FlowRow(maxItemsInEachRow = 4) {
                val catItems = cat.items
                catItems.forEachIndexed { index, item ->

                    val weight = calculateWeight(index, catItems.size)

                    Box(
                        modifier = Modifier
                            .then(
                                if (weight == 1.0f) {
                                    Modifier.weight(weight)
                                } else {
                                    Modifier.fillMaxWidth(weight)
                                }
                            )
                            .height(50.dp)
                            .padding(5.dp)
                            .background(Color.Red)
                    ) {
                        Text(text = weight.toString() + " " + index + " " + (catItems.size - 1))
                    }
                }
            }
        }
    }
}

fun calculateWeight(index: Int, listSize: Int): Float {
    val weight = when (listSize % 4) {
        0 -> 1f
        1 -> if (index % 4 == 0 && index == listSize - 1) 0.25f else 1f
        2 -> if ((index % 4 == 0 && index == listSize - 2) || (index % 4 == 1 && index == listSize - 1)) 0.25f else 1f
        3 -> if ((index % 4 == 0 && index == listSize - 3) || (index % 4 == 1 && index == listSize - 2) || (index % 4 == 2 && index == listSize - 1)) 0.25f else 1f
        else -> 1f
    }

    return weight
}

data class Cat(
    val title: String,
    val items: List<Item>
) {
    data class Item(
        val title: String
    )
}
