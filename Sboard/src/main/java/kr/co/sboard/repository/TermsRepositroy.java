package kr.co.sboard.repository;

import kr.co.sboard.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsRepositroy extends JpaRepository<TermsEntity, Integer> {
}
