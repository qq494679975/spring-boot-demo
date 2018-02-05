package demo.cwd.security.config.security;

import demo.cwd.security.config.security.handler.SessionAuthenticationFailureHandler;
import demo.cwd.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

/**
 * Created by chenweida on 2018/1/24.
 */
@Configuration
@EnableWebSecurity
//@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    /**
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 每一次http的验证
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().access("@menuService.hasPerssion(request,authentication)")//全部路径都要有权限验证
                .and()
                .formLogin()
                //.loginPage("") //登陆页面
                .defaultSuccessUrl("/swagger-ui.html").failureUrl("/login") //登录成功之后的跳转
                .and()
                .logout().logoutSuccessUrl("/login")//退出成功跳转的url
                .and()
                .sessionManagement()
                .maximumSessions(1)//同一个用户在系统中的最大session数，默认1
                .maxSessionsPreventsLogin(false)//达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
                .sessionRegistry(sessionRegistry())
                .and()
                .sessionAuthenticationFailureHandler(new SessionAuthenticationFailureHandler())


        ;
        http.userDetailsService(userService);
    }


    //=====================初始化相关bean====================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 设置使用redis 存储session
     *
     * @return
     */
    @Bean
    SpringSessionBackedSessionRegistry sessionRegistry() {
        SpringSessionBackedSessionRegistry springSessionBackedSessionRegistry = new SpringSessionBackedSessionRegistry(this.sessionRepository);
        return springSessionBackedSessionRegistry;
    }
}
