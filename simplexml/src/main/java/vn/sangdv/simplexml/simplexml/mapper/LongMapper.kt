package vn.sangdv.simplexml.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class LongMapper: XmlMapper<Long>() {
    override fun parse(parser: XmlPullParser): Long? {
        return parser.text.toLong();
    }
}