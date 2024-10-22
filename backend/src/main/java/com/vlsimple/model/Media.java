package com.vlsimple.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String title;

    @Column
    private String artist;

    @Column(name = "playlist_id")
    private String playlistId;

    @Column
    private String locale;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // getters


    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public String getLocale() {
        return locale;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // setters
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Media() {
        // No-arg constructor
    }

}

