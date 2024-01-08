package com.prueba.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.prueba.clases.Album;
import com.prueba.clases.Photo;
import com.prueba.dto.EnrichedAlbumDTO;

import reactor.core.publisher.Flux;

@Service
public class AlbumService {

	//uso de WebFlux, más moderno que  RestTemplate
	private final WebClient webClient;

    public AlbumService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
        					.baseUrl("https://jsonplaceholder.typicode.com")
        					.build();
    }

    public Flux<EnrichedAlbumDTO> enriquecerAlbums() {
        // Obtener álbumes
        Flux<Album> albumsFlux = webClient.get()
                .uri("/albums")
                .retrieve()
                .bodyToFlux(Album.class);

        // Obtener fotos y organizarlas por albumId en un Map
        Map<Integer, Collection<Photo>> photosByAlbumId = webClient.get()
                .uri("/photos")
                .retrieve()
                .bodyToFlux(Photo.class)
                .collectMultimap(Photo::getAlbumId)
                .block();

        // Convertir Map<Integer, Collection<Photo>> a Map<Integer, List<Photo>>
        Map<Integer, List<Photo>> photosByAlbumIdList = 
        		photosByAlbumId.entrySet()
        						.stream()
        						.collect(
        								Collectors.toMap(
        										Map.Entry::getKey, 
        										e -> List.copyOf(e.getValue()
        												)
        										)
        								);

        // Combinar álbumes y fotos
        return albumsFlux.map(album -> {
            List<Photo> photos = photosByAlbumIdList.getOrDefault(album.getId(), List.of());
            return new EnrichedAlbumDTO(album, photos);
        });
    }
	
}
