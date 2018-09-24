package com.itship.pashketbot.config;

import com.itship.pashketbot.bot.PashketBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Configuration
public class BotConfiguration {

    @Bean
    @ConditionalOnProperty(name = "bot.enabled", havingValue = "true")
    public PashketBot pingServiceBotProxy(
            @Value("${telegram.credentional.bot.login}") final String botName,
            @Value("${telegram.credentional.bot.token}") final String botToken,
            DefaultBotOptions botOptions) {
        return new PashketBot(botName, botToken, botOptions);
    }

    @Bean
    public DefaultBotOptions botOptions(@Value("${telegram.credentional.proxy.host}") final String proxyHost,
                                        @Value("${telegram.credentional.proxy.port}") final String proxyPort,
                                        @Value("${telegram.credentional.proxy.pass}") final String proxyPass,
                                        @Value("${telegram.credentional.proxy.user}") final String proxyUser) {
        if (!StringUtils.isEmpty(proxyUser) && !StringUtils.isEmpty(proxyPass)) {
            Authenticator.setDefault(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(proxyUser, proxyPass.toCharArray());
                }
            });
        }

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        if (!StringUtils.isEmpty(proxyHost) && !StringUtils.isEmpty(proxyPort)) {
            botOptions.setProxyHost(proxyHost);
            botOptions.setProxyPort(Integer.valueOf(proxyPort));
            botOptions.setProxyType(DefaultBotOptions.ProxyType.NO_PROXY);
        }
        return botOptions;
    }

}
