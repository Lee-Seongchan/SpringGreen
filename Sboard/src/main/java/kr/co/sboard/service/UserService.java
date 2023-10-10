package kr.co.sboard.service;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.repository.TermsRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private TermsRepositroy termsRepositroy;

    public TermsEntity findByTerms(){
        //Optional<TermsEntity> entity = termsRepositroy.findById(1);

        //TermsDTO dto = entity.get().toDTO();

        //return dto;
        return termsRepositroy.findById(1).get();
    }

}
