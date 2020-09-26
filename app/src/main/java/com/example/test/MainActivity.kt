package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.simplexml.SimpleXmlParser
import vn.sangdv.simplexml.connection.QueryElement
import vn.sangdv.simplexml.connection.RequestAsynTask
import vn.sangdv.simplexml.model.Optional

class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private var requestAsynTask: RequestAsynTask? = null
    var mListOptional = ArrayList<Optional>()
    private val URL = "https://localHost"
    private var data = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "   <soap:Body>\n" +
            "      <ns2:makeCustSurveyResponse xmlns:ns2=\"http://localHost/\">\n" +
            "         <return>\n" +
            "            <description>1</description>\n" +
            "            <errorCode/>\n" +
            "            <keyMsg>1</keyMsg>\n" +
            "            <paramsMsg/>\n" +
            "            <success>true</success>\n" +
            "            <ss>12</ss>\n" +
            "         </return>\n" +
            "      </ns2:makeCustSurveyResponse>\n" +
            "   </soap:Body>\n" +
            "</soap:Envelope>\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val makeCustSurveryResponse = data.konsumeXml().use { k ->
//            k.child("Envelope") {
//                Parent.xml(this)
//            }
//        }
//        Log.e(TAG, makeCustSurveryResponse.body?.makeCustSurvery?.returnData.toString())

        var entry = SimpleXmlParser.parse(data, SelfOrderKioskService::class.java!!)
        Log.e(TAG,"==>"+entry.toString())
        Log.e(TAG,"==>"+entry?.body?.makeCustSurvery?.returnData?.toString())
        mListOptional.add(Optional("lat", "12"))
        mListOptional.add(Optional("long", "10"))
        mListOptional.add(
            Optional(
                "shopCode",
                "212"
            )
        )
        mListOptional.add(Optional("imageCheckIn", ""))
        var a = QueryElement().addElement(mListOptional)
        requestAsynTask = RequestAsynTask(URL,
            "xml",
            object : RequestAsynTask.Callback{
                override fun onSuccessResponse(response: String?) {
                    Log.e(TAG,"onSuccess: ==>"+response.toString())
                }

                override fun onError(message: String?) {
                    Log.e(TAG,"onError: ==>"+message.toString())
                }

            }
        )
        requestAsynTask?.AsyncTaskRequest()?.execute()
    }

}