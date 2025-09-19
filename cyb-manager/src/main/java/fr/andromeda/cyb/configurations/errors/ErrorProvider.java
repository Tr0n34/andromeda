package fr.andromeda.cyb.configurations.errors;

import fr.andromeda.cyb.exceptions.ResourceNotFoundException;

public interface ErrorProvider {

    ResourceNotFoundException notFound(Class<?> entityClass);

}
