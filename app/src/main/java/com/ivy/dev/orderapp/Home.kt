package com.ivy.dev.orderapp

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ivy.dev.orderapp.data.MenuDao
import com.ivy.dev.orderapp.ui.theme.cloud
import com.ivy.dev.orderapp.ui.theme.gray
import com.ivy.dev.orderapp.ui.theme.green
import com.ivy.dev.orderapp.ui.theme.yellow
import java.util.Locale


@Composable
fun Home(menuDao: MenuDao, navController: NavHostController) {
    var searchPhrase by remember {
        mutableStateOf("")
    }

    Column(
        androidx.compose.ui.Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = " Restaurant Logo",
                modifier = Modifier
                    .weight(3F)
                    .size(50.dp)
                    .align(Alignment.CenterVertically)
                    .padding(top = 5.dp, bottom = 5.dp),
                contentScale = ContentScale.Fit
            )

            //Spacer(modifier = Modifier.width(50.dp))
            Box(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Profile.route) {
                            launchSingleTop = true
                        }
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile photo",
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        HeroSection(menuDao)

    }
}

@Composable
fun HeroSection(menuDao: MenuDao) {
    var searchPhrase by remember {
        mutableStateOf("")
    }
    var selectedCategories by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(green)
            .fillMaxWidth()
    ) {
        Text(
            text = "Little Lemon",
            color = yellow,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Text(
            text = "Chicago",
            color = cloud,
            fontSize = 24.sp,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .weight(1F)
                    .padding(vertical = 8.dp),
                text = "We are a family-owned Mediterranean restaurant, focused on traditional " +
                        "recipes served with a modern twist",
                style = MaterialTheme.typography.subtitle1,
                color = cloud,
                fontSize = 20.sp,
            )

            Image(
                painter = painterResource(id = R.drawable.heroimage),
                contentDescription = "Hero content ",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(height = 140.dp, width = 120.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
        }
        TextField(
            value = searchPhrase,
            onValueChange = { newValue -> searchPhrase = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = MaterialTheme.colors.onSurface,
                cursorColor = MaterialTheme.colors.onSurface,
                leadingIconColor = MaterialTheme.colors.onSurface
            ),
            placeholder = { Text("Enter Search Phrase") }
        )
    }

    Column(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        chipsCategories(selectedCategories) { category -> selectedCategories = category.toString() }
        MenuDish(menuDao = menuDao, searchPhrase = searchPhrase, selectedCategories)
    }

}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun MenuDish(menuDao: MenuDao, searchPhrase: String, category: String) {
    val menuItems by menuDao.getAllMenu().observeAsState(emptyList())

    val filteredMenuItems = if (!menuItems.isNullOrEmpty()) {
        if (category.isNullOrEmpty()) {
            menuItems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        } else {
            menuItems.filter {
                it.category == category && it.title.contains(
                    searchPhrase,
                    ignoreCase = true
                )
            }
        }
    } else {
        menuItems
    }

    LazyColumn {
        items(filteredMenuItems) { menuItem ->
            Card(onClick = {}, Modifier.padding(start = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column() {
                        Text(
                            text = menuItem.title,
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = menuItem.description,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth(0.75F)
                                .padding(top = 5.dp, bottom = 5.dp)
                        )

                        Text(
                            text = "$ %.2f".format(menuItem.price.toDouble()),
                            style = MaterialTheme.typography.body2
                        )
                    }

                    GlideImage(
                        model = menuItem.image,
                        contentDescription = " url image",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Divider(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                thickness = 1.dp,
                color = yellow
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun chipsCategories(selectedCategory: String, onCategorySelected: (String?) -> Unit) {
    val categories = listOf("Starters", "Main", "Desserts", "Drinks")
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .horizontalScroll(
                rememberScrollState()
            )
    ) {
        Text(text = "Order for delivery!", fontSize = 20.sp)
    }
    BoxWithConstraints {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight()
                .horizontalScroll(
                    rememberScrollState()
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (category in categories) {

                Chip(
                    onClick = { onCategorySelected(category.lowercase(Locale.ROOT)) },
                    modifier = Modifier.padding(end = 5.dp),
                    colors = ChipDefaults.chipColors(
                        backgroundColor =
                        gray
                    ),
                    content = {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.body2,
                            color = green
                        )
                    }
                )
            }
        }
    }
}