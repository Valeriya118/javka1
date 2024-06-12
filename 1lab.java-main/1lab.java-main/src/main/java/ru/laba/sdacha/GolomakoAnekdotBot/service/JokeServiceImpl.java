package ru.laba.sdacha.GolomakoAnekdotBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.laba.sdacha.GolomakoAnekdotBot.model.Joke;
import ru.laba.sdacha.GolomakoAnekdotBot.repository.JokesRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JokeServiceImpl implements JokeService {

    private final JokesRepository jokesRepository;

    @Override
    public boolean addJoke(Joke joke) {
        Optional<Joke> jokeOptional = jokesRepository.findByJoke(joke.getJoke());
        if (jokeOptional.isEmpty()) {
            joke.setId(null);
            Date curDate = new Date();
            joke.setCreated(curDate);
            joke.setUpdated(curDate);
            jokesRepository.save(joke);
            return true;
        }
        else
            return false;
    }

    @Override
    public List<Joke> getAllJokes() {
        return jokesRepository.findAll();
    }

    @Override
    public Optional<Joke> getJokeById(Long id) {
        return jokesRepository.findById(id);
    }

    @Override
    public boolean deleteJokeById(Long id) {
        Optional<Joke> jokeOptional = jokesRepository.findById(id);
        if (jokeOptional.isPresent()) {
            jokesRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean changeJokeById(Long id, Joke joke) {
        Optional<Joke> jokeOptional = jokesRepository.findById(id);
        if (jokeOptional.isPresent()) {
            Joke newJoke = jokeOptional.get();
            newJoke.setJoke(joke.getJoke());
            newJoke.setUpdated(new Date());
            jokesRepository.save(newJoke);
            return true;
        }
        else
            return false;
    }
}