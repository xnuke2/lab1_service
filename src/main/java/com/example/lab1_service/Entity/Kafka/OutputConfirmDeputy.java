package com.example.lab1_service.Entity.Kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputConfirmDeputy {
    String nameOfDeputy;
    String login;
}
