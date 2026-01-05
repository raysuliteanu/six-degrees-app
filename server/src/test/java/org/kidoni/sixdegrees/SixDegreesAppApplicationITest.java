package org.kidoni.sixdegrees;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "tmdb.api.url=https://api.themoviedb.org/",
    "tmdb.api.access-token=test-token"
})
class SixDegreesAppApplicationITest implements SixDegreesTestContainers {

    @Test
    void contextLoads() {
    }

}
