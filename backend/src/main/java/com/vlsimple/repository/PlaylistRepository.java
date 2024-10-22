package com.vlsimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vlsimple.model.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
