package com.ryan.richardson.videostreaming.rest;

import com.ryan.richardson.videostreaming.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Resource getVideoByName(@PathVariable("name") String name) {
        return new ByteArrayResource(videoService.getVideo(name).getData());
    }

    @GetMapping
    public List<String> getAllVideoNames(){

        List<H> hypos = new ArrayList<>();
        hypos.add(new H("a1"));
        hypos.add(new H("a1"));
        hypos.add(new H("a2"));

        List<E> exs = new ArrayList<>();
        exs.add(new E(hypos.get(0), "success"));
        exs.add(new E(hypos.get(1), "fail"));
        exs.add(new E(hypos.get(2), "success"));

        var rest = exs.stream()
                .collect(Collectors.groupingBy(e -> e.hypo.assertion));

        rest.forEach((assertion, results) -> {
            var successRate = (results.stream().filter(e -> e.result.equals("success")).count() / (double)results.size()) * 100;
        });

        return videoService.getAllVideoNames();
    }

    static class H {
        String assertion;

        public H(String assertion) {
            this.assertion = assertion;
        }
    }

    static class E {
        H hypo;
        String result;

        public E(H hypo, String result) {
            this.hypo = hypo;
            this.result = result;
        }
    }
}

