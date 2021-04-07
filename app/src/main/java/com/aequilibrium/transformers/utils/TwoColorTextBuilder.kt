package com.aequilibrium.transformers.utils

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.aequilibrium.transformers.transformers.LabeledText

class TwoColorTextBuilder(private val firstColor: Int, private val secondColor: Int) {

    fun getColoredText(labeledText: LabeledText) =
        getColoredText(labeledText.label, labeledText.text)

    fun getColoredText(
        firstColorPhrase: String,
        secondColorPhrase: String
    ): SpannableStringBuilder {
        val phrase = "$firstColorPhrase: $secondColorPhrase"
        val spannableStringBuilder = SpannableStringBuilder(phrase)
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(secondColor),
            0, phrase.length, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableStringBuilder.setSpan(
            ForegroundColorSpan(firstColor),
            0, firstColorPhrase.length + 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        return spannableStringBuilder
    }
}