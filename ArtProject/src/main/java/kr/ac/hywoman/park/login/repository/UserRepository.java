package kr.ac.hywoman.park.login.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import kr.ac.hywoman.park.login.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인용
    Optional<User> findByUsername(String username);
    
    // 아이디 찾기용
    Optional<User> findByNameAndPhonenumberAndEmail(
    	    String name,
    	    String phonenumber,
    	    String email
    	);


    // 회원가입 중복 체크용
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhonenumber(String phonenumber);
    
    // 회원정보 수정 중복 체크 (본인 제외)
    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByPhonenumberAndIdNot(String phonenumber, Long id);
}
