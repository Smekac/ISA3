package com.example.isa.service;

import com.example.isa.Model.Bid;

import java.util.List;

/**
 * Created by Ivan V. on 29-Jan-18
 */
public interface BidService {
    List<Bid> findAll();

    Bid findOne(Long id);

    Bid save(Bid bid);

    void delete(Long id);
}
