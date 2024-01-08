package com.prueba.dto;

import java.util.List;

import com.prueba.clases.Album;
import com.prueba.clases.Photo;

public class EnrichedAlbumDTO extends AlbumDTO {

    public EnrichedAlbumDTO(Album album, List<Photo> photos) {
        super(album.getUserId(), album.getId(), album.getTitle());
        this.photos = photos;
    }

	private List<Photo> photos;

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

}
