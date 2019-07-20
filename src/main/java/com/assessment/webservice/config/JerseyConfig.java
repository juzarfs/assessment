package com.assessment.webservice.config;

import static org.glassfish.jersey.server.ServerProperties.BV_SEND_ERROR_IN_RESPONSE;
import static org.glassfish.jersey.server.ServerProperties.MEDIA_TYPE_MAPPINGS;
import static org.glassfish.jersey.server.ServerProperties.WADL_FEATURE_DISABLE;
import static org.glassfish.jersey.servlet.ServletProperties.FILTER_FORWARD_ON_404;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import com.assessment.webservice.Application;

@ApplicationPath(Application.BASE_PATH)
@Configuration
public class JerseyConfig extends ResourceConfig {

	
	@PostConstruct
	public void init() {
		// Registers classes for jersey classpath scanning.
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
		scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
		registerClasses(scanner.findCandidateComponents("com.assessment.*").stream()
				.map(beanDefinition -> ClassUtils.resolveClassName(beanDefinition.getBeanClassName(), getClassLoader() ))
		.collect(Collectors.toSet()));
		
		//Enable media type mappings
		property(MEDIA_TYPE_MAPPINGS,"json:application/json");
		//Enable java Bean validation integration
		property(BV_SEND_ERROR_IN_RESPONSE,Boolean.TRUE.toString());
		//disable wadl
		property(WADL_FEATURE_DISABLE,Boolean.TRUE.toString());
		// forward 404s to spring mvc, which servers up non-jersey resources
		property(FILTER_FORWARD_ON_404,Boolean.TRUE.toString());
		
		register(RolesAllowedDynamicFeature.class);

	}
}
