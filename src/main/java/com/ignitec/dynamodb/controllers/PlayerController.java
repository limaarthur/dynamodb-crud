package com.ignitec.dynamodb.controllers;

import com.ignitec.dynamodb.dto.ScoreDto;
import com.ignitec.dynamodb.entities.PlayerHistoryEntity;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/players")
public class PlayerController {

    private DynamoDbTemplate dynamoDbTemplate;

    public PlayerController(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping("/{username}/games")
    public ResponseEntity<Void> save(@PathVariable("username") String username, @RequestBody ScoreDto scoreDto) {

        var entities = PlayerHistoryEntity.fromScore(username, scoreDto);
        dynamoDbTemplate.save(entities);

        return ResponseEntity.ok().build();
    }
}
