package ru.ohhaithere.coffeedate.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.ohhaithere.coffeedate.service.FileStorageService

@RestController
@CrossOrigin
@RequestMapping("/download")
class DownloadController(val fileService: FileStorageService) {

    @GetMapping(value = arrayOf("/{filename}"), produces = arrayOf(MediaType.IMAGE_JPEG_VALUE))
    @ResponseBody
    fun get(@PathVariable filename: String): ByteArray {
        return fileService.download(filename)
    }

}