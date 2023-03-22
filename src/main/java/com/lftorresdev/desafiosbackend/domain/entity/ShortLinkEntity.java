package com.lftorresdev.desafiosbackend.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortLinkEntity {

  @Id
  private String target;
  private String newLink;
  private LocalDateTime expiration;
}
