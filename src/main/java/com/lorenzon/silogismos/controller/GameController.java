package com.lorenzon.silogismos.controller;

import com.lorenzon.silogismos.domain.ArgumentCard;
import com.lorenzon.silogismos.service.BingoService;
import com.lorenzon.silogismos.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private final DeckService deckService;
    private final BingoService bingoService;

    public GameController(DeckService deckService, BingoService bingoService) {
        this.deckService = deckService;
        this.bingoService = bingoService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api/reset")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> reset() {
        deckService.reshuffle();
        bingoService.reset();

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("status", "ok");
        return ResponseEntity.ok(payload);
    }

    @GetMapping("/api/next")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> next() {
        ArgumentCard card = deckService.next();

        bingoService.registerDraw(card.getName());
        List<Integer> newWinners = bingoService.checkNewWinners();

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("id", card.getId());
        payload.put("premises", card.getPremises());
        payload.put("conclusion", card.getConclusion());
        payload.put("newBingos", newWinners);
        payload.put("totalDraws", bingoService.getDraws());
        return ResponseEntity.ok(payload);
    }

    @GetMapping("/api/answer")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> answer() {
        ArgumentCard card = deckService.current();
        if (card == null) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("id", card.getId());
        payload.put("name", card.getName());
        payload.put("figure", card.getFigure());
        payload.put("mood", card.getMood());
        return ResponseEntity.ok(payload);
    }

    @GetMapping("/api/status")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("totalDraws", bingoService.getDraws());
        payload.put("winners", bingoService.getWinners());
        payload.put("drawnModesCount", bingoService.getDrawnModes().size());
        return ResponseEntity.ok(payload);
    }
}
