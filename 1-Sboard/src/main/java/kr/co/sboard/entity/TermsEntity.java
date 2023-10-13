package kr.co.sboard.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.TermsDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Terms")
public class TermsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String terms;
    private String privacy;


    public TermsDTO toDTO() {
        return TermsDTO.builder()
                .no(no)
                .privacy(privacy)
                .terms(terms)
                .build();
    }

}
