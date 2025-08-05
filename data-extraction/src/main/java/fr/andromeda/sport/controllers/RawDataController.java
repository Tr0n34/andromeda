package fr.andromeda.sport.controllers;

import fr.andromeda.sport.objects.dto.RawDataDTO;
import fr.andromeda.sport.objects.inputs.RawDataInput;
import fr.andromeda.sport.services.RawDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RawDataController {

    private static final Logger logger = LoggerFactory.getLogger(RawDataController.class);

    private final RawDataService rawDataService;

    @Autowired
    public RawDataController(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }

    @QueryMapping(name = "rowDataByCriteria")
    public List<RawDataDTO> searchByCriteria(@Argument RawDataInput criteria) {
        List<RawDataDTO> rawDatas =  rawDataService.search(criteria);
        return rawDatas;
    }

}
