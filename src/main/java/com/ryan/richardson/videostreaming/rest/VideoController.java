package com.ryan.richardson.videostreaming.rest;

import com.ryan.richardson.videostreaming.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("rest/videos")
@AllArgsConstructor
public class VideoController {
    private VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String saveVideo(@RequestParam("file") MultipartFile file,
                                            @RequestParam("name") String name) throws IOException {
        videoService.saveVideo(file, name);
        return "Video saved successfully.";
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Resource getVideoByName(@PathVariable("name") String name) {
        return new ByteArrayResource(videoService.getVideo(name).getData());
    }

    @GetMapping
    public List<String> getAllVideoNames(){
        return videoService.getAllVideoNames();
    }
}
