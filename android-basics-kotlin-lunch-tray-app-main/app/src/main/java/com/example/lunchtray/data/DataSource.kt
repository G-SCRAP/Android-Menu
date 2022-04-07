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

/**
 * Gavin Ogren
 * 4/6/2022
 * Lunch Tray Application: Using Kotlin and Android Studio I created a application that takes
 * menu items and displays the price.
 */

package com.example.lunchtray.data

import com.example.lunchtray.constants.ItemType
import com.example.lunchtray.model.MenuItem

// So I didn't change any variables that were names and callings but I changed the attributes of it.
object DataSource {
    val menuItems = mapOf(
        "cauliflower" to
                MenuItem(
                    name = "New York-Style Pizza",
                    description = "Our World Famous Pizza ready to be enjoyed! Includes Pepperoni and Cheese and delicious homemade bread.",
                    price = 16.00,
                    type = ItemType.ENTREE
                ),
        "chili" to
                MenuItem(
                    name = "Meat Lovers Pizza",
                    description = "Cooked and crumbled with your choice Italian sausage or ground beef, bacon, pepperoni and sliced ham.",
                    price = 14.00,
                    type = ItemType.ENTREE
                ),
        "pasta" to
                MenuItem(
                    name = "California Pizza",
                    description = "Thin-crust pizza noted for its fresh, nontraditional toppings, such as chicken, peanut sauce, artichoke hearts, and goat cheese.",
                    price = 15.50,
                    type = ItemType.ENTREE
                ),
        "skillet" to
                MenuItem(
                    name = "Philly Cheese Steak Pizza",
                    description = "Instead of a tomato sauce we use three types of cheese: mozzarella, provolone and American. Toppings include sautéed onions, mushrooms and bell peppers. Of course, you can't forget tender slices of steak sliced thin!",
                    price = 15.50,
                    type = ItemType.ENTREE
                ),
        "salad" to
                MenuItem(
                    name = "Summer Salad",
                    description = "Heirloom tomatoes, butter lettuce, peaches, avocado, balsamic dressing",
                    price = 5.00,
                    type = ItemType.SIDE_DISH
                ),
        "soup" to
                MenuItem(
                    name = "2 liter Soda of the day",
                    description = "Homemade Mountain Dew",
                    price = 3.00,
                    type = ItemType.SIDE_DISH
                ),
        "potatoes" to
                MenuItem(
                    name = "Spicy Potatoes",
                    description = "Marble potatoes, roasted, and fried in house spice blend",
                    price = 6.00,
                    type = ItemType.SIDE_DISH
                ),
        "rice" to
                MenuItem(
                    name = "Breadsticks",
                    description = "The stuff is addictive, which explains why so many people are asking for the recipe. Note: Contains Milk",
                    price = 3.50,
                    type = ItemType.SIDE_DISH
                ),
        "bread" to
                MenuItem(
                    name = "Lunch Roll",
                    description = "Fresh baked roll made in house",
                    price = 0.50,
                    type = ItemType.ACCOMPANIMENT
                ),
        "berries" to
                MenuItem(
                    name = "Mixed Berries",
                    description = "Strawberries, blueberries, raspberries, and huckleberries",
                    price = 1.00,
                    type = ItemType.ACCOMPANIMENT
                ),
        "pickles" to
                MenuItem(
                    name = "Homemade Ranch",
                    description = "Creamy and non watery homemade ranch",
                    price = 0.50,
                    type = ItemType.ACCOMPANIMENT
                )

    )
}
