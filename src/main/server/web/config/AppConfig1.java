//package web.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.Entity;
//
//public class AppConfig1 {
//    @Configuration
//    @ComponentScan
//    @EnableJpaRepositories("web.repository")
//    @EnableTransactionManagement
//    public class ContextConfiguration {
//        @Bean
//        public LocalEntityManagerFactoryBean entityManagerFactory() {
//            LocalEntityManagerFactoryBean result =
//                    new LocalEntityManagerFactoryBean();
//            result.setPersistenceUnitName("web.repository");
//            return result;
//        }
//
//        @Bean
//        public PlatformTransactionManager transactionManager() {
//            JpaTransactionManager result = new JpaTransactionManager();
//            result.setEntityManagerFactory(entityManagerFactory().getObject());
//            return result;
//        }
//    }
//}
