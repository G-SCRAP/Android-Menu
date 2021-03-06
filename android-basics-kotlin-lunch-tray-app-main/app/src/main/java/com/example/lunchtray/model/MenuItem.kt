/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray.model

/**
 * Gavin Ogren
 * 4/6/2022
 * Lunch Tray Application: Using Kotlin and Android Studio I created a application that takes
 * menu items and displays the price.
 */
import java.text.NumberFormat
//Menu Items
data class MenuItem(
    val name: String,
    val description: String,
    var price: Double,
    val type: Int
) {
    //Function Gets the formatted Price
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}
