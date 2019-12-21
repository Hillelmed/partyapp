package com.patyapp.party.datasource;

import com.patyapp.party.entitys.PartyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface PartyJpa extends ReactiveCrudRepository<PartyEntity,Long> {

}