package com.itship.pashketbot.config;

import com.itship.pashketbot.botProcessing.PashketBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Configuration
public class BotConfiguration {

    @Bean
    @ConditionalOnProperty(name = "bot.enabled", havingValue = "true")
    public PashketBot pingServiceBotProxy(
            @Value("${bot.credential.telegram.login}") final String botName,
            @Value("${bot.credential.telegram.token}") final String botToken,
            DefaultBotOptions botOptions) {
        return new PashketBot(botOptions, botName, botToken);
    }

    @Bean
    public DefaultBotOptions botOptions(@Value("${bot.config.proxy.host}") final String proxyHost,
                                        @Value("${bot.config.proxy.port}") final String proxyPort,
                                        @Value("${bot.config.proxy.pass}") final String proxyPass,
                                        @Value("${bot.config.proxy.user}") final String proxyUser) {
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
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        }
        return botOptions;
    }

}
