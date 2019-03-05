package com.trlogic.statemachineconsumer;

import com.trlogic.statemachineconsumer.domain.Product;
import com.trlogic.statemachineconsumer.endpoint.StateMachineEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.SpelExpressionGuard;

@Configuration
@EnableStateMachine
public class StateMachineConfiguration extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
        states
                .withStates()
                .initial("initial")
                .state("start")
                .state("bonus")
                .end("end");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions) throws Exception {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseRaw("extendedState.variables['name'] == 'cikolata'");
        SpelExpressionGuard<String, String> spelExpressionGuard = new SpelExpressionGuard<>(expression);

        transitions.withExternal()
                .source("initial")
                .target("start")

                .and()
                .withExternal()

                .source("start")
                .target("bonus")
                .event("trigger")
                .guard(spelExpressionGuard)
                .action(new CustomAction())

                .and()
                .withExternal()

                .source("bonus")
                .target("bonus")
                .event("trigger")
                .guard(spelExpressionGuard)
                .action(new CustomAction());
    }
}

class CustomAction implements Action<String, String> {

    @Override
    public void execute(StateContext<String, String> stateContext) {
        Product product = (Product) stateContext.getExtendedState().getVariables().get("product");

        int bonus = product.getPrice() * product.getQuantity() * 50 / 100;
        StateMachineEndpoint.bonus = bonus;
    }
}