package com.example.test.simplexml.annotation

@Target(AnnotationTarget.CLASS, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class XmlAsArray (val name: String)