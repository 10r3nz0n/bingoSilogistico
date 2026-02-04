package com.lorenzon.silogismos.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lorenzon.silogismos.domain.BingoCard;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class BingoService {

    private final List<BingoCard> cards;
    private final Set<String> drawnModes;
    private final Set<Integer> winners;
    private int draws;

    public BingoService(ObjectMapper objectMapper) {
        this.cards = loadCards(objectMapper);
        this.drawnModes = new HashSet<>();
        this.winners = new LinkedHashSet<>();
        this.draws = 0;
    }

    public synchronized void reset() {
        this.drawnModes.clear();
        this.winners.clear();
        this.draws = 0;
    }

    public synchronized void registerDraw(String modeName) {
        this.drawnModes.add(modeName);
        this.draws++;
    }

    public synchronized List<Integer> checkNewWinners() {
        List<Integer> newWinners = new ArrayList<>();
        for (BingoCard card : this.cards) {
            if (this.winners.contains(card.getNumber())) {
                continue;
            }
            if (this.drawnModes.containsAll(card.getModes())) {
                this.winners.add(card.getNumber());
                newWinners.add(card.getNumber());
            }
        }
        return newWinners;
    }

    public synchronized Set<String> getDrawnModes() {
        return Collections.unmodifiableSet(this.drawnModes);
    }

    public synchronized List<Integer> getWinners() {
        return new ArrayList<>(this.winners);
    }

    public synchronized int getDraws() {
        return this.draws;
    }

    private List<BingoCard> loadCards(ObjectMapper objectMapper) {
        try {
            ClassPathResource resource = new ClassPathResource("bingo-cards.json");
            InputStream inputStream = resource.getInputStream();
            List<BingoCard> loaded = objectMapper.readValue(inputStream, new TypeReference<List<BingoCard>>() {});
            return new ArrayList<>(loaded);
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao carregar bingo-cards.json", ex);
        }
    }
}
