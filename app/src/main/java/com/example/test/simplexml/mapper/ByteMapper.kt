package com.example.test.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class ByteMapper: XmlMapper<Byte>() {
    override fun parse(parser: XmlPullParser): Byte? {
        return parser.text.toByte();
    }
}