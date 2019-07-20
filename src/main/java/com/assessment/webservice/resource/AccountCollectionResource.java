package com.assessment.webservice.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.service.AccountService;
import com.assessment.webservice.service.SecurityService;


@Path("accounts")
@Produces({ MediaType.APPLICATION_JSON})
public class AccountCollectionResource {
	private static final Logger logger = LoggerFactory.getLogger(AccountCollectionResource.class);

	@Context
	private ResourceContext resourceContext;
	
	@Inject
	AccountService accountService;
	
	@Inject
	SecurityService securityService;
	
	@GET
	@RolesAllowed({"ACCESS","UPDATE"})
	public Response getAccounts() {
		logger.info("getaccounts for username {}",securityService.getUserDetails().getUsername());
		List<AccountDetails> accounts = accountService.getAccountsByUsername(securityService.getUserDetails().getUsername());
		return Response.status(Response.Status.OK).entity(accounts).build();
	}
	
	@Path("{accountId}")
	public AccountResource getAccountResource(@NotNull @PathParam("accountId") Long accountId) {
		return resourceContext.getResource(AccountResource.class);
	}
	
	
}
