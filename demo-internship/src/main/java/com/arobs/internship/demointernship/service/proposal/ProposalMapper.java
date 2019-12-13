package com.arobs.internship.demointernship.service.proposal;

import com.arobs.internship.demointernship.entity.Proposal;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.generator.specification.Convert;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

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
        addAllMapperBeans(applicationContext);

        factory.classMap(Proposal.class, ProposalDTO.class)
                .byDefault()
                .register();

    }

    private void addAllMapperBeans(final ApplicationContext applicationContext) {
        //get all existing mappers from context

        Mapper <String, Mapper> mappers = (Mapper<String, Mapper>) applicationContext.getBeansOfType(Mapper.class);
        factory.registerMapper(mappers);

        //get all converters from context
        Map <String, Convert> beansOfType = applicationContext.getBeansOfType(Convert.class);
        factory.getConverterFactory().registerConverter(beansOfType);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        init();
    }
}
