package org.kidoni.sixdegrees;

import org.kidoni.sixdegrees.tmdb.DefaultTmdbClient;
import org.kidoni.sixdegrees.tmdb.MovieDetailsRepository;
import org.kidoni.sixdegrees.tmdb.PersonDetailsRepository;
import org.kidoni.sixdegrees.tmdb.TmdbClient;
import org.kidoni.sixdegrees.tmdb.TmdbConfigurationProperties;
import org.kidoni.sixdegrees.tmdb.TmdbSixDegreesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class SixDegreesConfiguration {

    @Bean
    public TmdbClient tmdbClient(TmdbConfigurationProperties configurationProperties) {
        return new DefaultTmdbClient(configurationProperties);
    }

    @Bean
    public SixDegreesService sixDegreesService(TmdbClient tmdbClient, final PersonDetailsRepository personDetailsRepository, final MovieDetailsRepository movieDetailsRepository, final TaskExecutor taskExecutor) {
        return new TmdbSixDegreesService(tmdbClient, personDetailsRepository, movieDetailsRepository, taskExecutor);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new VirtualThreadTaskExecutor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
