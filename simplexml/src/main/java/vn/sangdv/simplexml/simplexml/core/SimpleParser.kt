package vn.sangdv.simplexml.simplexml.core

import android.text.TextUtils
import vn.sangdv.simplexml.simplexml.mapper.XmlMapper
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import vn.sangdv.simplexml.core.util.FieldsHelper
import java.io.InputStream
import java.io.StringReader
import java.lang.reflect.Field


/**
 * Created by Vladimir on 6/15/17.
 */
open class SimpleParser {

    var mappers: HashMap<Class<*>, XmlMapper<*>>? = XmlMapper.getMappers()

    /**
     * Entry point which wrap {@link java.io.InputStream} with {@link org.xmlpull.v1.XmlPullParser}.
     *
     * @param input - InputStream with XML data
     * @param clazz - XML data should be parsed to an instance of this {@link java.lang.Class} object
     * @return instance of param clazz
     */
    fun <T> parse(input: InputStream?, clazz: Class<T>): T? {

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(input, null)

        return parseFor(parser, clazz)
    }

    /**
     * Entry point which wrap {@link java.lang.String} with {@link org.xmlpull.v1.XmlPullParser}.
     *
     * @param input - String with XML data
     * @param clazz - XML data should be parsed to an instance of this {@link java.lang.Class} object
     * @return instance of param clazz
     */
    fun <T> parse(input: String?, clazz: Class<T>): T? {

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setInput(StringReader(input))

        return parseFor(parser, clazz)
    }

    private fun <T> parseFor(parser: XmlPullParser, clazz: Class<T>): T? {
        val targetTagName = FieldsHelper.targetTagName(clazz) ?: throw NullPointerException("Mark target class with annotation XmlName(name)")
        val fields = FieldsHelper.getFieldsMap(clazz)

        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {

                    if (TextUtils.equals(parser.name, targetTagName)) {
                        return parseFor(parser, targetTagName, fields, clazz)
                    }
                }
            }
            eventType = parser.next()
        }

        return null
    }

    private fun <T> parseFor(parser: XmlPullParser, oldTargetName: String, fields: Map<String, CleverField>, clazz: Class<T>): T {
        val instance = clazz.newInstance()

        var m: XmlMapper<*>? = null
        var f: CleverField? = null

        while (!shouldStop(parser.next(), parser, oldTargetName)) {

            val name = parser.name
            when (parser.eventType) {
                XmlPullParser.START_TAG -> {
                    fields[name]?.let { field ->
                        f = field

                        if(field.isPrimitive()) {
                            m = mappers?.get(field.type())
                        } else {
                            setValueToField(field.type(), parser, name, field, instance)
                        }
                    }
                }
                XmlPullParser.TEXT -> {
                    m?.parse(parser)?.let { value ->
                        f?.setValue(instance, value)
                        f = null;
                        m = null;
                    }
                }
            }
        }

        return instance
    }

    private fun <T> setValueToField(type: Class<*>, parser: XmlPullParser, name: String,
                                    field: CleverField, instance: T?) {

        val localFields = FieldsHelper.getFieldsMap(type)
        val localInstance = parseFor(parser, name, localFields, type)

        field.setValue(instance, localInstance)
    }

    private fun shouldStop(eventType: Int, parser: XmlPullParser, oldTargetName: String): Boolean = eventType == XmlPullParser.END_TAG && TextUtils.equals(parser.name, oldTargetName)
}