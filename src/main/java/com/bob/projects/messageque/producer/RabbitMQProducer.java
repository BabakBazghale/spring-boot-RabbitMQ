package com.bob.projects.messageque.producer;

import com.bob.projects.messageque.model.Book;
import com.bob.projects.messageque.model.BookData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class RabbitMQProducer {
    private static final Faker faker = new Faker();
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;


    public String sendBookMessage(BookData bookData) {
        Book initializedBook = null;
        initializedBook = bookData == null ? initializedBook.builder()
                .author(faker.book().author())
                .title(faker.book().title())
                .publisher(faker.book().publisher())
                .genre(faker.book().genre()).build() : initializedBook.builder()
                .author(bookData.getAuthor())
                .title(bookData.getTitle())
                .publisher(bookData.getPublisher())
                .genre(bookData.getGenre()).build();
        rabbitTemplate.convertAndSend(exchange, routingKey, initializedBook);
        log.info("book message produced successfully.");
        return "book message sent successfully.";
    }

    public String sendCustomBookMessage(BookData bookData) {
        Book initializedBook = null;
        ObjectMapper objectMapper = new ObjectMapper();
        initializedBook = bookData == null ? initializedBook.builder()
                .author(faker.book().author())
                .title(faker.book().title())
                .publisher(faker.book().publisher())
                .genre(faker.book().genre()).build() : initializedBook.builder()
                .author(bookData.getAuthor())
                .title(bookData.getTitle())
                .publisher(bookData.getPublisher())
                .genre(bookData.getGenre()).build();
        Message message = null;
        try {
            message = MessageBuilder
                    .withBody(objectMapper.writeValueAsString(initializedBook).getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        log.info("book message produced successfully.");
        return "book message sent successfully.";
    }
}
