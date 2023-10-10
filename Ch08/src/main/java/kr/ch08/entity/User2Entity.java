package kr.ch08.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.ch08.dto.User1DTO;
import kr.ch08.dto.User2DTO;
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
@Entity
@Table(name = "user2")
public class User2Entity {
	
	@Id
	private String uid;
	private String name;
	private String hp;
	private int age;
	
	
	// DTO 변환 메서드
		public User2DTO toDTO() {
			return User2DTO.builder()
					.uid(uid)
					.name(name)
					.hp(hp)
					.age(age)
					.build();
		}
	
	
}
