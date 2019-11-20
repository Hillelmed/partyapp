package com.patyapp.party.datasource;

import com.patyapp.party.entitys.PartyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface PartyJpa extends ReactiveCrudRepository<PartyEntity,Long> {

}