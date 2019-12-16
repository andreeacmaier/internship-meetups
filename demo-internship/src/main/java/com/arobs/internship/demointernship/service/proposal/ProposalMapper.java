package com.arobs.internship.demointernship.service.proposal;

import com.arobs.internship.demointernship.entity.Proposal;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

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
        //addAllMapperBeans(applicationContext);

        factory.classMap(Proposal.class, ProposalDTO.class)
                .byDefault()
                .register();

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        init();
    }
}
