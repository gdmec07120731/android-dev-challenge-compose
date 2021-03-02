package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.PuppyTheme
import com.example.androiddevchallenge.ui.theme.bean.Puppy
import com.example.androiddevchallenge.ui.theme.purple700
import dev.chrisbanes.accompanist.coil.CoilImage

class PuppyDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PuppyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = getString(R.string.app_name),
                                    color = Color.White
                                )
                            },
                            backgroundColor = purple700, elevation = 0.dp
                        )
                    }
                ) {
                    val puppy = intent?.getSerializableExtra("data") as Puppy
                    ShowDetail(puppy)
                }
            }
        }
    }

    @Composable
    fun ShowDetail(puppy: Puppy) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            CoilImage(
                puppy.image,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentDescription = "My content description",
                contentScale = ContentScale.FillBounds,
            )

            Text(
                text = puppy.detail,
                Modifier.padding(10.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}


