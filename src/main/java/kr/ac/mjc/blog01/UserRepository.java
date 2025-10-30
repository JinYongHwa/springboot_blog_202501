package kr.ac.mjc.blog01;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    
    //회원 가입시 사용
    User save(User user);
    
    //로그인시에 이메일과 패스워드 동시에 맞는 사용자 찾기(사용자 인증)
    User findOneByEmailAndPassword(String email,String password);
    
    //가입된 이메일 있는지 체크에 사용
    User findOneByEmail(String email);
}
