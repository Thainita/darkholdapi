package com.darkhold.darkholdapi.classes

import it.skrape.core.document
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.*
import it.skrape.selects.html5.*
import java.util.*
import kotlin.collections.ArrayList


data class MySimpleDataClass(
    //val httpStatusCode: Int,
    //val httpStatusMessage: String,
    val tableContent: List<String>,
    //val tableFirstContent: String,
)

class HtmlExtractionService {

    fun extract(): String {
        val extracted = skrape(HttpFetcher) {
            request {
                url = "https://fundamentus.com.br/fii_resultado.php"
            }

            response {
                MySimpleDataClass(

                    //httpStatusCode = status { code },
                    //httpStatusMessage = status { message },

                    //tableContent = document.tr { td { findAll { eachText.drop(1).subList(0,2) } } }
                    //tableContent = document.table { tr { td  { findAll { eachText.subList(0,4)} }} }
                    tableContent = document.table { tr { findAll { eachText.drop(0).subList(1,4) } } }
                    //tableFirstContent = document.tr { findFirst { text } }
                )
            }
        }
        //println(extracted.httpStatusCode)
       // println(extracted.httpStatusMessage)
       val investimentsList: ArrayList<FundoInvestimentoImobiliario> = ArrayList()

       for (element in extracted.tableContent) {

           val s: Scanner = Scanner(
               extracted.tableContent.get(extracted.tableContent.indexOf(element))
           ).useDelimiter("\\s")

           investimentsList.add(
               FundoInvestimentoImobiliario(
                   s.next(),
                   s.next()
               )
           )

       //extracted.tableContent.map { FundosInvestimentoImobiliario(extracted.tableContent.get(1))}
       }

//        extracted.tableContent.forEach {
//            investimentoImobiliario.papel = it
//        }

//        listOf(extracted.tableContent).forEach {
//            if (it.size == 13) return@forEach // local return to the caller of the lambda - the forEach loop
//            print(it)
//        }



        investimentsList.forEach { println( "Investimento: " + it.papel + " " + it.segmento) }
        return extracted.tableContent.toString()
    }

}