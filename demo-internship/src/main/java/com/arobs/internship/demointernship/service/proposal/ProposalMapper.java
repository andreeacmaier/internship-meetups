package com.arobs.internship.demointernship.service.proposal;

import com.arobs.internship.demointernship.entity.Proposal;
import com.arobs.internship.demointernship.entity.User;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProposalMapper extends ConfigurableMapper implements ApplicationContextAware {

    private MapperFactory factory;
    private ApplicationContext applicationContext;

    public ProposalMapper() {
        super(false);
    }

    @Override
    protected void configure(MapperFactory factory) {
        this.factory = factory;
        factory.classMap(Proposal.class, ProposalDTO.class)
                //.field("user.id", "userId")
                .customize(new CustomMapper<Proposal, ProposalDTO>() {
                    @Override
                    public void mapAtoB(Proposal proposal, ProposalDTO proposalDTO, MappingContext context) {
                        proposalDTO.setUserId(proposal.getUser().getId());
                    }

                    @Override
                    public void mapBtoA(ProposalDTO proposalDTO, Proposal proposal, MappingContext context) {
                        User user = new User(proposalDTO.getUserId());
                        proposal.setUser(user);
                    }
                })
                .byDefault()
                .register();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        init();
    }
}
