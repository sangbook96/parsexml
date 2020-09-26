package vn.sangdv.simplexml.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class ShortMapper: XmlMapper<Short>() {
    override fun parse(parser: XmlPullParser): Short? {
        return parser.text.toShort();
    }
}