package com.abelyaev.balance.contoller;

import com.abelyaev.balance.service.FileParserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
