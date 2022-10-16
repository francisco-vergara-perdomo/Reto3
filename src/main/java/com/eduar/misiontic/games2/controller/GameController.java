package com.eduar.misiontic.games2.controller;


import com.eduar.misiontic.games2.entities.Games;
import com.eduar.misiontic.games2.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Game")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/all")
    public List<Games> getAll(){
        return gameService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Games save(@RequestBody Games g){
        return gameService.save(g);
    }
}
