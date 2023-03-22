package com.lftorresdev.desafiosbackend.domain.mapper;

import org.mapstruct.Mapper;

import com.lftorresdev.desafiosbackend.domain.dto.ShortLinkDto;
import com.lftorresdev.desafiosbackend.domain.entity.ShortLinkEntity;

@Mapper(componentModel = "spring")
public interface ShortLinkMapper {

  ShortLinkDto toDto(ShortLinkEntity entity);
}
