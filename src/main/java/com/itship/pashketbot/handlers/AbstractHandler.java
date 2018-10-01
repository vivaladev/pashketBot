package com.itship.pashketbot.handlers;

import com.itship.pashketbot.utils.MessageProvider;
import com.itship.pashketbot.utils.LocalizedMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.StringJoiner;

@SuppressWarnings("SameParameterValue")
public abstract class AbstractHandler {

    private MessageProvider messageProvider;

    protected AbstractHandler(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    protected void sendMessage(final AbsSender sender, final Long chatId,
                               final LocalizedMessage message) throws TelegramApiException {
        sender.execute(message(chatId, message));
    }

    protected void sendMessage(final AbsSender sender, final Long chatId,
                               final LocalizedMessage message, String... additionals) throws TelegramApiException {
        sender.execute(message(chatId, message, additionals));
    }

    protected void sendMessage(final AbsSender sender, final Long chatId,
                               final LocalizedMessage message, final Object... args) throws TelegramApiException {
        sender.execute(message(chatId, message, args));
    }

    protected SendMessage message(final Long chatId, final LocalizedMessage message) {
        final String localizedMessage = messageProvider.getMessage(message);
        return new SendMessage().setChatId(chatId).setText(localizedMessage).enableHtml(true);
    }

    protected SendMessage message(final Long chatId, final LocalizedMessage defaultMessage, final String... additionalMessages) {
        final String localizedMessage = defaultMessage + String.join(" ", additionalMessages);
        return new SendMessage().setChatId(chatId).setText(localizedMessage).enableHtml(true);
    }

    protected SendMessage message(final Long chatId, final LocalizedMessage message,
                                  final Object... args) {
        final String formattedMessage = MessageFormat.format(messageProvider.getMessage(message), args);
        return new SendMessage().setChatId(chatId).setText(formattedMessage).enableHtml(true);
    }


}
