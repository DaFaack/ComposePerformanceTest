package com.aaronwaller.composeperformance

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aaronwaller.composeperformance.ui.theme.MyTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val tabList = listOf(
        "Bisasam",
        "Glumanda",
        "Schiggy",
        "Raupy",
        "Fukano",
        "Machollo",
        "Knofensa",
        "Garados",
        "Relaxo",
        "Mogelbaum"
    )

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val pagerState: PagerState = rememberPagerState(initialPage = 2)
                val coroutineScope = rememberCoroutineScope()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    ScrollableTabRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentColor = Color.White,
                        backgroundColor = Color(0xff37474f),
                        edgePadding = 8.dp,
                        selectedTabIndex = pagerState.currentPage,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                                color = Color(0xFFFF738E)
                            )
                        }
                    ) {

                        tabList.forEachIndexed { index, title ->
                            Tab(
                                text = { Text(title) },
                                selected = pagerState.currentPage == index,
                                selectedContentColor = Color(0xFFFF738E),
                                unselectedContentColor = Color.White,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                            )
                        }
                    }

                    HorizontalPager(
                        state = pagerState,
                        count = tabList.size
                    ) { page: Int ->

                        when (page) {
                            0 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png", name = "Bisasam")
                            1 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png", name = "Glumanda")
                            2 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/007.png", name = "Schiggy")
                            3 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/010.png", name = "Raupy")
                            4 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/058.png", name = "Fukano")
                            5 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/066.png", name = "Machollo")
                            6 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/069.png", name = "Knofensa")
                            7 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/130.png", name = "Garados")
                            8 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/143.png", name = "Relaxo")
                            9 -> ImageList(imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/185.png", name = "Mogelbaum")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageList(imageUrl: String, name: String) {

    LazyVerticalGrid(columns = GridCells.Fixed(5), content = {
        items(40) {
            ImageItem(imageUrl, name)
        }
    })

}

@Composable
fun ImageItem(imageUrl: String, name: String){
    Card(
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 7.dp, end = 4.dp, top = 7.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            tint = Color(0xffff4d57),
                            modifier = Modifier
                                .size(18.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "0",
                        color = Color.LightGray,
                        fontSize = 13.sp
                    )
                }
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "",
                    tint = Color.LightGray,
                    modifier = Modifier
                        .size(18.dp))
            }

            Box(
                modifier = Modifier
                    .padding(top = 5.dp),
            ) {

                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(start = 3.dp, end = 3.dp), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 5.dp))

            }
        }

    }
}
