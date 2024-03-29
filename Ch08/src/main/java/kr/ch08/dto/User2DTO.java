package kr.ch08.dto;

import jakarta.persistence.Id;
import kr.ch08.entity.User1Entity;
import kr.ch08.entity.User2Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User2DTO {

	@Id
	private String uid;
	private String name;
	private String hp;
	private int age;
	
	
	// Entity 변환 메서드
	public User2Entity toEntity() {
		return User2Entity.builder()
				.uid(uid)
				.name(name)
				.hp(hp)
				.age(age)
				.build();
	}
	
}
