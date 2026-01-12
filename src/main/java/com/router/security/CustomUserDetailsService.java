package com.router.security;




import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.router.entity.RbacMaster;
import com.router.entity.UserMaster;
import com.router.entity.UserOrganization;
import com.router.repository.RbacMasterRepository;
import com.router.repository.UserMasterRepository;
import com.router.repository.UserOrganizationRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired 
    private UserMasterRepository userRepository;
    @Autowired 
    private UserOrganizationRepository userOrganizationRepository;
    @Autowired 
    private RbacMasterRepository rbacMasterRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserMaster user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserOrganization userOrganization = userOrganizationRepository
                .findByUserMasterUserId(user.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("Role not assigned"));

      //  System.out.println("==============="+byRole);
        List<SimpleGrantedAuthority> authorities =
        		rbacMasterRepository.findAllByRoleMasterRoleId(userOrganization.getRoleMaster().getRoleId())
                        .stream()
                        .map(RbacMaster::getUsecaseMaster)
                        .map(u -> new SimpleGrantedAuthority(u.getUsecaseName()))
                        .collect(Collectors.toList());
        
        System.out.println(authorities);

        return new CustomUserDetails(user.getUsername(), user.getPassword(), authorities);
    }
}
