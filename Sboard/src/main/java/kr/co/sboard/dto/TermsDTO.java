package kr.co.sboard.dto;

import kr.co.sboard.entity.TermsEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TermsDTO {

    private int no;
    private String privacy;
    private String terms;

    public TermsEntity toEntity() {
        return TermsEntity.builder()
                .no(no)
                .privacy(privacy)
                .terms(terms)
                .build();
    }

}
