package com.example.mixedsale.ui.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
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
}
