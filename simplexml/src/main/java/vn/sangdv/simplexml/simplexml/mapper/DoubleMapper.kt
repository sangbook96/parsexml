package vn.sangdv.simplexml.simplexml.mapper

import org.xmlpull.v1.XmlPullParser

open class DoubleMapper: XmlMapper<Double>() {
    override fun parse(parser: XmlPullParser): Double? {
        return parser.text.toDouble();
    }
}