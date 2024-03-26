package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.repository.ConcertRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ConcertServiceTest {

    @Mock
    private ConcertRepository concertRepository;

    @InjectMocks
    private ConcertServiceImpl concertService;

    @Test
    public void createConcert_ShouldSaveAndReturnConcert() {

        Concert concert = new Concert();
        concert.setName("Test Concert");
        concert.setArtist("Test Artist");
        Concert savedConcert = new Concert();
        savedConcert.setId(1L);
        savedConcert.setName(concert.getName());
        savedConcert.setArtist(concert.getArtist());
        when(concertRepository.save(concert)).thenReturn(savedConcert);
        Concert result = concertService.createConcert(concert);
        verify(concertRepository).save(concert);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(savedConcert.getId());
        assertThat(result.getName()).isEqualTo(savedConcert.getName());
        assertThat(result.getArtist()).isEqualTo(savedConcert.getArtist());
    }

    private static Stream<Arguments> provideConcertsForTest() {
        Concert existingConcert = new Concert();
        existingConcert.setId(1L);
        existingConcert.setName("Test Concert");
        return Stream.of(
                Arguments.of(1L, Optional.of(existingConcert), null),
                Arguments.of(2L, Optional.empty(), RuntimeException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideConcertsForTest")
    public void getConcertByIdTest(Long id, Optional<Concert> optionalConcert, Class<? extends Throwable> expectedException) {
        when(concertRepository.findById(id)).thenReturn(optionalConcert);

        if (expectedException != null) {
            Exception exception = (Exception) assertThrows(expectedException, () -> concertService.getConcertById(id));
            assertTrue(exception.getMessage().contains("Concert not found with id: " + id));
        } else {
            Concert result = concertService.getConcertById(id);
            assertNotNull(result);
            assertEquals(id, result.getId());
        }
    }
}