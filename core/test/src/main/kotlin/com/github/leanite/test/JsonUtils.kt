package com.github.leanite.test

import android.content.Context
import androidx.annotation.RawRes
import androidx.test.core.app.ApplicationProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun getJson(
    resourceId: Int,
    context: Context = ApplicationProvider.getApplicationContext()) =
    context.resources.openRawResource(resourceId).bufferedReader().use { it.readText() }

inline fun <reified T> Context.jsonToClass(@RawRes resourceId: Int): T =
    Gson().fromJson(
        resources.openRawResource(resourceId).bufferedReader().use { it.readText() },
        T::class.java
    )

inline fun <reified  T> Context.jsonListToClass(@RawRes resourceId: Int): T {
    val type = object : TypeToken<T>() {}.type

    return Gson().fromJson(
        resources.openRawResource(resourceId).bufferedReader().use { it.readText() },
        type
    )
}