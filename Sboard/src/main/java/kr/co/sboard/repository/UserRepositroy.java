package kr.co.sboard.repository;

import kr.co.sboard.entity.UserEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface UserRepositroy extends JpaRepository<UserEntity,String> {

}
