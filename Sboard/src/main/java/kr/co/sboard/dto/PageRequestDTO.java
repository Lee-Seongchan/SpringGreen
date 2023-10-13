package kr.co.sboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    private int pg = 1;

    @Builder.Default
    private int size = 10;

    @Builder.Default
    private String cate = "notice";

                                        //"no"
    public Pageable getpPageable(String sort){          //Sort는 정렬 정보를 나타내는 객체
        return PageRequest.of(this.pg - 1, this.size, Sort.by(sort).descending());
    }

    // 주로 해당 클래스나 라이브러리의 객체를 생성하고 초기화하는 용도로 사용
    // PageRequest.of()의 경우, 이것은 Spring Data JPA에서 페이지 정보를 생성하기 위한 정적 메서드입니다.
    // 이 메서드는 PageRequest 클래스에 정의되어 있으며, 페이징 및 정렬 정보를 설정하는 데 사용됩니다.
    // 다른 예로는 Java의 Set 인터페이스를 구현한 클래스인 HashSet에서 Set.of() 메서드가 있으며,
    // 초기화된 집합(Set)을 생성하는 데 사용됩니다.


}
