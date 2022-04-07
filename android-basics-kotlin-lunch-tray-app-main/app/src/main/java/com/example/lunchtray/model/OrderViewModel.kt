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
 * Lunch Tray Appplication: Using Kotlin and Android Studio I created a application that takes
 * menu items and displays the price.
 */
package com.example.lunchtray.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.DataSource
import java.text.NumberFormat


class OrderViewModel : ViewModel() {


    // A bunch of variables declared:

    // menu items
    val menuItems = DataSource.menuItems

    // Default values for item prices
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    // The tax rate
    private val taxRate = 0.08

    // Entree
    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree


    // Side
    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    // Accompaniment
    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    // Subtotal
    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Total cost
    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Tax
    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    fun setEntree(entree: String) {

        // The functions saves the selected entree information including price and name values
        // and stores it inside a local variable.


        // Makes sure user cannot spam multiple items in the menu
        if (_entree.value != null) {
            previousEntreePrice = _entree.value?.price ?: 0.0
        }

        // Makes sure user cannot spam multiple items in the menu
        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value ?: 0.0) - previousEntreePrice
        }

        // set the current entree value to the menu item corresponding to the passed in string
        _entree.value = menuItems[entree]

        // update the subtotal to reflect the price of the selected entree.
        updateSubtotal(menuItems[entree]?.price ?: 0.0)
    }


    fun setSide(side: String) {

        // The functions saves the selected side information including price and name values
        // and stores it inside a local variable.


        // Makes sure user cannot spam multiple items in the menu
        if (_side.value != null) {
            previousSidePrice = _side.value?.price ?: 0.0

        }

        // Makes sure user cannot spam multiple items in the menu
        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value ?: 0.0) - previousSidePrice
        }

        // set the current side value to the menu item corresponding to the passed in string
        _side.value = menuItems[side]
        // updates the subtotal to reflect the price of the selected side.
        updateSubtotal(menuItems[side]?.price ?: 0.0)
    }

    fun setAccompaniment(accompaniment: String) {

        // The functions saves the selected Accompaniment information including price and name values
        // and stores it inside a local variable.

        // Makes sure user cannot spam multiple items in the menu
        if (_accompaniment.value != null) {
            previousAccompanimentPrice = _accompaniment.value?.price ?: 0.0
        }

        // Makes sure user cannot spam multiple items in the menu
        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value ?: 0.0) - previousAccompanimentPrice

        }


        // set the current accompaniment value to the menu item corresponding to the passed in
        //  string
        _accompaniment.value = menuItems[accompaniment]
        // update the subtotal to reflect the price of the selected accompaniment.
        updateSubtotal(menuItems[accompaniment]?.price ?: 0.0)
    }

    private fun updateSubtotal(itemPrice: Double) {
        //Updates the subtotal of chosen radiobutton and saves the amount.
        // Calls the Calculate Tax Function

        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value)?.plus(itemPrice)
        } else {
            _subtotal.value = itemPrice
        }
        calculateTaxAndTotal()
    }

    fun calculateTaxAndTotal() {
        //Tax rate was defined above and based on that rate
        // The code takes the subtotal and multiplies it together
        // and adds that result to subtotal and gets the total value.
        _tax.value = _subtotal.value?.times(taxRate)
        _total.value = (_subtotal.value)?.plus(_tax.value ?: 0.0)
    }

    fun resetOrder() {
        // Reset all values associated with an order
        // This is only called at the end of a order or if
        // the cancel button is pressed.

        previousEntreePrice = 0.0
        previousSidePrice = 0.0
        previousAccompanimentPrice = 0.0

        _entree.value = null
        _side.value = null
        _accompaniment.value = null

        _tax.value = 0.0
        _total.value = 0.0
        _subtotal.value = 0.0
    }
}
