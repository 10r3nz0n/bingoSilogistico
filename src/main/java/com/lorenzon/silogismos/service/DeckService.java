package com.lorenzon.silogismos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lorenzon.silogismos.domain.ArgumentCard;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeckService {

    private final List<ArgumentCard> deck;
    private int index;
    private ArgumentCard current;

    public DeckService(ObjectMapper objectMapper) {
        this.deck = loadDeck(objectMapper);
        reshuffle();
    }

    public synchronized void reshuffle() {
        Collections.shuffle(this.deck);
        this.index = 0;
        this.current = null;
    }

    public synchronized ArgumentCard next() {
        if (this.index >= this.deck.size()) {
            reshuffle();
        }
        this.current = this.deck.get(this.index);
        this.index++;
        return this.current;
    }

    public synchronized ArgumentCard current() {
        return this.current;
    }

    private List<ArgumentCard> loadDeck(ObjectMapper objectMapper) {
        try {
            ClassPathResource resource = new ClassPathResource("cards.json");
            InputStream inputStream = resource.getInputStream();
            List<ArgumentCard> loaded = objectMapper.readValue(inputStream, new TypeReference<List<ArgumentCard>>() {});
            return new ArrayList<>(loaded);
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao carregar cards.json", ex);
        }
    }
}
