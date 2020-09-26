package com.example.test

import com.gitlab.mvysny.konsumexml.Konsumer

//class ReturnResponse {
//    var description: String = ""
//    var errorCode: String = ""
//    var keyMsg: String = ""
//    var paramsMsg: String = ""
//    var success: String = ""
//
//    companion object {
//        fun xml(k: Konsumer): ReturnResponse {
//            k.checkCurrent("return")
//            val returnResponse = ReturnResponse()
//            if (!k.childText("description").isNullOrEmpty()) {
//                returnResponse.description = k.childText("description")
//            }
//            return returnResponse
//        }
//    }
//}