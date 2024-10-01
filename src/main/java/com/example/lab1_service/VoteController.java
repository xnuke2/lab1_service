package com.example.lab1_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/deputes")
@Tag(name="Система голосования.", description="Позволяет добавлять людей участвующих  в голосовании")
@ApiResponses(@ApiResponse(responseCode = "200", useReturnTypeSchema = true))
public class VoteController {
    @Autowired
    VoteService voteService ;


    @Operation(
            summary = "Получения списка всех людей участвующих  в голосовании",
            description = "Позволяет получить список людей участвующих  в голосовании"
    )
    @GetMapping
    public ResponseEntity<List<DeputyEntity>> getListDeputes(){

        List<DeputyEntity> deputy = voteService.getListDeputes();
        return deputy != null &&  !deputy.isEmpty()
                ? new ResponseEntity<>(deputy, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Operation(
            summary = "Получения конкретного человека участвующего  в голосовании",
            description = "Позволяет получить конкретного человека участвующего  в голосовании"
    )
    @GetMapping("/{name}")
    public ResponseEntity<?> getDeputeById(@Parameter(description = "ФИО", example = "Иванов Иван Иванович") @PathVariable String name){

        DeputyEntity deputy = voteService.getDeputeById(name);
        return deputy != null
                ? new ResponseEntity<>(deputy, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Operation(
            summary = "Добавить человека, участвующего в голосовании",
            description = "Позволяет добавить человека в список"
    )
    @PostMapping
    public ResponseEntity<?> addNewDeputy(
            @Parameter(description = "ФИО", example = "Иванов Иван Иванович") @RequestParam String name,
            @Parameter(description = "Возраст", example = "23") @RequestParam int age,
            @Parameter(description = "Политическая партия", example = "ЛДПР") @RequestParam String party,
            @Parameter(description = "Количество голосов", example = "563") @RequestParam int numOfVotes){
        return voteService.addNewDeputy(name, age, party, numOfVotes)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @Operation(
            summary = "Убрать человека из списка",
            description = "Позволяет убрать человека из списока"
    )
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(
            @Parameter(description = "ФИО", example = "Иванов Иван Иванович")@PathVariable String name){
        return voteService.deleteByName(name)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @Operation(
            summary = "Редактировать информацию о человеке из списка",
            description = "Позволяет Редактировать информацию о человеке из списка"
    )
    @PutMapping("/{oldName}")
    public ResponseEntity<?> putDeputy(
            @Parameter(description = "ФИО человека для изменения", example = "Иванов Иван Иванович")
            @PathVariable String oldName,
            @Parameter(description = "новое ФИО", example = "Иванов Иван Иванович") @RequestParam String name,
            @Parameter(description = "Возраст", example = "23") @RequestParam int age,
            @Parameter(description = "Политическая партия", example = "ЛДПР") @RequestParam String party,
            @Parameter(description = "Количество голосов", example = "563") @RequestParam int numOfVotes){
        return voteService.putDeputy(oldName, name, age, party, numOfVotes)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
