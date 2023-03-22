package com.lftorresdev.desafiosbackend.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortLinkDto {

  private String newLink;
  private LocalDateTime expiration;
}
