package com.lftorresdev.desafiosbackend.api.openapi;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.lftorresdev.desafiosbackend.domain.request.UrlRequest;

@Api(value = "ShortLinkController", tags = "teste")
public interface ShortLinkOpenApi {

  @ApiOperation(value = "Endpoint that receive a link and return a shorten version of it")
  ResponseEntity<?> shortLink(UrlRequest urlRequest);

  @ApiOperation(value = "Endpoint that receive a parameter and search in the database the original link")
  ResponseEntity<?> redirect(String link);
}
