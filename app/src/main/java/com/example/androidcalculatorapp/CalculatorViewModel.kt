package com.example.androidcalculatorapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel() {
    private val _result = MutableLiveData("1234")
    val result: LiveData<String> = _result

    private val _query = MutableLiveData("1234")
    val query: LiveData<String> = _query

    fun ButtonOnClick(btn: String) {
        _query.value?.let {
            if (btn == "AC") {
                _result.value = "0";
                _query.value = "";
                return
            }
            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _query.value = it.substring(0, it.length - 1)
                }
                return
            }
            if (btn == "=") {
                _query.value = _result.value
                return
            }
            _query.value = it + btn

            //Calculate Result
            try {
                _result.value = calculateResult(_query.value.toString())
            } catch (_: Exception) {
            }

        }

        Log.i("TAG", "ButtonOnClick: ${_query.value}")
    }

    fun calculateResult(equation: String): String {
        val context: Context = Context.enter()
        context.optimizationLevel = -1
        val scriptable: Scriptable = context.initStandardObjects()
        var finalResult =
            context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()
        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "")
        }
        return finalResult
    }
}