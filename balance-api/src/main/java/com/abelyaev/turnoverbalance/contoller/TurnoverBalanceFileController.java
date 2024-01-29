package com.abelyaev.turnoverbalance.contoller;

import com.abelyaev.turnoverbalance.model.exception.ParseFileException;
import com.abelyaev.turnoverbalance.service.FileParserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Tag(name = "Файлы.", description = "Контроллер для работы с файлами.")
public class TurnoverBalanceFileController {
    private final FileParserService fileParserService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Flux<String> uploadFile(@RequestPart("file") Flux<Part> file) {
        return fileParserService.parseFile(file);
    }
}
