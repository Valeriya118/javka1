package ru.laba.sdacha.GolomakoAnekdotBot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.laba.sdacha.GolomakoAnekdotBot.model.Joke;
import ru.laba.sdacha.GolomakoAnekdotBot.service.JokeService;

import java.util.List;
import java.util.Random;

@Configuration
public class TelegramConfig {

    @Value("${bot.token}") // Забираем токен бота из конфига
    private String telegramToken;

    @Autowired
    private JokeService jokeService;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(telegramToken);
        bot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                if (update.message() != null && "/j".equals(update.message().text())) {
                    sendRandomJoke(update.message().chat().id());
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        return bot;
    }

    private void sendRandomJoke(Long chatId) {
        List<Joke> allJokes = jokeService.getAllJokes();
        if (!allJokes.isEmpty()) {
            Random random = new Random();
            Joke randomJoke = allJokes.get(random.nextInt(allJokes.size()));
            telegramBot().execute(new SendMessage(chatId, randomJoke.getJoke()));
        } else {
            telegramBot().execute(new SendMessage(chatId, "К сожалению, нет доступных шуток."));
        }
    }
}
