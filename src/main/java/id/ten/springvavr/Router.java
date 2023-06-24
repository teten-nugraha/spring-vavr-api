package id.ten.springvavr;

import id.ten.springvavr.handlers.InvoiceHandler;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class Router {

    private final InvoiceHandler invoiceHandler;

    @Bean
    public RouterFunction<ServerResponse> applicationRouter() {
        return pingRouter().and(invoicesRouter());
    }

    private RouterFunction<ServerResponse> pingRouter() {
        return route(GET("/ping"), req -> ServerResponse.ok().body("pong"));
    }

    public RouterFunction<ServerResponse> invoicesRouter() {
        return route(GET("/invoices"), invoiceHandler::findAll)
                .andRoute(POST("/invoices"), invoiceHandler::create)
                .andRoute(GET("/invoices/{id}"), invoiceHandler::get)
                .andRoute(PUT("/invoices/{id}"), invoiceHandler::update)
                .andRoute(DELETE("/invoices/{id}"), invoiceHandler::delete);
    }
}
