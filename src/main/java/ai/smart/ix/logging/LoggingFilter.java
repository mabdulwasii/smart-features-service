package ai.smart.ix.logging;

import io.vertx.core.http.HttpServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Context
    UriInfo uriInfo;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        logger.info("Request {} {}", requestContext.getMethod(), uriInfo.getPath());
    }

    @Override
    public void filter(ContainerRequestContext requestCxt, ContainerResponseContext responseCxt) {
        logger.info("Response {} {}: {}", requestCxt.getMethod(), uriInfo.getPath(), responseCxt.getStatus());
    }
}
