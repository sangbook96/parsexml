package vn.sangdv.simplexml.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class FloatMapper: XmlMapper<Float>() {
    override fun parse(parser: XmlPullParser): Float? {
        return parser.text.toFloat();
    }
}