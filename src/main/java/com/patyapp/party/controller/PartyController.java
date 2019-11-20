package com.patyapp.party.controller;


import com.patyapp.party.datasource.PartyJpa;
import com.patyapp.party.entitys.PartyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class PartyController {

    @Autowired
    private PartyJpa partyJpa;

    @Autowired
    RestTemplate restTemplate;

    public Mono<PartyEntity> createParty(String locationPhoto, PartyEntity partyEntity) {
        ResponseEntity<byte[]> reponsePhoto = restTemplate.getForEntity(locationPhoto,byte[].class);
        System.out.println(reponsePhoto.getBody());
        partyEntity.photo = reponsePhoto.getBody();
        return partyJpa.save(partyEntity);
    }


    public Mono<PartyEntity> getParty(long id) {
        return partyJpa.findById(id);
    }

    public Flux<DataBuffer> getImage(long id) {
        Mono<PartyEntity> partyEntityMono = partyJpa.findById(id);
        PartyEntity partyEntity = getValue(partyEntityMono);
        DataBuffer buffer = new DefaultDataBufferFactory().wrap(partyEntity.photo);
        return Flux.just(buffer);
    }

    public void deleteParty(long id) {
        PartyEntity partyEntity = new PartyEntity();
        partyEntity.id = id;
        partyJpa.delete(partyEntity);
    }


    private byte[] getPhotoFromEntity(PartyEntity partyEntity) {
        return partyEntity.photo;
    }

    private byte[] getBtyeFromPic(String path) throws IOException {
/*
        byte[] bFile = Files.readAllBytes(new File(filePath).toPath());
*/
        //or this
        return Files.readAllBytes(Paths.get(path));
    }


    private void validate(PartyEntity partyEntity) throws Exception {
        System.out.println(partyEntity.name);
        System.out.println(partyEntity.minAge);
        if (partyEntity == null) {
            throw new Exception("Party c'ant be null");
        }
    }

    PartyEntity getValue(Mono<PartyEntity> mono) {
        return mono.block();
    }

}
