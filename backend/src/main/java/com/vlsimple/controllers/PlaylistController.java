package com.vlsimple.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vlsimple.model.Playlist;
import com.vlsimple.repository.PlaylistRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistRepository playlistRepository;

    // all listing
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // id listing
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistId(@PathVariable Long id) {
        return playlistRepository.findById(id)
            .map(playlist -> ResponseEntity.ok().body(playlist))
            .orElse(ResponseEntity.notFound().build());
    }

    // post new playlist
    @PostMapping
    public Playlist postNewPlaylist(@RequestBody Playlist playlist) {
        if (playlist.getCreatedAt() == null) {
            playlist.setCreatedAt(LocalDateTime.now());
        }
        
        playlist.setModifiedAt(LocalDateTime.now());
        
        return playlistRepository.save(playlist);
    }
}

