package com.example.profilecardlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profilecardlayout.ui.theme.MyTheme
import com.example.profilecardlayout.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                //這裡不用 MyTheme(),而用 MyTheme {} 的原因,不知是否為Lambda處理的關係
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(topBar = { AppBar() } ) {  // AppBar()也要加 {}, 因為他是composable
        Surface(modifier = Modifier.fillMaxSize(),
            //color = Color.LightGray
        ) {
            Column() {
                ProfileCard()
                ProfileCard()
            }

        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = { Icon(Icons.Default.Home,
            "123",
            Modifier.padding(horizontal = 12.dp))},
        title = { Text("Messaging Application users") }
        // 1. Icon function後面要加 contentDescription,否則會錯(可看原始code)
        // 2. 上述二個要加 { } 是因為裡面要放composable,是一堆程式的集合,所以要用 {}
    )
}


@Composable
fun ProfileCard() {
    //Card(modifier = Modifier.fillMaxWidth(),  //First code
    Card(modifier = Modifier
        .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top),    //Second modify
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            //modifier = Modifier.wrapContentSize(),
            modifier = Modifier.fillMaxWidth(), //如果不設這個,而是使用wrapContentSize(),就算設了 Arrangement.Start, 畫面也不會移到左邊,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) { //Card 只能放一個Composable,所以要用Row(), Column() 來放多個Composable
            ProfilePicture()
            ProfileContent()
        }
    }
}

@Composable
fun ProfilePicture() {
    Card(
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.lightGreen),
           // color 的設定,要變成 theme/Color.kt  的 @Composable 設定,才能用
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) { // 因為要把照片變圓型,所以外面要加一個Card來協助作成圓形的樣子
        Image(painter = painterResource(id = R.drawable.profile_picture), contentDescription = "",
            modifier = Modifier.size(72.dp),   //寫法已跟作者的方式不同了
            contentScale = ContentScale.Crop)
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "John Doe",
            style = MaterialTheme.typography.h5
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "Active now",
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        MainScreen()
    }
}