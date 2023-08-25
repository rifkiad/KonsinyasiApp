package com.example.konsinyasiapp

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

class Converter(private val textView: TextView?, private val editText: EditText?) {

    private val numberFormat: NumberFormat =
        DecimalFormat("Rp#,###", DecimalFormatSymbols(Locale("id", "ID")))

    init {
        textView?.let {
            setUpTextView(it)
        }

        editText?.let {
            setUpEditText(it)
        }
    }

    private fun setUpTextView(textView: TextView) {
        updateText("0")

        textView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val stringText = s.toString()

                textView.removeTextChangedListener(this)

                val cleanString = stringText.replace("[Rp,.\\s]".toRegex(), "")

                if (cleanString.isNotEmpty()) {
                    val convertToLong = cleanString.toLong()
                    val formatted = numberFormat.format(convertToLong)

                    updateText(formatted)
                }

                textView.addTextChangedListener(this)
            }
        })
    }

    private fun setUpEditText(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val stringText = s.toString()

                editText.removeTextChangedListener(this)

                val cleanString = stringText.replace("[Rp,.\\s]".toRegex(), "")

                if (cleanString.isNotEmpty()) {
                    val convertToLong = cleanString.toLong()
                    val formatted = numberFormat.format(convertToLong)

                    editText.setText(formatted)
                    editText.setSelection(formatted.length)
                }

                editText.addTextChangedListener(this)
            }
        })
    }

    fun updateText(amount: String) {
        textView?.text = amount
    }

    fun formatRupiah(amount: String): String {
        val cleanString = amount.replace("[Rp,.\\s]".toRegex(), "")

        return if (cleanString.isNotEmpty()) {
            val convertToLong = cleanString.toLong()
            numberFormat.format(convertToLong)
        } else {
            "0"
        }
    }
}
