package kr.ac.mjc.blog01;

import kr.ac.mjc.blog01.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    //회원가입 함수
    public UserResponseDto join(User user){
        UserResponseDto response=new UserResponseDto();
        if(user.getEmail().isEmpty()){      //이메일이 비어있는경우
            response.setSuccess(false);
            response.setMessage("이메일을 입력해주세요");
            return response;
        }
        //패스워드 체크는 생략
        //이미 같은 이메일로 가입된 사용자 있는지 체크
        User emailCheckUser=userRepository.findOneByEmail(user.getEmail());
        if(emailCheckUser!=null){   //이미 가입된 이메일이 있을경우
            response.setSuccess(false);
            response.setMessage("이미 가입된 이메일 입니다");
            return response;
        }
        //회원가입을 위한 체크 완료
        User joinedUser=userRepository.save(user);
        response.setSuccess(true);
        response.setUser(joinedUser);
        return response;
    }
    
    //로그인
    public UserResponseDto login(User user){
        UserResponseDto response=new UserResponseDto();

        User loginUser=userRepository.findOneByEmailAndPassword(user.getEmail(),
                user.getPassword());
        if(loginUser==null){    //아이디 또는 패스워드가 틀렸을경우
            response.setSuccess(false);
            response.setMessage("아이디 또는 패스워드가 틀렸습니다");
            return response;
        }
        //로그인 성공
        response.setSuccess(true);
        response.setUser(loginUser);
        return response;
    }
}
