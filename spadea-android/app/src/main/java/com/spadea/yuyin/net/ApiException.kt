package com.spadea.yuyin.net

/**
 * Copyright (c) 1
 *
 * @Description
 * @Author (沐枫/hanlin_bj@163.com)
 * @Copyright Copyright (c) 1
 * @Date 2018
 * @CreateBy android_studio
 * @Remarks
 */
class ApiException(code: Int,msg: String): Exception(msg){
    var code: Int? = 0
    init {
        this.code = code
    }
}