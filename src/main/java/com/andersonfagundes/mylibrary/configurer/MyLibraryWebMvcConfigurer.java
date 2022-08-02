package com.andersonfagundes.mylibrary.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//Colocando uma configuração global para sobrescrever o que o spring tem em relação ao paginação

@Configuration
public class MyLibraryWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver pageHandler = new PageableHandlerMethodArgumentResolver();
        //mudando a configuração global de paginação = comecara da pagina com indice 0 e retornara 5 registros
        //esses valores poderao serem alterados passando querystring ex: ?size=20&page=1
        pageHandler.setFallbackPageable(PageRequest.of(0,5));
        resolvers.add(pageHandler);
    }
}
