package com.demo.SBDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.demo.SBDemo.encryption.EncryptionUtilityService;
import com.demo.SBDemo.utils.AppConstants;

@Configuration
@EnableAuthorizationServer
public class OauthConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private EncryptionUtilityService encryptionUtilityService; 

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		       .withClient(AppConstants.OAUTH_CLIENT_ID)
		       .secret(encryptionUtilityService.passwordEncoder().encode(AppConstants.OAUTH_CLIENT_SECRET))
		       .authorizedGrantTypes(AppConstants.OAUTH_AUTH_TYPE,AppConstants.OAUTH_GRANT_TYPE,AppConstants.OAUTH_REFRESH_TOKEN)
		       .scopes(AppConstants.SCOPE_READ,AppConstants.SCOPE_WRITE)
		       .accessTokenValiditySeconds(1)
		       .autoApprove(true);
	}
	
	@Bean
	public JwtAccessTokenConverter defaultAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("signingKey");
		return converter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		JwtAccessTokenConverter jwtTokenEnhancer= defaultAccessTokenConverter();
		JwtTokenStore store = new JwtTokenStore(jwtTokenEnhancer);
		return store;
	}

}
