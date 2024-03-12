package com.olvera.warehouse.service;

import com.olvera.warehouse.dto.PersonDto;

public interface IPersonService {

    /**
     *
     * @param personDto - PersonDto object
     * @return
     */
    PersonDto createUser(PersonDto personDto);

    /**
     *
     * @param personId - Input person id
     * @return Person Details based on a given id
     */
    PersonDto getUserById(Long personId);

}
