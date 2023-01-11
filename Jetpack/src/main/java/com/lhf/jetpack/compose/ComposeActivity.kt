package com.lhf.jetpack.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lhf.jetpack.R
import com.lhf.jetpack.compose.ui.theme.MyStudyAppTheme

const val TAG = "ComposeActivity"

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyStudyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Row {
        Image(
            painter = painterResource(R.drawable.myicon),
            contentDescription = "test image",
            modifier = Modifier.size(100.dp)
        )
        Column {
            Text(text = "Hello $name!", fontSize = 16.sp, color = Color.Blue)
            var text by remember {
                mutableStateOf("")
            }
            TextField(value = text, onValueChange = {
                Log.d(TAG, "Greeting: TextField onValueChange: $it")
                text = it
            }, placeholder = { Text(text = "请输入") })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyStudyAppTheme {
        LazyColumn(content = {
            for (i in 0..20) {
                item {
                    Greeting("Compose")
                }
            }
        })
    }
}