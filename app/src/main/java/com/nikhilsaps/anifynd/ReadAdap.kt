package com.nikhilsaps.anifynd

import android.content.res.Resources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ListItem( modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth()) {
        Text(
            text = "nikhil",
            )
        // … other composables required for displaying `data`
    }
}
