package com.assessment.webservice.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.Transaction;
import com.assessment.webservice.service.AccountService;
import com.assessment.webservice.service.SecurityService;

@Path("{accountid}")
@Produces({ MediaType.APPLICATION_JSON})
public class AccountResource {

	private static final Logger logger = LoggerFactory.getLogger(AccountResource.class);
	@Inject
	SecurityService securityService;
	
	@Inject
	AccountService accountService;
	
	@GET
	@Path("transactions")
	@RolesAllowed({"ACCESS","UPDATE"})
	public Response fetchAccountTransactions(@NotNull @PathParam("accountId") Long accountId){
		logger.info("fetchAccountTransactions for username {} and account id {} ",securityService.getUserDetails().getUsername(),accountId);

		AccountDetails accountDetails = accountService.getAccountsByUsername(securityService.getUserDetails().getUsername()).stream()
		.filter(accountDetail -> accountDetail.getAccount().getAccountId() == accountId)
		.findFirst()
		.orElse(null);
		
		if (accountDetails != null ) {
			List<Transaction> transactions = accountService.getTransactionsByAccountId(accountId);
			return Response.status(Response.Status.OK).entity(transactions).build();	
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("Account doesn't belong to user").build();
		}
			
		
	}
	
}
