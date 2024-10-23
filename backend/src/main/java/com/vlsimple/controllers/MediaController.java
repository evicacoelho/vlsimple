
package com.vlsimple.controllers;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vlsimple.model.Media;
import com.vlsimple.repository.MediaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;

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
        if (media.getCreatedAt() == null) {
            media.setCreatedAt(LocalDateTime.now());
        }
        
        return mediaRepository.save(media);
    }

    // list by id
    @GetMapping("/{id}")
    public ResponseEntity<Media> getMediaEntity(@PathVariable Long id) {
        return mediaRepository.findById(id)
            .map(media -> ResponseEntity.ok().body(media))
            .orElse(ResponseEntity.notFound().build());
    }

    // add audio file
    @PostMapping("/file")
    public ResponseEntity<?> addMediaFiles(@RequestBody String filePath) {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                Media media = processFile(path);
                return ResponseEntity.ok(mediaRepository.save(media));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to proccess file: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("File not found.");
        }
    }

    // parse folder to add audio files
    @PostMapping("/folder")
    public ResponseEntity<?> addMediaFolder(@RequestBody String folderPath) {
        Path dir = Paths.get(folderPath);
        if (Files.exists(dir) && Files.isDirectory(dir)) {
            try (Stream<Path> paths = Files.walk(dir)) {
                List<Media> mediaList = paths.filter(Files::isRegularFile)
                    .map(this::processFile)
                    .collect(Collectors.toList());
                mediaRepository.saveAll(mediaList);
                return ResponseEntity.ok(mediaList);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to proccess file: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Directory not found.");
        }
    }

    // method: proccessing files with apache tika
    public Media processFile(Path filePath) {
        Media media = new Media();

        media.setLocale(filePath.toString());

        try (InputStream stream = Files.newInputStream(filePath)) {
            // file parsing
            AutoDetectParser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();

            parser.parse(stream, handler, metadata);

            // metadata extraction
            String artistMetadata = metadata.get("xmpDM:artist");
            String titleMetadata = metadata.get("title");

            // what if there is no metadata? use file name as song name
            if (titleMetadata == null || titleMetadata.isEmpty()) {
                titleMetadata = filePath.getFileName().toString();
            }

            // title
            media.setTitle(titleMetadata);
            //artist
            if (artistMetadata != null) {
                media.setArtist(artistMetadata);
            }
            // creation date
            media.setCreatedAt(LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace(); // err log
        }

        return media;
    }

    // getter & setter
    public MediaRepository getMediaRepository() {
        return mediaRepository;
    }

    public void setMediaRepository(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

}
