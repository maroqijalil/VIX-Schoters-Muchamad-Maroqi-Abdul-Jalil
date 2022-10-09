package com.maroqi.newsapplication.core.utils

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class MapUtil {
    companion object {
        fun <T : Any> toMap(obj: T): Map<String, String> {
            return (obj::class as KClass<T>).memberProperties.associate { prop ->
                prop.name to prop.get(obj)?.let { value ->
                    if (value::class.isData) {
                        toMap(value)
                    } else {
                        value
                    }
                }
            }.filter { it.value != null }.map { it.key to it.value.toString() }.toMap()
        }
    }
}
