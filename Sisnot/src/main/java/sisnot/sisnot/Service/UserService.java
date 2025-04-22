package sisnot.sisnot.Service;

import sisnot.sisnot.Model.Dto.UserProfileDTO;
import sisnot.sisnot.Model.Dto.UserRegistrationDTO;

public interface UserService {
    // Registro Alumno
    UserProfileDTO registerAlumno(UserRegistrationDTO registrationDTO);

   //REgistro Docente
    UserProfileDTO registerDocente(UserRegistrationDTO registrationDTO);

    //ACtualizar Perfil usuario
    UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO);

    //Get user Profile by ID
    UserProfileDTO getUserProfileById(Integer id);

}
