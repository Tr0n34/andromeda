package fr.andromeda.sport.services;

import fr.andromeda.sport.configurations.GraphQLConfiguration;
import fr.andromeda.sport.controllers.RawDataController;
import fr.andromeda.sport.objects.dto.RawDataDTO;
import fr.andromeda.sport.objects.inputs.RawDataInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@GraphQlTest(RawDataController.class)
@Import(GraphQLConfiguration.class)
public class RawDataControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private RawDataService rawDataService;

    @Test
    void testRowDataByCriteria() {

        RawDataInput input = new RawDataInput();
        input.setCadenceSpm(24);
        input.setPowerW(100);
        input.setStrokeCount(10);
        input.setCaloriesKcal(50);
        input.setHeartRateBpm(80);
        input.setElapsedTimeS(30.5f);

        RawDataDTO dto = new RawDataDTO();
        dto.setCadenceSpm(24);
        dto.setPowerW(100);
        dto.setStrokeCount(10);
        dto.setCaloriesKcal(50);
        dto.setHeartRateBpm(80);
        dto.setElapsedTimeS(30.5f);

        when(rawDataService.search(any(RawDataInput.class))).thenReturn(List.of(dto));

        graphQlTester
                .document("""
          query {
            rowDataByCriteria(criteria: {
              cadenceSpm: 24,
              powerW: 100,
              strokeCount: 10,
              caloriesKcal: 50,
              heartRateBpm: 80,
              elapsedTimeS: 30.5
            }) {
              cadenceSpm
              powerW
              strokeCount
              caloriesKcal
              heartRateBpm
              elapsedTimeS
            }
          }
        """)
                .execute()
                .path("rowDataByCriteria[0].cadenceSpm").entity(Integer.class).isEqualTo(24)
                .path("rowDataByCriteria[0].powerW").entity(Integer.class).isEqualTo(100)
                .path("rowDataByCriteria[0].elapsedTimeS").entity(Float.class).isEqualTo(30.5f);
    }

}
