/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.ui.theme.PuppyData
import com.example.androiddevchallenge.ui.theme.PuppyTheme
import com.example.androiddevchallenge.ui.theme.bean.Puppy
import com.example.androiddevchallenge.ui.theme.purple700
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {
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

                    val data = Gson().fromJson<List<Puppy>>(
                        PuppyData.data,
                        object : TypeToken<List<Puppy>>() {}.type)
                    ShowPuppyList(data)
                }
            }
        }
    }

    @Composable
    fun ShowPuppyList(data:List<Puppy>) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .scrollable(rememberLazyListState(), Orientation.Vertical)
            ) {
                items(data.size) {
                    ImageListItem(data[it])
                }
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun goPuppyDetail(puppy:Puppy) {
        val intent = Intent(this, PuppyDetailActivity::class.java).also {
            it.putExtra("data",puppy)
        }
        intent.resolveActivity(packageManager)?.let {
            startActivity(intent)
        }
    }

    @Composable
    fun ImageListItem(puppy:Puppy) {
        Column(
            Modifier
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .fillMaxWidth()
                .clickable {
                    goPuppyDetail(puppy)
                }) {
            Row(
                Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp)
            ) {
                CoilImage(
                    puppy.authorImage,
                    modifier = Modifier.size(50.dp),
                    contentDescription = "My content description",
                    requestBuilder = {
                        transformations(CircleCropTransformation())
                    }
                )

                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterVertically)
                ) {
                    Text(puppy.author, fontWeight = FontWeight.Bold)
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(puppy.createTime, style = MaterialTheme.typography.body2)
                    }
                }
            }
            CoilImage(
                puppy.image,
                modifier = Modifier
                    .padding(10.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .fillMaxWidth(),
                contentDescription = "My content description",
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}
