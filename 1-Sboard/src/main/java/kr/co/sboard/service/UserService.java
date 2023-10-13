package kr.co.sboard.service;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.entity.TermsEntity;
import kr.co.sboard.entity.UserEntity;
import kr.co.sboard.repository.TermsRepositroy;
import kr.co.sboard.repository.UserRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private TermsRepositroy termsRepositroy;

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TermsDTO findByTerms(){
        Optional<TermsEntity> entity = termsRepositroy.findById(1);
        TermsDTO dto = entity.get().toDTO();
        return dto;
        //return termsRepositroy.findById(1).get();
    }

    public void save(UserDTO dto){

        // 비밀번호 암호화
        dto.setPass1(passwordEncoder.encode(dto.getPass1()));

        //DTO를 Entity로 변환
        UserEntity entity = dto.toEntity();

        //DB insert
        userRepositroy.save(entity);

    }

    public int countUid(String uid){
        return userRepositroy.countByUid(uid);
    }

    public int countNick(String nick){
        return userRepositroy.countByNick(nick);
    }


}
