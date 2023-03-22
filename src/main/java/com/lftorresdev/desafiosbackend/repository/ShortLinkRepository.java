package com.lftorresdev.desafiosbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lftorresdev.desafiosbackend.domain.entity.ShortLinkEntity;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLinkEntity, String> {

  Optional<ShortLinkEntity> findByNewLink(String link);
}
