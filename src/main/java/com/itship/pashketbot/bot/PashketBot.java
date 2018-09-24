package com.itship.pashketbot.bot;

import com.itship.pashketbot.handlers.MessageHandler;
import com.sun.research.ws.wadl.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PashketBot extends TelegramLongPollingBot{

    private static final Logger logger = LoggerFactory.getLogger(PashketBot.class);

    private final List<MessageHandler> messageHandlers;

    private TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

    private String botName;
    private String botToken;
    private BotSession session;

    public PashketBot(@Value("${telegram.credentional.bot.name}") String botName,
                      @Value("${telegram.credentional.bot.token}") String botToken,
                      List<MessageHandler> messageHandlers,
                      DefaultBotOptions defaultBotOptions) {
        super(defaultBotOptions);
        this.messageHandlers = messageHandlers;
        this.botName = botName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.info("Update received");
        if (update.hasMessage()) {
            try {
                for (MessageHandler messageHandler : messageHandlers) {
                    final boolean ans = messageHandler.execute(update.getMessage(), this);
                    if (ans) {
                        return;
                    }
                }
            } catch (TelegramApiException e) {
                logger.error("Failed to send message \"{}\" to {} due to error: {}", e.getMessage());
            }
        }
    }

    @PostConstruct
    public void start() {
        logger.info("username: {}, token: {}", botName, botToken);
        try {
            session = telegramBotsApi.registerBot(this);
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
        }
    }
}
