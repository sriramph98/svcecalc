package com.cosmos.android.svcecalculator

import com.cosmos.android.svcecalculator.model.Phase

public object DataHelper {
    var phases: ArrayList<Phase> = ArrayList()

    init {

    }

    var internatlMark: Double = 0.0
        get() {
            var sum: Double = 0.0
            for (phase in phases) {
                sum += phase.phaseMark
            }
            sum *= 50 / 3
            return sum
        }


}