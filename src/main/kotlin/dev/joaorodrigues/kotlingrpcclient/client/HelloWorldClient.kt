package dev.joaorodrigues.kotlingrpcclient.client

import io.grpc.ManagedChannelBuilder
import java.io.Closeable
import java.util.concurrent.TimeUnit
import main.proto.GreeterGrpcKt
import main.proto.HelloRequest
import org.springframework.stereotype.Component

@Component
class HelloWorldClient : Closeable {
    private final val port = System.getenv("PORT")?.toInt() ?: 50051
    private final val channel = ManagedChannelBuilder.forAddress("localhost", port).usePlaintext().build()

    private val stub: GreeterGrpcKt.GreeterCoroutineStub = GreeterGrpcKt.GreeterCoroutineStub(channel)

    suspend fun greet(name: String) {
        val request = HelloRequest.newBuilder().setName(name).build()
        val response = stub.sayHello(request)
        println("Received: ${response.message}")
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}