package com.example.test.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class BooleanMapper: XmlMapper<Boolean>() {
    override fun parse(parser: XmlPullParser): Boolean? {
        return parser.text.toBoolean();
    }
}