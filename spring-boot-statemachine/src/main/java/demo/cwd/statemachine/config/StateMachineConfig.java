package demo.cwd.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * Created by chenweida on 2018/1/24.
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .machineId("spring-boot-statemachine-id")
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(States.SI)//设置初始状态
                .end(States.S2)//设置结束状态
                .states(EnumSet.allOf(States.class)); //设置所有的状态的枚举类
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                    .source(States.SI)
                    .target(States.S1)
                    .event(Events.E1)
                    .action(new Action<States, Events>() {
                        @Override
                        public void execute(StateContext<States, Events> context) {
                            //2.触发转换时候的业务
                            System.out.println("st to s1 action");
                        }
                    })
                    .guard(new Guard<States, Events>() {
                        @Override
                        public boolean evaluate(StateContext context) {
                            // 1.先执行这个
                            System.out.println("st to s1 Guard");
                            return true;
                        }
                    })
                .and()
                .withExternal()
                .source(States.S1).target(States.S2).event(Events.E2)

        ;
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            // 每次流程转换 在 action结束之后都会触发一次监听
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
                System.out.println("State change from " + from.getId());
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
