package ru.ohhaithere.coffeedate.client

import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.util.stream.Collectors

@Service
class RandomOrgClient {

    fun getRandomNumber(): String {
        val url = URL(
            "http://www.random.org/strings/?num=1&len=4&digits=on&unique=on&format=plain&rnd=new"
        )
        val connection: URLConnection = url.openConnection()
        connection.setConnectTimeout(5000)
        val reader = BufferedReader(
            InputStreamReader(
                connection.getInputStream()
            )
        )

        val line = reader.lines().collect(Collectors.joining("\n"));

        reader.close()
        return line;
    }

}