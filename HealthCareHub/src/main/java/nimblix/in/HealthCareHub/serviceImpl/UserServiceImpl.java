package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.UserRegistrationRequest;
import nimblix.in.HealthCareHub.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(UserRegistrationRequest request){
        //--Check if email already exists--
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            return "User already exists with this email";
        }
        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); //--password encrypted
        user.setRole(Role.DOCTOR); //--default role--
        user.setEnabled(true);

        userRepository.save(user);
        return "User Registered Successfully";
    }
}
