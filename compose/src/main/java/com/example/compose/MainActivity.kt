package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MessageCard(Message("나", "너무 졸려...."))
        }
    }
}
data class Message(val author : String, val body : String)

@Composable
private fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(text = message.author)
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(text = message.author)
            Text(text = message.body)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard(){
    MessageCard(
        Message("나", "너무 졸리다")
    )
}