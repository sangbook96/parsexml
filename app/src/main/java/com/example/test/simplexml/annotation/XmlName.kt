package com.example.test.simplexml.annotation

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class XmlName(val name: String = "", vararg val names: String = arrayOf<String>())