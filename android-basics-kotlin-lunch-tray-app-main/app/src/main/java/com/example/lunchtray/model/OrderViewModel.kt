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

    // Map of menu items
    val menuItems = DataSource.menuItems

    // Default values for item prices
    private var previousEntreePrice = 0.0
    private var previousSidePrice = 0.0
    private var previousAccompanimentPrice = 0.0

    // Default tax rate
    private val taxRate = 0.08

    // Entree for the order
    private val _entree = MutableLiveData<MenuItem?>()
    val entree: LiveData<MenuItem?> = _entree


    // Side for the order
    private val _side = MutableLiveData<MenuItem?>()
    val side: LiveData<MenuItem?> = _side

    // Accompaniment for the order.
    private val _accompaniment = MutableLiveData<MenuItem?>()
    val accompaniment: LiveData<MenuItem?> = _accompaniment

    // Subtotal for the order
    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<String> = Transformations.map(_subtotal) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Total cost of the order
    private val _total = MutableLiveData(0.0)
    val total: LiveData<String> = Transformations.map(_total) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Tax for the order
    private val _tax = MutableLiveData(0.0)
    val tax: LiveData<String> = Transformations.map(_tax) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    /**
     * Set the entree for the order.
     */
    fun setEntree(entree: String) {

        // The functions saves the selected entree information including price and name values
        // and stores it inside a local variable.


        // if _entree.value is not null, set the previous entree price to the current entree price.
        if (_entree.value != null) {
            previousEntreePrice = _entree.value?.price ?: 0.0
        }

        // if _subtotal.value is not null subtract the previous entree price from the current
        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value ?: 0.0) - previousEntreePrice
        }

        // set the current entree value to the menu item corresponding to the passed in string
        _entree.value = menuItems[entree]

        // update the subtotal to reflect the price of the selected entree.
        updateSubtotal(menuItems[entree]?.price ?: 0.0)
    }

    /**
     * Set the side for the order.
     */
    fun setSide(side: String) {

        // The functions saves the selected side information including price and name values
        // and stores it inside a local variable.


        // if _side.value is not null, set the previous side price to the current side price.
        previousEntreePrice = _side.value?.price ?: 0.0
        //  if _subtotal.value is not null subtract the previous side price from the current
        //  subtotal value. This ensures that we only charge for the currently selected side.
        _subtotal.value = (_subtotal.value ?: 0.0) - previousSidePrice
        // set the current side value to the menu item corresponding to the passed in string
        _side.value = menuItems[side]
        // updates the subtotal to reflect the price of the selected side.
        updateSubtotal(menuItems[side]?.price ?: 0.0)
    }

    /**
     * Set the accompaniment for the order.
     */
    fun setAccompaniment(accompaniment: String) {

        // The functions saves the selected Accompaniment information including price and name values
        // and stores it inside a local variable.

        //  if _accompaniment.value is not null, set the previous accompaniment price to the
        //  current accompaniment price.
        previousAccompanimentPrice = _side.value?.price ?: 0.0
        //  if _accompaniment.value is not null subtract the previous accompaniment price from
        //  the current subtotal value. This ensures that we only charge for the currently selected
        //  accompaniment.
        _subtotal.value = (_subtotal.value ?: 0.0) - previousAccompanimentPrice

        // set the current accompaniment value to the menu item corresponding to the passed in
        //  string
        _accompaniment.value = menuItems[accompaniment]
        // update the subtotal to reflect the price of the selected accompaniment.
        updateSubtotal(menuItems[accompaniment]?.price ?: 0.0)
    }

    /**
     * Update subtotal value.
     */
    private fun updateSubtotal(itemPrice: Double) {
        //Updates the subtotal of chosen radiobutton and saves it.
        // Calls the Calculate Tax Function

        if (_subtotal.value != null) {
            _subtotal.value = (_subtotal.value)?.plus(itemPrice)
        } else {
            _subtotal.value = itemPrice
        }


        calculateTaxAndTotal()
    }

    /**
     * Calculate tax and update total.
     */
    fun calculateTaxAndTotal() {
        //Tax rate was defined above and based on that rate
        // The code takes the subtotal and multiplies it together
        // and adds that result to subtotal and gets the total value.
        _tax.value = _subtotal.value?.times(taxRate)
        _total.value = (_subtotal.value)?.plus(_tax.value ?: 0.0)
    }

    /**
     * Reset all values pertaining to the order.
     */
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
