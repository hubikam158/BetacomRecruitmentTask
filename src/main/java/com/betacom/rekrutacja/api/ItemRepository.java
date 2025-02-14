package com.betacom.rekrutacja.api;

import com.betacom.rekrutacja.entity.Item;
import com.betacom.rekrutacja.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    List<Item> findAllByOwner(User user);
}
