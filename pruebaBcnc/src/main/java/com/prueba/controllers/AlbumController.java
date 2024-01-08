package com.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.dto.EnrichedAlbumDTO;
import com.prueba.services.AlbumService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

	@Autowired
	private final AlbumService albumService;

	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@GetMapping
	public Flux<EnrichedAlbumDTO> obtenerAlbums() {
	    return albumService.enriquecerAlbums()
	            .onErrorResume(error -> {
	                return Flux.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener álbumes enriquecidos"));
	            });
	}

}
