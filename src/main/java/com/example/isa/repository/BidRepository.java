package com.example.isa.repository;


import com.example.isa.Model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Smekac
 */
public interface BidRepository extends JpaRepository<Bid,Long> {
    List<Bid> findAll();

    Bid findOne(Long id);

    Bid save(Bid bid);

    void delete(Long id);

}
