package com.example.lab1_service;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
@Schema(description = "Человек за которого голосуют")
public class DeputyEntity {
    @NotBlank
    @Schema(description = "ФИО")
    String name;

    @NotBlank
    @Schema(description = "возраст ")
    int age;

    @NotBlank
    @Schema(description = "Партия к которой принадлежит")
    String party;

    @NotBlank
    @Schema(description = "Колличество голосов")
    int numberOfVotes;
}
