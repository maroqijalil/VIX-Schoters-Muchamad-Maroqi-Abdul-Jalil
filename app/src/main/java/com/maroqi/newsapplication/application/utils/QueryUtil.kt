package com.maroqi.newsapplication.application.utils

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

object QueryUtil {
    fun <T : Any> toMap(obj: T): Map<String, String> {
        return (obj::class as KClass<T>).memberProperties.mapNotNull { prop ->
            prop.takeIf { it.get(obj) != null }?.let {
                it to prop.get(obj)?.let { value ->
                    if (value::class.isData) {
                        toMap("$value")
                    } else {
                        "$value"
                    }
                }
            }
        }.toMap() as Map<String, String>
    }

    fun <T : Any> toMapWithOnlyPrimaryConstructorProperties(obj: T): Map<String, String> {
        val kClass = obj::class as KClass<T>
        val primaryConstructorPropertyNames = kClass.primaryConstructor?.parameters?.map { it.name } ?: run {
            return toMap(obj)
        }
        return kClass.memberProperties.mapNotNull { prop ->
            prop.takeIf { (it.name in primaryConstructorPropertyNames) && (it.get(obj) != null) }?.let {
                it to prop.get(obj)?.let { value ->
                    if (value::class.isData) {
                        toMap("$value")
                    } else {
                        "$value"
                    }
                }
            }
        }.toMap() as Map<String, String>
    }
}
