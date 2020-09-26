package com.example.test

import com.gitlab.mvysny.konsumexml.Konsumer


private var data = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "   <soap:Body>\n" +
        "      <ns2:makeCustSurveyResponse xmlns:ns2=\"http://localhost/\">\n" +
        "         <return>\n" +
        "            <description>1</description>\n" +
        "            <errorCode/>\n" +
        "            <keyMsg>1</keyMsg>\n" +
        "            <paramsMsg/>\n" +
        "            <success>true</success>\n" +
        "            <ebs>true</ebs>\n" +
        "         </return>\n" +
        "      </ns2:makeCustSurveyResponse>\n" +
        "   </soap:Body>\n" +
        "</soap:Envelope>\n"

data class Parent(val body: Body?) {
    companion object {
        fun xml(k: Konsumer): Parent {
            k.checkCurrent("Envelope")
            return Parent(k.childOpt("Body") { Body.xml(this) })
        }
    }
}

data class Body(val makeCustSurvery: MakeCustSurvery?) {
    companion object {
        fun xml(k: Konsumer): Body {
            k.checkCurrent("Body")
            return Body(k.childOpt("makeCustSurveyResponse") {
                MakeCustSurvery.xml(this)
            })
        }
    }
}

data class MakeCustSurvery(
    val returnData: ReturnResponse?
) {
    companion object {
        fun xml(k: Konsumer): MakeCustSurvery {
            k.checkCurrent("makeCustSurveyResponse")
            return MakeCustSurvery(k.childOpt("return") {
                ReturnResponse.xml(this)
            })
        }
    }
}


data class ReturnResponse(
    val description: String?,
    val errorCode: String?,
    val keyMsg: String?,
    val paramsMsg: String?,
    val success: String?
) {
    companion object {
        fun xml(k: Konsumer): ReturnResponse {
            k.checkCurrent("return")
//            k.child(anyName){
//                when(localName){
//                    "description"-> text()
//                    "errorCode"-> text()
//                    "keyMsg"-> text()
//                    "paramsMsg"-> text()
//                    "success"-> text()
//                    else ->skipContents()
//                }
//            }
            return ReturnResponse(
                k.childTextOpt("description"),
                k.childTextOpt("errorCode"),
                k.childTextOpt("keyMsg"),
                k.childTextOpt("paramsMsg"),
                k.childTextOpt("success")
            )
        }
    }
}

