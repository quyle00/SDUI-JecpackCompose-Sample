package com.quyt.sdui_jecpack_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter

object ComponentUtils {

    @Composable
    fun BuildComponent(data: ComponentModel) {
        when (data.type) {
            ComponentModel.COLUMN -> BuildColumn(data)
            ComponentModel.ROW -> BuildRow(data)
            ComponentModel.TEXT -> BuildText(data)
            ComponentModel.BUTTON -> BuildButton(data)
            ComponentModel.IMAGE -> BuildImage(data)
            ComponentModel.SPACER -> BuildSpacer(data)
            else -> {
                Log.d("ComponentUtils", "Not support type: ${data.type}")
            }
        }
    }

    @Composable
    private fun BuildColumn(column: ComponentModel) {
        val horizontalAlignment = when (column.props?.alignment) {
            "CENTER" -> {
                Alignment.CenterHorizontally
            }

            "END" -> {
                Alignment.End
            }

            else -> {
                Alignment.Start
            }
        }
        val bgColor = column.props?.modifier?.backgroundColor
        val margin = column.props?.modifier?.margin ?: 0
        val rounded = column.props?.modifier?.rounded ?: 0
        val modifier = Modifier
            .fillMaxSize()
            .padding(margin.dp)
            .clip(RoundedCornerShape(rounded.dp))
            .background(Color(bgColor?.toColorInt() ?: 0))
        //
        Column(
            horizontalAlignment = horizontalAlignment,
            modifier = modifier
        ) {
            column.children?.forEach {
                BuildComponent(it)
            }
        }
    }

    @Composable
    private fun BuildRow(row : ComponentModel){
        val verticalAlignment = when (row.props?.alignment) {
            "CENTER" -> {
                Alignment.CenterVertically
            }

            "BOTTOM" -> {
                Alignment.Bottom
            }

            "TOP" -> {
                Alignment.Top
            }

            else -> {
                Alignment.CenterVertically
            }
        }
        val horizontalArrangement = when (row.props?.arrangement) {
            "CENTER" -> {
                androidx.compose.foundation.layout.Arrangement.Center
            }

            "END" -> {
                androidx.compose.foundation.layout.Arrangement.End
            }

            "SPACE_BETWEEN" -> {
                androidx.compose.foundation.layout.Arrangement.SpaceBetween
            }

            "SPACE_AROUND" -> {
                androidx.compose.foundation.layout.Arrangement.SpaceAround
            }

            "SPACE_EVENLY" -> {
                androidx.compose.foundation.layout.Arrangement.SpaceEvenly
            }

            else -> {
                androidx.compose.foundation.layout.Arrangement.Start
            }

        }
        val bgColor = row.props?.modifier?.backgroundColor
        val margin = row.props?.modifier?.margin ?: 0
        val rounded = row.props?.modifier?.rounded ?: 0
        val width = row.props?.modifier?.width ?: 0
        val height = row.props?.modifier?.height ?: 0
        var modifier = Modifier
            .padding(margin.dp)
            .clip(RoundedCornerShape(rounded.dp))
            .background(Color(bgColor?.toColorInt() ?: 0))
        if(width == "100%"){
            modifier = modifier.fillMaxWidth()
        }
        if(height == "100%"){
            modifier = modifier.fillMaxHeight()
        }
        //
        Row(
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            row.children?.forEach {
                BuildComponent(it)
            }
        }
    }

    @Composable
    private fun BuildText(text: ComponentModel) {
        val textColor = text.props?.style?.color
        val fontSize = text.props?.style?.fontSize ?: 0

        Text(text = text.value ?: "", color = Color(textColor?.toColorInt() ?: 0), fontSize = fontSize.sp)
    }

    @Composable
    private fun BuildButton(button: ComponentModel) {
        Button(onClick = {}) {
            Text(text = button.value ?: "")
        }
    }

    @Composable
    private fun BuildImage(image: ComponentModel) {
        val imageSize = image.props?.modifier?.size ?: 0
        Image(
            painter = rememberAsyncImagePainter(image.value),
            contentDescription = null,
            Modifier.size(imageSize.dp)
        )
    }

    @Composable
    private fun BuildSpacer(spacer: ComponentModel) {
        val size = spacer.props?.modifier?.size ?: 0
        Spacer(modifier = Modifier.size(size.dp))
    }

}