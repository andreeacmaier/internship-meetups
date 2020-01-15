package com.arobs.internship.demointernship.service.prize;

import com.arobs.internship.demointernship.entity.Prize;
import org.springframework.stereotype.Component;

@Component
public class PrizeMapper {

    public Prize mapFromDtoToEntity(PrizeDTO prizeDTO){
        Prize prize = new Prize();
        prize.setDescription(prizeDTO.getDescription());
        prize.setValue(prizeDTO.getValue());
        return prize;
    }

}
