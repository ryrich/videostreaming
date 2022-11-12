package com.ryan.richardson.videostreaming.repository;

import com.ryan.richardson.videostreaming.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT name FROM Video")
    List<String> getAllVideoNames();

    Video findByName(String name);
    boolean existsByName(String name);
}
