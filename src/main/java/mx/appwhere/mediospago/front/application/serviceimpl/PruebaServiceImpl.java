package mx.appwhere.mediospago.front.application.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.appwhere.mediospago.front.domain.entities.BsfPruebaEntity;
import mx.appwhere.mediospago.front.domain.repositories.PruebaRepository;

@Service
public class PruebaServiceImpl {
    
    private PruebaRepository pruebaRepository;
    
    public PruebaServiceImpl(PruebaRepository pruebaRepository) {
	this.pruebaRepository = pruebaRepository;
    }
    
    public List<BsfPruebaEntity> findAll() {
	return (List<BsfPruebaEntity>)pruebaRepository.findAll(); 
    }
}
