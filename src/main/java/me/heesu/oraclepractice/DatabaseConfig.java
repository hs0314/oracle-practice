package me.heesu.oraclepractice;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    /*
    spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
    spring.datasource.username=system
    spring.datasource.password=oracle
     */
    @ConfigurationProperties(prefix="spring.datasource")
    @Bean(name="DataSoruce")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:oracle:thin:@localhost:49161/xe")
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                .username("system")
                .password("oracle")
                .build();

    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource);

        //mapper 워치에 따라서 classpath*:static/mappers/**/*Mapper.xml 이부분을 조정
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:static/mappers/**/*Mapper.xml"));
        return sqlSessionFactory.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
