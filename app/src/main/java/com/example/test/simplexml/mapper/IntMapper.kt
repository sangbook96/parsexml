package com.example.test.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class IntMapper: XmlMapper<Int>() {
    override fun parse(parser: XmlPullParser): Int? {
        return parser.text.toInt();
    }
}