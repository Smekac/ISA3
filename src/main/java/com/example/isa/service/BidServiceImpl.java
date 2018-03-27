package com.example.isa.service;


import com.example.isa.Model.Bid;
import com.example.isa.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ivan V. on 29-Jan-18
 */
@Service
public class BidServiceImpl implements BidService {
    @Autowired
    private BidRepository bidRepository;


    @Override
    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    @Override
    public Bid findOne(Long id) {
        return bidRepository.findOne(id);
    }

    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public void delete(Long id) {
        bidRepository.delete(id);
    }

}
