package com.example.profilecardlayout

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.profilecardlayout.ui.UserProfile
import com.example.profilecardlayout.ui.theme.MyTheme
import com.example.profilecardlayout.ui.theme.lightGreen
import com.example.profilecardlayout.ui.userProfileList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                //這裡不用 MyTheme(),而用 MyTheme {} 的原因,不知是否為Lambda處理的關係
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UserListScreen(userProfiles, navController)
        }
        composable("users_details") {
            UserProfileDetailScreen()
        }
    }

}
@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavController?) { //navController型態要加?,表示可以為null
    Scaffold(topBar = { AppBar() } ) {  // AppBar()也要加 {}, 因為他是composable
        Surface(modifier = Modifier.fillMaxSize(),
            //color = Color.LightGray
        ) {
            LazyColumn {
                items(userProfiles) { userProfile ->
                    Log.v("MainActivity","data in") //當在scroll時,這裡會跟著跑動
                    ProfileCard(userProfile = userProfile) {  //要在這裡做 Navigation的處理
                        navController?.navigate("users_details") //1.要選參數是route的那個.  2. 在Preview沒有 navController,值為null,所以要用?
                    } //Lambda trailing,最後面的參數,就可以用 { } 包起來
                }
            }
        }
    }
}

@Composable
fun UserProfileDetailScreen(userProfile: UserProfile = userProfileList[0]) {
    Scaffold(topBar = { AppBar() } ) {  // AppBar()也要加 {}, 因為他是composable
        Surface(
            modifier = Modifier.fillMaxSize(),
            //color = Color.LightGray
        ) {
            Column(
                //modifier = Modifier.wrapContentSize(),
                modifier = Modifier.fillMaxWidth(), //如果不設這個,而是使用wrapContentSize(),就算設了 Arrangement.Start, 畫面也不會移到左邊,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) { //Card 只能放一個Composable,所以要用Row(), Column() 來放多個Composable
                ProfilePicture(userProfile.pictureUrl, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
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
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {    //重要,有click的處理
    //Card(modifier = Modifier.fillMaxWidth(),  //First code
    Card(modifier = Modifier
        .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top) //Second modify
        .clickable(onClick = { clickAction.invoke() }), //重要,有click的處理
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            //modifier = Modifier.wrapContentSize(),
            modifier = Modifier.fillMaxWidth(), //如果不設這個,而是使用wrapContentSize(),就算設了 Arrangement.Start, 畫面也不會移到左邊,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) { //Card 只能放一個Composable,所以要用Row(), Column() 來放多個Composable
            ProfilePicture(userProfile.pictureUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus)
                MaterialTheme.colors.lightGreen
            else Color.Red
        ), // color 的設定,要變成 theme/Color.kt  的 @Composable 設定,才能用
        modifier = Modifier
            .padding(16.dp)
            .size(imageSize),
        elevation = 4.dp
    ) { // 因為要把照片變圓型,所以外面要加一個Card來協助作成圓形的樣子
        Image(
            painter = rememberImagePainter(data = pictureUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },),
            contentDescription = "Profile picture description",
            modifier = Modifier.size(72.dp)
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        //.fillMaxWidth() //這樣只會使用他的值的最大寬度,而非螢幕的最大寬度,這不符合第二畫面的需求,所以要註解掉
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(LocalContentAlpha provides (
                if (onlineStatus) 1f
                else ContentAlpha.medium
                )
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.h5
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (onlineStatus)
                    "Active now"
                else "Offline now",
                style = MaterialTheme.typography.body2
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailPreview() {
    MyTheme {
        UserProfileDetailScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    MyTheme {
        UserListScreen(userProfiles = userProfileList, null)    //在Preview裡,沒有navigation,所以參數2是null
    }
}