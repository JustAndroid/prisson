package com.goodsoft.prisson.utils

/**
 * Created by Nikolay on 5/8/21.
 */
sealed class DataState {
    class Loading(): DataState()
    class Data(val data: Any?): DataState()
}
