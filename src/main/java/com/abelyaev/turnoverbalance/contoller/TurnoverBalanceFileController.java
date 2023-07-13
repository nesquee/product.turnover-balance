package com.abelyaev.turnoverbalance.contoller;

import com.abelyaev.turnoverbalance.model.exception.ParseFileException;
import com.abelyaev.turnoverbalance.service.FileParserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Tag(name = "Файлы.", description = "Контроллер для работы с файлами.")
public class TurnoverBalanceFileController {
    private final FileParserService fileParserService;

    @Operation(
            summary = "Загрузка файла",
            description = "Метод для загрузки файла")
    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile reapExcelDataFile) {
        try {
            return ResponseEntity.ok(fileParserService.parseFile(reapExcelDataFile));
        } catch (IllegalArgumentException | ParseFileException e) {
            return ResponseEntity.ok("Error!");
        }
    }

    @GetMapping(value = "/lol")
    public ResponseEntity<String> getLol() {
        return ResponseEntity.ok("Lol!");
    }
}
