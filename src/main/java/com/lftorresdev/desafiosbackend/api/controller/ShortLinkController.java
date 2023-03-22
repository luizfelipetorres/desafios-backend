package com.lftorresdev.desafiosbackend.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lftorresdev.desafiosbackend.api.openapi.ShortLinkOpenApi;
import com.lftorresdev.desafiosbackend.domain.request.UrlRequest;
import com.lftorresdev.desafiosbackend.service.ShortLinkService;

@RestController
@RequestMapping(path = "/")
public class ShortLinkController implements ShortLinkOpenApi {

  @Autowired
  private ShortLinkService shortenerService;

  @PostMapping(path = "encurtar")
  public ResponseEntity<?> shortLink(@RequestBody UrlRequest urlRequest) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(shortenerService.encurtarLink(urlRequest));
  }

  @GetMapping(path = "{link}")
  public ResponseEntity<?> redirect(@PathVariable String link) {
    return shortenerService.redirecionar(link)
        .map(s -> ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(s))
            .build())
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
