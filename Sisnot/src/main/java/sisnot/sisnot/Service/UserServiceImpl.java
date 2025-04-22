package sisnot.sisnot.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sisnot.sisnot.Mapper.UserMapper;
import sisnot.sisnot.Model.Dto.UserProfileDTO;
import sisnot.sisnot.Model.Dto.UserRegistrationDTO;
import sisnot.sisnot.Model.entity.Alumno;
import sisnot.sisnot.Model.entity.Docente;
import sisnot.sisnot.Model.entity.Role;
import sisnot.sisnot.Model.entity.User;
import sisnot.sisnot.Model.enums.ERole;
import sisnot.sisnot.Repository.AlumnoRepository;
import sisnot.sisnot.Repository.DocenteRepository;
import sisnot.sisnot.Repository.RoleRepository;
import sisnot.sisnot.Repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AlumnoRepository alumnoRepository;
    private final DocenteRepository docenteRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserProfileDTO registerAlumno(UserRegistrationDTO registrationDTO) {

        return registerUserWithRole (registrationDTO, ERole.ALUMNO);
    }

    @Override
    public UserProfileDTO registerDocente(UserRegistrationDTO registrationDTO) {

        return registerUserWithRole (registrationDTO, ERole.DOCENTE);
    }

    @Override
    public UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO) {
        return null;
    }

    @Override
    public UserProfileDTO getUserProfileById(Integer id) {
        return null;
    }


    private UserProfileDTO registerUserWithRole (UserRegistrationDTO registrationDTO, ERole roleEnum) {
        // verificar si el email ya esta registrado o si ya existe un usuario con el mismo nombre y apellido
        boolean  existsByEmail = userRepository.existsByEmail(registrationDTO.getEmail());
        boolean existsAsAlumno= alumnoRepository.existsBynombreAndAndApellidoPaterno(registrationDTO.getFirstName(), registrationDTO.getLastName());
        boolean existsAsDocente= docenteRepository.existsBynombreAndAndApellidoPaterno(registrationDTO.getFirstName(), registrationDTO.getLastName());


        if (existsByEmail) {
            throw new IllegalArgumentException("El email ya existe");

        }

        if (existsAsAlumno || existsAsDocente) {
            throw new IllegalArgumentException("El usuario ya existe");
        }

        Role role= roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));

        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        User user = userMapper.toUserEntity(registrationDTO);
        user.setRole(role);

        if (roleEnum == ERole.ALUMNO) {
            Alumno alumno = new Alumno();
            alumno.setNombre(registrationDTO.getFirstName());
            alumno.setApellidoPaterno(registrationDTO.getLastName());
            alumno.setFechaIngreso(LocalDateTime.now());
            alumno.setUser(user);
        }

        if (roleEnum == ERole.DOCENTE) {
            Docente docente = new Docente();
            docente.setNombre(registrationDTO.getFirstName());
            docente.setApellidoPaterno(registrationDTO.getLastName());
            docente.setFechaIngreso(LocalDateTime.now());
            docente.setUser(user);
        }

        User savedUser = userRepository.save(user);

       return userMapper.toUserProfileDTO(savedUser);
    }

}
