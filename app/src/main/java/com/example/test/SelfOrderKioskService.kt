package com.example.test

import com.example.test.simplexml.annotation.XmlName

@XmlName("soap:Envelope")
class SelfOrderKioskService {
    @XmlName("soap:Body")
    var body: Body? = null


    @XmlName("soap:Body")
    class Body {
        @XmlName("ns2:makeCustSurveyResponse")
        var makeCustSurvery: MakeCustSurvery? = null
        override fun toString(): String {
            return "Body(makeCustSurvery=$makeCustSurvery)"
        }

    }

    @XmlName("ns2:makeCustSurveyResponse")
    class MakeCustSurvery {
        @XmlName("return")
        var returnData: ReturnResponseData? = null
        override fun toString(): String {
            return "MakeCustSurvery(returnData=$returnData)"
        }

    }

    @XmlName("return")
    class ReturnResponseData {
        @XmlName("description")
        var description: String = ""

        @XmlName("errorCode")
        var errorCode: String = ""

        @XmlName("keyMsg")
        var keyMsg: String = ""

        @XmlName("paramsMsg")
        var paramsMsg: String = ""

        @XmlName("success")
        var success: String = ""
        override fun toString(): String {
            return "ReturnResponseData(description='$description', errorCode='$errorCode', keyMsg='$keyMsg', paramsMsg='$paramsMsg', success='$success')"
        }


    }

    override fun toString(): String {
        return "ResponseData(body=$body)"
    }
}