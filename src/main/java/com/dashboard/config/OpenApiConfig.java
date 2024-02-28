// package com.dashboard.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Contact;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.info.License;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;

// @Configuration
// public class OpenApiConfig {

// //     private SecurityScheme createAPIKeyScheme() {
// //         return new SecurityScheme().type(SecurityScheme.Type.HTTP)
// //                 .bearerFormat("JWT")
// //                 .scheme("bearer");
// //     }

//     @Bean
//     OpenAPI openAPI() {
//         return new OpenAPI()
//         // .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
//         //         .components(new Components().addSecuritySchemes("Bearer Authentication",
//         //                 createAPIKeyScheme()))
//                 .info(new Info().title("Dashboard API")
//                         .description("Some custom description of API.")
//                         .version("1.0").contact(new Contact().name("Said Muhammad Khatami")
//                                 .email("khatamisaid@gmail.com")
//                                 .url("https://www.pusiknas.polri.go.id"))
//                         .license(new License().name("License of API")
//                                 .url("https://www.pusiknas.polri.go.id"))
//                         .description("For JWT Token you must contact Administrator"));
//     }
// }
