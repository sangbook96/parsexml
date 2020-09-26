package vn.sangdv.simplexml.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

class ObjectMapper: XmlMapper<Any>() {
    override fun parse(parser: XmlPullParser): Any? {
        return super.parse(parser);
    }
}