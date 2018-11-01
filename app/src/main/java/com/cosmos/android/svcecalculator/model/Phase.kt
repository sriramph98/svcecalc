package com.cosmos.android.svcecalculator.model

import com.cosmos.android.svcecalculator.view.NumberEditText

data class Phase(val catMark: Double, val assMark: Double) {

    var phaseMark: Double = 0.0
        get() {
            return catMark / 50 * 0.7 + assMark / 50 * 0.3
        }
}