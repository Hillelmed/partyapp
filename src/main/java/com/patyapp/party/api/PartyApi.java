package com.patyapp.party.api;


import com.patyapp.party.controller.PartyController;
import com.patyapp.party.entitys.PartyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("partyservice")
public class PartyApi {

    @Autowired
    PartyController partyController;


    @GetMapping
    public Mono<PartyEntity> getParty(@RequestParam("id") long id) {
        return partyController.getParty(id);
    }

    @PostMapping
    public Mono<PartyEntity> createParty(@RequestParam("location") String locationPhoto, @RequestBody PartyEntity partyEntity) throws Exception {
        validate(partyEntity);
        return partyController.createParty(locationPhoto, partyEntity);
    }

    @DeleteMapping
    public void deleteParty(@RequestParam("id") long id) {
        partyController.deleteParty(id);
    }

    @GetMapping(value = "getimageparty")
    public Flux<DataBuffer> captchaImage(@RequestParam("id") long id) {
        return partyController.getImage(id);
    }


    private void validate(PartyEntity partyEntity) throws Exception {
        System.out.println(partyEntity.name);
        System.out.println(partyEntity.minAge);
        if (partyEntity == null) {
            throw new Exception("Party c'ant be null");
        }
    }


}
