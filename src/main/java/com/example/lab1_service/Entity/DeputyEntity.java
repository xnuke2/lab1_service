package com.example.lab1_service.Entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Document
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@RequiredArgsConstructor
@Schema(description = "Человек за которого голосуют")
public class DeputyEntity implements Serializable {
    @NonNull
    @Id
    @Schema(description = "ФИО")
    String name;

    @NonNull
    @Schema(description = "возраст ")
    int age;

    @NonNull
    @Schema(description = "Партия к которой принадлежит")
    String party;

    @NonNull
    @Schema(description = "Колличество голосов")
    int numberOfVotes;

    @NonNull
    String userID;

    String confirm=null;
}
