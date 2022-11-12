package com.ryan.richardson.videostreaming.service;

import com.ryan.richardson.videostreaming.domain.Video;
import com.ryan.richardson.videostreaming.exceptions.VideoAlreadyExistsException;
import com.ryan.richardson.videostreaming.exceptions.VideoNotFoundException;
import com.ryan.richardson.videostreaming.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SimpleVideoService implements VideoService {

    private VideoRepository videoRepository;

    @Override
    public Video getVideo(String name) {
        if (!videoRepository.existsByName(name)){
            throw new VideoNotFoundException();
        }

        return videoRepository.findByName(name);
    }

    @Override
    public List<String> getAllVideoNames() {
        return videoRepository.getAllVideoNames();
    }

    @Override
    public void saveVideo(MultipartFile file, String name) throws IOException {
        if (videoRepository.existsByName(name)){
            throw new VideoAlreadyExistsException();
        }

        Video newVid = new Video(name, file.getBytes());
        videoRepository.save(newVid);
    }
}
