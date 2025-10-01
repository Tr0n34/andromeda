package fr.andromeda.cyb.services.business;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorService {

    private final ErrorProvider errorProvider;

    @Autowired
    public UserValidatorService(ErrorProvider errorProvider) {
        this.errorProvider = errorProvider;
    }

}
