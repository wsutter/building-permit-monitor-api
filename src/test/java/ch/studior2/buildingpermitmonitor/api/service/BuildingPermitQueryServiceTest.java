package ch.studior2.buildingpermitmonitor.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.when;

import ch.studior2.buildingpermitmonitor.api.dto.BuildingPermitDto;
import ch.studior2.buildingpermitmonitor.api.repository.BuildingPermitQueryRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

@DisplayName("BuildingPermitQueryService")
class BuildingPermitQueryServiceTest {

  private final BuildingPermitQueryRepository repository =
      Mockito.mock(BuildingPermitQueryRepository.class);
  private final BuildingPermitQueryService service = new BuildingPermitQueryService(repository);

  @Nested
  @DisplayName("findBuildingPermits")
  class FindBuildingPermits {

    @ParameterizedTest(name = "{0}")
    @MethodSource("filters")
    @DisplayName("should return repository result")
    void shouldReturnRepositoryResult(
        String municipality, String category, List<BuildingPermitDto> expected) {
      when(repository.findBuildingPermits(municipality, category)).thenReturn(expected);

      assertThat(service.findBuildingPermits(municipality, category)).isEqualTo(expected);
    }

    static Stream<Arguments> filters() {
      BuildingPermitDto dto =
          new BuildingPermitDto(
              UUID.randomUUID(),
              "Umbau Wohnung",
              "Umbau Wohnung",
              "RENOVATION",
              "SUBMITTED",
              "Thalwil",
              LocalDate.of(2026, 5, 19),
              "Eisenbahnstrasse 27",
              47.2918,
              8.5631);

      return Stream.of(
          arguments(named("without filters", null), null, List.of(dto)),
          arguments(named("municipality filter", "Thalwil"), null, List.of(dto)),
          arguments(
              named("municipality and category filter", "Thalwil"), "RENOVATION", List.of(dto)));
    }
  }
}
