package com.arobs.internship.demointernship.service.prize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    PrizeObject prizeObject;

    @Override
    @Transactional
    public void save(PrizeDTO prizeDTO) {
        prizeObject.savePrize(prizeDTO);
    }
}
