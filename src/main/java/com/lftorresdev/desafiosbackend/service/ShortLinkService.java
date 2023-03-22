package com.lftorresdev.desafiosbackend.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lftorresdev.desafiosbackend.domain.dto.ShortLinkDto;
import com.lftorresdev.desafiosbackend.domain.entity.ShortLinkEntity;
import com.lftorresdev.desafiosbackend.domain.mapper.ShortLinkMapper;
import com.lftorresdev.desafiosbackend.domain.request.UrlRequest;
import com.lftorresdev.desafiosbackend.repository.ShortLinkRepository;

@Service
public class ShortLinkService {

  @Autowired
  private ShortLinkRepository repository;

  @Autowired
  private ShortLinkMapper mapper;

  public ShortLinkDto encurtarLink(UrlRequest urlRequest) {
    ShortLinkEntity entity;
    Optional<ShortLinkEntity> optionalEntity = repository.findById(urlRequest.getUrl());
    if (optionalEntity.isEmpty() || isExpired(optionalEntity.get())) {
      LocalDateTime expiration = LocalDateTime.now().plusMinutes(1);
      entity = repository.save(new ShortLinkEntity(urlRequest.getUrl(), generateUrl(), expiration));
      return mapper.toDto(entity);
    }
    return mapper.toDto(optionalEntity.get());
  }

  private boolean isExpired(ShortLinkEntity shortLinkEntity) {
    return shortLinkEntity.getExpiration().isBefore(LocalDateTime.now());
  }

  private String generateUrl() {
    int limit = new Random().nextInt(5, 10);
    String uri = UUID.randomUUID()
        .toString()
        .substring(0, limit)
        .replace("-", "");
    return formatLink(uri);
  }

  private String formatLink(String link) {
    String base = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
    return String.format("%s/%s", base, link);
  }


  public Optional<String> redirecionar(String link) {
    Optional<ShortLinkEntity> entity = repository.findByNewLink(formatLink(link));
    if (entity.isPresent() && !isExpired(entity.get())) {
      return entity.map(ShortLinkEntity::getTarget);
    }
    return Optional.empty();
  }
}
