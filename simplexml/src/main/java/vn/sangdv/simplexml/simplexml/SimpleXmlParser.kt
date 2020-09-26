package vn.sangdv.simplexml.simplexml

import vn.sangdv.simplexml.simplexml.core.SimpleParser
import java.io.InputStream

class SimpleXmlParser {
    companion object {
        fun <T> parse(input: InputStream?, clazz: Class<T>): T? {
            return SimpleParser().parse<T>(input, clazz)
        }

        fun <T> parse(input: String?, clazz: Class<T>): T? {
            return SimpleParser().parse<T>(input, clazz)
        }
    }
}