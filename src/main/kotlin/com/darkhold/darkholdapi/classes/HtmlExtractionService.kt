package com.darkhold.darkholdapi.classes

import it.skrape.core.document
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.*
import it.skrape.selects.html5.*
import java.util.*
import kotlin.collections.ArrayList


data class RequestScrappingClass(
    val httpStatusCode: Int,
    val httpStatusMessage: String,
    val tableContent: List<String>
)

class HtmlExtractionService {

    val investimentsList: ArrayList<FundoInvestimentoImobiliario> = ArrayList()

    fun extract(): ArrayList<FundoInvestimentoImobiliario> {
        val extracted = skrape(HttpFetcher) {
            request {
                url = "https://fundamentus.com.br/fii_resultado.php"
            }

            response {
                RequestScrappingClass(

                    httpStatusCode = status { code },
                    httpStatusMessage = status { message },
                    tableContent = document.table {
                        tr {
                            findAll {
                                eachText.drop(0).subList(1,10)
                            }
                        }
                    }
                )
            }
        }

       for (element in extracted.tableContent) {

           val s: Scanner = Scanner(
               extracted.tableContent.get(extracted.tableContent.indexOf(element))
           ).useDelimiter("\\s\\s|\\t|\\s")

           investimentsList.add(
               FundoInvestimentoImobiliario(
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next(),
                   s.next()
               )
           )
       }
        investimentsList.forEach {
            println( "Registro: " +
                    it.papel + " "
                    + it.segmento + " "
                    + it.cotacao + " "
                    + it.ffq + " "
                    + it.dividendYield + " "
                    + it.pvp + " "
                    + it.valorDeMercado + " "
                    + it.liquidez + " "
                    + it.quantidadeDeImoveis + " "
                    + it.precoDoM2 + " "
                    + it.AluguelPorM2 + " "
                    + it.capRate + " "
                    + it.vacanciaMedia
            )
        }
        return investimentsList
    }

}