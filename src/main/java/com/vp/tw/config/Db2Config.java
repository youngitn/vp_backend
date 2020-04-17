package com.vp.tw.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * 多資料來源設定檔
 * 
 * appliaction.properties先建立DB相關屬性,在這邊做引用.
 * 這邊主要定義@EnableJpaRepositories中的basePackages屬性
 * 需放入Repositories package name.
 * 
 * 需定義LocalContainerEntityManagerFactoryBean的basePackages屬性虛放入entity的package name.
 * 
 * 切記 不同資料來源的entity和Repositories(Dao)一定是要獨立的(主要是名稱,即使是將相同DB做成不同source也一樣要分開且名稱不能重覆)
 * 
 * 使用上看你用哪隻dao(repo)就能判斷要用哪個DataSource
 * 
 * @author USER
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.vp.tw.repository.t200" })
public class Db2Config {

	@Bean(name = "dataSourceProperties")
	@ConfigurationProperties("db2.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "dataSource")
	@ConfigurationProperties("db2.datasource.configuration")
	public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.vp.tw.entity.t200").persistenceUnit("db2")
				.build();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}