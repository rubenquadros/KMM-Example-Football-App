package com.ruben.footiescore.android.ui.utility

/**
 * Created by Ruben Quadros on 27/11/21
 **/
fun String?.shouldPerformSearch(): Boolean {
    return this.isNullOrBlank().not() && this!!.length >= 3
}