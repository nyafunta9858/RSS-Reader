package com.github.nyafunta.rssreader.infra.convert

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type


internal class RssConverterFactory
/**
 * Constructor
 */
private constructor() : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? = RssResponseBodyConverter()

    companion object {

        /**
         * Creates an instance
         *
         * @return instance
         */
        fun create(): RssConverterFactory {
            return RssConverterFactory()
        }
    }
}