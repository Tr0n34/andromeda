package fr.andromeda.sport.controllers;

import fr.andromeda.sport.objects.dto.RowDataDTO;
import fr.andromeda.sport.objects.inputs.RowDataInput;
import fr.andromeda.sport.services.RowaDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RawDataController {

    private static final Logger logger = LoggerFactory.getLogger(RawDataController.class);

    private final RowaDataService rowaDataService;

    @Autowired
    public RawDataController(RowaDataService rowaDataService) {
        this.rowaDataService = rowaDataService;
    }

    @QueryMapping(name = "rowDataByCriteria")
    public List<RowDataDTO> searchByCriteria(@Argument RowDataInput criteria) {
        List<RowDataDTO> rawDatas =  rowaDataService.search(criteria);
        return rawDatas;
    }

}
