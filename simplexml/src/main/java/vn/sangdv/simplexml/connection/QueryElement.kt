package vn.sangdv.simplexml.connection

import vn.sangdv.simplexml.model.Optional

class QueryElement {
    fun addElement(listOptional:List<Optional>):String{
        val envelope = StringBuilder()
        for (i in 0 until listOptional.size){
            envelope.append("<${listOptional.get(i).key}>"+"${listOptional.get(i).value}"+"</${listOptional.get(i).key}>")
        }
        return envelope.toString()
    }

}