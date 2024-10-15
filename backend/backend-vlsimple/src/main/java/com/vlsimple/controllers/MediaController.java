package com.vlsimple.controller;

import com.vlsimple.model.Media;
import com.vlsimple.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaRepository mediaRepository;

    // Listar todas as mídias
    @GetMapping
    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    // Adicionar uma nova mídia
    @PostMapping
    public Media createMedia(@RequestBody Media media) {
        return mediaRepository.save(media);
    }

    // Buscar uma mídia específica
    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaById(@PathVariable Long id) {
        return mediaRepository.findById(id)
                .map(media -> ResponseEntity.ok().body(media))
                .orElse(ResponseEntity.notFound().build());
    }
}
