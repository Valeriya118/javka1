package ru.laba.sdacha.GolomakoAnekdotBot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.laba.sdacha.GolomakoAnekdotBot.model.Joke;
import ru.laba.sdacha.GolomakoAnekdotBot.service.JokeService;

import java.util.List;

@RestController
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {
    private final JokeService jokeService;

    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @PostMapping
    ResponseEntity<Void> addJoke(@RequestBody Joke joke) {
        if (jokeService.addJoke(joke))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    ResponseEntity<List<Joke>> getJokes() {
        return ResponseEntity.ok(jokeService.getAllJokes());
    }

    @GetMapping("/{id}")
    ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        return jokeService.getJokeById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Joke> deleteJokeById(@PathVariable Long id) {
        if (jokeService.deleteJokeById(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Joke> changeJokeById(@PathVariable Long id, @RequestBody Joke joke) {
        if (jokeService.changeJokeById(id, joke))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}