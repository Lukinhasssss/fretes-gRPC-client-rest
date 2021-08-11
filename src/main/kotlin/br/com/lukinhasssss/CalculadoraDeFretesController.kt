package br.com.lukinhasssss

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.inject.Inject

@Controller("/api/fretes")
class CalculadoraDeFretesController(@Inject val grpcClient: FretesServiceGrpc.FretesServiceBlockingStub) {

    @Get
    fun calcula(@QueryValue cep: String): FreteResponse {
        val request = CalculaFreteRequest.newBuilder()
            .setCep(cep)
            .build()

        val response = grpcClient.calculaFrete(request)

        return FreteResponse(cep = response.cep, valor = response.valor)
    }

}

data class FreteResponse(val cep: String, val valor: Double) {}