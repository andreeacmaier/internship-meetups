package com.arobs.internship.demointernship.service.prize;

import com.arobs.internship.demointernship.entity.Prize;
import com.arobs.internship.demointernship.repository.PrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrizeObject {

    @Autowired
    PrizeRepository prizeRepository;

    @Autowired
    PrizeMapper prizeMapper;

    public void savePrize(PrizeDTO prizeDTO) {
        Prize prize = prizeMapper.mapFromDtoToEntity(prizeDTO);
        prizeRepository.save(prize);
    }
}
