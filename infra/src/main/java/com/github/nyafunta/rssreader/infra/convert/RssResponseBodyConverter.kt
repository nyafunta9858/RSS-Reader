package com.github.nyafunta.rssreader.infra.convert

import com.github.nyafunta.rssreader.domain.model.RssFeed
import okhttp3.ResponseBody
import org.xml.sax.InputSource
import retrofit2.Converter
import javax.xml.parsers.SAXParserFactory


internal class RssResponseBodyConverter : Converter<ResponseBody, RssFeed> {

    override fun convert(value: ResponseBody): RssFeed {
        val items = kotlin.runCatching {
            val parser = XMLParser()
            val parserFactory = SAXParserFactory.newInstance()
            val saxParser = parserFactory.newSAXParser()
            val xmlReader = saxParser.xmlReader
            xmlReader.contentHandler = parser
            val inputSource = InputSource(value.charStream())
            xmlReader.parse(inputSource)
            parser.rssItems
        }.getOrNull().orEmpty()

        return RssFeed(items)
    }

}