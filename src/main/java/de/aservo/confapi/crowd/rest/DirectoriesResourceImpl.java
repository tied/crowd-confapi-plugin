package de.aservo.confapi.crowd.rest;

import com.sun.jersey.spi.container.ResourceFilters;
import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.crowd.filter.SysadminOnlyResourceFilter;
import de.aservo.confapi.crowd.model.DirectoryBean;
import de.aservo.confapi.crowd.rest.api.DirectoriesResource;
import de.aservo.confapi.crowd.service.api.DirectoriesService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path(ConfAPI.DIRECTORIES)
@ResourceFilters(SysadminOnlyResourceFilter.class)
@Component
public class DirectoriesResourceImpl implements DirectoriesResource {

    private final DirectoriesService directoriesService;

    @Inject
    public DirectoriesResourceImpl(
            final DirectoriesService directoriesService) {

        this.directoriesService = directoriesService;
    }

    @Override
    public Response getDirectory(
            final long id) {

        final DirectoryBean directoryBean = directoriesService.getDirectory(id);
        if (directoryBean == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(directoryBean).build();
    }

}