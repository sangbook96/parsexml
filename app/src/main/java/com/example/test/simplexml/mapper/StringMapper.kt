package com.example.test.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

class StringMapper: XmlMapper<String>() {
    override fun parse(parser: XmlPullParser): String {
        return parser.text;
    }
}