package com.example.snackordering

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snackordering.ui.theme.SnackOrderingTheme

class MainPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackOrderingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF1F5F9) // Light background color for aesthetic look
                ) {
                    FinalView(this)
                }
            }
        }
    }
}

@Composable
fun TopPart() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF6200EA), Color(0xFF03DAC5))
                )
            )
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu Icon",
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .background(Color.White)
                .padding(8.dp),
            tint = Color(0xFF6200EA)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Location",
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
            Row {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.Red,
                    modifier = Modifier.size(16.dp)
                )
                Text(text = "Accra", color = Color.White, fontSize = 14.sp)
            }
        }

        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notification Icon",
            modifier = Modifier
                .size(45.dp)
                .background(Color.White, shape = CircleShape)
                .padding(8.dp),
            tint = Color(0xFF6200EA)
        )
    }
}

@Composable
fun CardPart() {
    Card(
        modifier = Modifier
            .size(width = 350.dp, height = 170.dp)
            .shadow(8.dp, RoundedCornerShape(20.dp))
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color(0xFF6200EA)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Get Special Discounts",
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "up to 85%",
                    style = MaterialTheme.typography.h4.copy(color = Color.Yellow)
                )
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(Color(0xFF03DAC5))
                ) {
                    Text(text = "Claim Voucher", color = Color.White)
                }
            }
            Image(
                painter = painterResource(id = R.drawable.food_tip_im),
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
        }
    }
}

@Composable
fun PopularFood(
    @DrawableRes drawable: Int,
    @StringRes text1: Int,
    context: Context
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .width(250.dp)
            .shadow(6.dp, RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star Icon",
                    tint = Color.Yellow
                )
                Text(text = "4.3", fontWeight = FontWeight.Bold, color = Color.Gray)
            }
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "Food Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFECEFF1))
            )
            Text(
                text = stringResource(id = text1),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF6200EA)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$50",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EA)
                    )
                )
                IconButton(onClick = {
                    val intent = Intent(context, TargetActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping Cart",
                        tint = Color(0xFF03DAC5)
                    )
                }
            }
        }
    }
}

private val FoodList = listOf(
    DrawableStringPair(R.drawable.sandwish, R.string.sandwich),
    DrawableStringPair(R.drawable.burger, R.string.burgers),
    DrawableStringPair(R.drawable.pack, R.string.pack),
    DrawableStringPair(R.drawable.pasta, R.string.pasta),
    DrawableStringPair(R.drawable.tequila, R.string.tequila),
    DrawableStringPair(R.drawable.wine, R.string.wine),
    DrawableStringPair(R.drawable.salad, R.string.salad),
    DrawableStringPair(R.drawable.pop, R.string.popcorn)
)

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text1: Int
)

@Composable
fun App(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
            TopPart()
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardPart()

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Popular Food",
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF6200EA)
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        PopularFoodColumn(context)
    }
}

@Composable
fun PopularFoodColumn(context: Context) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(FoodList) { item ->
                PopularFood(context = context, drawable = item.drawable, text1 = item.text1)
            }
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FinalView(mainPage: MainPage) {
    SnackOrderingTheme {
        Scaffold {
            val context = LocalContext.current
            App(context)
        }
    }
}
