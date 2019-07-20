package com.assessment.webservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.powermock.api.mockito.PowerMockito;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.assessment.webservice.security.model.UserDetailsImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
public class SecurityServiceTest {
	
	@InjectMocks
	SecurityService securityService;
	
	@Mock
	SecurityContext mockContext;
	
	@Mock
	Authentication mockAuthentication;
	
	private UserDetails dummyUserDetails;
	@Before
	public void setUp()  {
        MockitoAnnotations.initMocks(this);
        dummyUserDetails = new UserDetailsImpl("test", 1l, new ArrayList<>());
        
        PowerMockito.mockStatic(SecurityContextHolder.class);
        PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(mockContext);
        when(mockContext.getAuthentication()).thenReturn(mockAuthentication);
	}
	
	@Test
	public void testGetUserDetailsSuccess(){
        when(mockAuthentication.getPrincipal()).thenReturn(dummyUserDetails);
        UserDetails loggedInUser = securityService.getUserDetails();
        assertEquals(dummyUserDetails,loggedInUser);
		
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testGetUserDetailsfail(){
        when(mockAuthentication.getPrincipal()).thenReturn(null);
        securityService.getUserDetails();
		
	}

}
