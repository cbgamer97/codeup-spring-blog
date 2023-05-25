package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceResults(@PathVariable int n, Model model){
        model.addAttribute("n", n);
        double randomNumber = Math.floor(Math.random() * 6 + 1);
        model.addAttribute("randomNumber", randomNumber);
        return "roll-dice";
    }
}
