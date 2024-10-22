package com.vlsimple.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private Long playlistId;

    @Column
    private String locale;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // getters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public Long getPlaylistId() {
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

