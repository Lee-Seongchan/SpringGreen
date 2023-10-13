package kr.co.sboard.repository;

import kr.co.sboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface UserRepositroy extends JpaRepository<UserEntity,String> {
        
        //아이디 중복체크
        int countByUid(String uid);
        
        //별명 중복체크
        int countByNick(String nick);

    }
