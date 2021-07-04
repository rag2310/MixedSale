package com.example.mixedsale.ui.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.text.isDigitsOnly
import androidx.databinding.*
import com.google.android.material.chip.ChipGroup
import java.text.SimpleDateFormat
import java.util.*

object Adapters {

    //CONVERT STRING TO DOUBLE DATA-BINDING TWO-WAY - BEGIN
    @BindingAdapter("price")
    @JvmStatic
    fun setPrice(view: EditText, newValue: Double) {
        view.setText(newValue.toString())
    }

    @InverseBindingAdapter(attribute = "price")
    @JvmStatic
    fun getPrice(view: EditText): Double {
        if (view.text.equals(".")) return 0.0
        return view.text.toString().toDouble()
    }

    @BindingAdapter("priceAttrChanged")
    @JvmStatic
    fun setListeners(
        view: EditText,
        attrChange: InverseBindingListener
    ) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                println("afterTextChanged ${s.toString()}")
                if (s.toString() != ".")
                    if (s.toString().trim().isNotEmpty())
                        attrChange.onChange()
            }
        })
    }
    //CONVERT STRING TO DOUBLE DATA-BINDING TWO-WAY - END

    //CONVERT DATE TO STRING DATA-BINDING ONE-WAY - BEGIN
    @SuppressLint("SimpleDateFormat")
    @BindingAdapter("date")
    @JvmStatic
    fun date(view: TextView, data: Date) {
        val formatter = SimpleDateFormat("kk:mm dd MMMM yyyy")
        view.text = formatter.format(data)
    }
    //CONVERT DATE TO STRING DATA-BINDING ONE-WAY - END

    //CONVERT DOUBLE TO STRING DATA-BINDING ONE-WAY - BEGIN
    @BindingAdapter("convertDoubleToString")
    @JvmStatic
    fun convertDoubleToString(view: TextView, data: Double) {
        view.text = "C$$data"
    }
    //CONVERT DOUBLE TO STRING DATA-BINDING ONE-WAY - END

    @BindingAdapter("filterChips")
    @JvmStatic
    fun ChipGroup.setFilterChips(id: Int) {
        this.check(id)
    }

    @InverseBindingAdapter(attribute = "filterChips")
    @JvmStatic
    fun ChipGroup.getFilterChips(): Int {
        return this.checkedChipId
    }

    @BindingAdapter("filterChipsAttrChanged")
    @JvmStatic
    fun ChipGroup.setListeners(
        attrChange: InverseBindingListener
    ) {
        this.setOnCheckedChangeListener { _, _ -> attrChange.onChange() }
    }
}