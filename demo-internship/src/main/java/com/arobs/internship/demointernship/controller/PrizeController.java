package com.arobs.internship.demointernship.controller;

import com.arobs.internship.demointernship.service.prize.PrizeDTO;
import com.arobs.internship.demointernship.service.prize.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prize")
public class PrizeController {

    @Autowired
    PrizeService prizeService;

    @PostMapping("/createPrize")
    public ResponseEntity createPrize(@RequestBody PrizeDTO prizeDTO){
        prizeService.save(prizeDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
