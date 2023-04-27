package com.quyt.sdui_jecpack_compose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            loadJSONFromAsset(this)?.let {
                val componentModel = Gson().fromJson(it, ComponentModel::class.java)
                ComponentUtils.BuildComponent(componentModel)
            }
        }
    }

    private fun loadJSONFromAsset(context: Context): String? {
        var json = ""
        try {
            val inputStream = context.assets.open("response.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}

