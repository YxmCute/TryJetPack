package com.lij.tryjetpack

data class BaseResponse<T>
    (val data: T, val errorCode: Int, val errorMsg: String)