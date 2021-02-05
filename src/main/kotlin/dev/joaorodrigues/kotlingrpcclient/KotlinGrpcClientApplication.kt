package dev.joaorodrigues.kotlingrpcclient

import dev.joaorodrigues.kotlingrpcclient.client.HelloWorldClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinGrpcClientApplication

suspend fun main(args: Array<String>) {
    val applicationContext = runApplication<KotlinGrpcClientApplication>(*args)
    applicationContext.getBean(HelloWorldClient::class.java).greet("client gRPC! FUNCIONOU PORRA")
}
