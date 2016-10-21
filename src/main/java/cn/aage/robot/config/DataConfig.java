package cn.aage.robot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by eric on 2015/5/12.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class DataConfig {
    @Autowired
    Environment env;

    @Bean(destroyMethod = "close", initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(env.getProperty("driverClassName"));
        druidDataSource.setUrl(env.getProperty("DATABASE_URL"));
        druidDataSource.setUsername(env.getProperty("DATABASE_USERNAME"));
        druidDataSource.setPassword(env.getProperty("DATABASE_PASSWORD"));
        druidDataSource.setMaxActive(50);
        druidDataSource.setInitialSize(20);
        druidDataSource.setMinIdle(20);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setTestWhileIdle(false);
        try {
            druidDataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        druidDataSource.setDefaultAutoCommit(false);
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("GenerateDdl")));
        vendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("showSql")));

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        //new String[]{"cn.com.workway.warehousesystem.model","cn.com.workway.bean"}
        entityManagerFactoryBean.setPackagesToScan(new String[]{"cn.aage.robot.model", "cn.aage.robot.bean"});
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.afterPropertiesSet();
        entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return entityManagerFactoryBean;
    }


    //    @Bean
//    public JpaTransactionManager transactionManager(){
//        JpaTransactionManager transactionManager=new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        transactionManager.setDataSource(dataSource());
//        transactionManager.setJpaDialect(jpaDialect());
        return transactionManager;
    }

}
