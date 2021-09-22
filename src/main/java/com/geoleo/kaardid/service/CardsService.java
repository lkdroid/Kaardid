package com.geoleo.kaardid.service;

import com.geoleo.kaardid.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;


    public Boolean uniqueName(String name) {
       Boolean uniqueName = cardsRepository.uniqueName(name);
        if (!uniqueName) {
            return  true;

        }
        else {
            return false;


        }
    }


//    public String insertName(String name) {
//        cardsRepository.insertName(name);
//        return insertName(name);
//    }
}
