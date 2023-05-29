package ru.ohhaithere.coffeedate.service

import com.amazonaws.services.s3.AmazonS3ClientBuilder

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.auth.BasicAWSCredentials
import org.springframework.web.multipart.MultipartFile
import java.util.*
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.stereotype.Service


@Service
class FileStorageService {

    fun upload(files: List<MultipartFile>?): String {
        val awsCreds = BasicAWSCredentials("YCAJEKDr0ZNj2RW8a70qR6WZM", "YCOn-TwLccjKShkMjhxtzC4pL9MC_B6k9QwEs2Gg")
        var idList = emptyList<String>()
        var s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .withEndpointConfiguration(
                EndpointConfiguration(
                    "storage.yandexcloud.net", "ru-central1"
                )
            )
            .build()
        files?.forEach {
            val data = ObjectMetadata()
            data.contentType = it.getContentType()
            data.contentLength = it.getSize()
            var id = UUID.randomUUID().toString()
            s3.putObject("coffeedate-test", id, it.inputStream, data)
            idList = idList + id;
        }
        return idList.joinToString(",")
    }

}