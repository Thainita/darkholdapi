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
                                eachText.drop(1)
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

           var papel: String = s.next()
           val segmento: String = s.next()
           val cotacao: String = s.next()
           val ffq: String = s.next()
           val dividendYield: String = s.next()
           val pvp: String = s.next()
           val valorDeMercado: String = s.next()
           val liquidez: String = s.next()
           val quantidadeDeImoveis: String = s.next()
           val precoDoM2: String = s.next()
           val AluguelPorM2: String = s.next()
           val capRate: String = s.next()
           var vacanciaMedia: String = " NADA "

           if (!s.hasNext()){
               continue
           }else{
               vacanciaMedia = s.next()
           }

           investimentsList.add(
               FundoInvestimentoImobiliario(papel, segmento, cotacao, ffq, dividendYield, pvp, valorDeMercado, liquidez, quantidadeDeImoveis, precoDoM2, AluguelPorM2, capRate, vacanciaMedia)
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