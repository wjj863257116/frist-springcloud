package boot.admin.secority;

import boot.dao.dao.Mapper.UserMapper;
import boot.dao.dao.entity.Roles;
import boot.dao.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BootUserDetailsService implements UserDetailsService{

    private Logger logger= LoggerFactory.getLogger(getClass());


    @Autowired
    private UserMapper usMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("-------------获取登录名和密码！");

        User user=usMapper.selectByName(username);

       if(user==null){
           logger.error("此用户不存在!");
           throw new UsernameNotFoundException("-------------不存在的用户："+username);
       }
        Roles  roles=new Roles();
         roles.setId(1);
         roles.setName("USER");
         List<Roles> list=new ArrayList<Roles>();
         list.add(roles);
         user.setRoles(list);
        return  user;
    }


}
