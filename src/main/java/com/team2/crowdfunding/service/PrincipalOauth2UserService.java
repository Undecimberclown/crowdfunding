package com.team2.crowdfunding.service;

import com.team2.crowdfunding.controller.PrincipalDetails;
import com.team2.crowdfunding.entity.User;
import com.team2.crowdfunding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration());
        System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("getAttributes : "+oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String provider_id = oAuth2User.getAttribute("sub");
        String username = provider +"_" + provider_id;
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .role(role)
                    .email(email)
                    .provider(provider)
                    .provider_id(provider_id)
                    .build();
            userRepository.save(userEntity);
        }


        return new PrincipalDetails(userEntity,oAuth2User.getAttributes());
    }
}
