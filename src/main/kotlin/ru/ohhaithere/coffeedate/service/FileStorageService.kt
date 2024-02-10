package ru.ohhaithere.coffeedate.service

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.util.IOUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*


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

    fun download(filename: String): ByteArray {
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
        try {
            val content: ByteArray
            val s3Object: S3Object = s3.getObject("coffeedate-test", filename)
            val stream = s3Object.objectContent
            content = IOUtils.toByteArray(stream)
            s3Object.close()
            return content
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        } catch (serviceException: AmazonServiceException) {
            serviceException.printStackTrace()
        } catch (clientException: AmazonClientException) {
            clientException.printStackTrace()
        }
        return ByteArray(0)
    }


}