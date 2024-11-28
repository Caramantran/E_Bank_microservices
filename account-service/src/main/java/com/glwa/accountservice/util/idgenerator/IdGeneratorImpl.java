package com.glwa.accountservice.util.idgenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@Transactional
public class IdGeneratorImpl implements IdGenerator{

    private final CompterRepository repository;

    public IdGeneratorImpl(CompterRepository repository) {
        this.repository = repository;
    }


    @Override
    public String autoGenerate() {
        try {
            List<Compter> compters = repository.findAll();
            Compter compter;
            if(compters.isEmpty()) {
                compter = new Compter((long) 123456789);
            }
            else {
                compter = compters.get(compters.size() - 1);
                repository.deleteById(compter.getId());
            }
            return saveCompter(compter);
        }catch(Exception e) {
            log.error("Id not generated due to :"+e.getMessage());
            return null;
        }
    }

    private String saveCompter(Compter compter) {
        Long cpt = compter.getId()+1;
        repository.save(new Compter(cpt));
        String prefix = String.valueOf(LocalDate.now().getYear());
        return prefix+cpt;
    }
}
