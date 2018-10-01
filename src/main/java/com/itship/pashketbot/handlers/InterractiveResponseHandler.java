package com.itship.pashketbot.handlers;

import com.itship.pashketbot.utils.LocalizedMessage;
import com.itship.pashketbot.utils.MessageProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Component
public class InterractiveResponseHandler extends AbstractHandler implements MessageHandler{

    protected InterractiveResponseHandler (MessageProvider provider) {
        super(provider);
    }

    @Override
    public boolean execute (Message message, AbsSender sender) throws TelegramApiException {
        final String textMessage = message.getText();

        if (StringUtils.isNotEmpty(textMessage)) {
            if (StringUtils.startsWithIgnoreCase(textMessage, "/sword")) {
                Random rnd = new Random();
                sendMessage(sender, message.getChatId(), LocalizedMessage.SWORD_MES, String.valueOf(rnd.nextInt(100)));
                return true;
            }
            if (StringUtils.startsWithIgnoreCase(textMessage, "/rev")) {
                String phrase = textMessage.substring(4);
                sendMessage(sender, message.getChatId(), LocalizedMessage.REVERSE_NAME, StringUtils.reverse(phrase));
                return true;
            }
        }
        return false;
    }
}
