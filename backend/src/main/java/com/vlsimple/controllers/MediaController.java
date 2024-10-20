
package com.vlsimple.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vlsimple.model.Media;
import com.vlsimple.repository.MediaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/media")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;
    

    // test listing
    @GetMapping
    public List<Media> getAllMedias() {
        return mediaRepository.findAll();
    }
    
    // add media
    @PostMapping
    public Media createMedia(@RequestBody Media media) {
        return mediaRepository.save(media);
        
    }

    // list by id
    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaEntity(@PathVariable Long id) {
        return mediaRepository.findById(id)
            .map(media -> ResponseEntity.ok().body(media))
            .orElse(ResponseEntity.notFound().build());
    }
    
    
}
