package com.vp.tw.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "t100EntityManagerFactory", basePackages = {
		"com.vp.tw.repository.t100" })
public class Db1Config {

	@Primary
	@Bean(name = "t100DataSourceProperties")
	@ConfigurationProperties("t100.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "t100DataSource")
	@ConfigurationProperties("t100.datasource.configuration")
	public DataSource dataSource(@Qualifier("t100DataSourceProperties") DataSourceProperties t100DataSourceProperties) {
		return t100DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "t100EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("t100DataSource") DataSource t100DataSource) {
		return builder.dataSource(t100DataSource).packages("com.vp.tw.entity.t100").persistenceUnit("t100")
				.build();
	}

	@Primary
	@Bean(name = "t100TransactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("t100EntityManagerFactory") EntityManagerFactory t100EntityManagerFactory) {
		return new JpaTransactionManager(t100EntityManagerFactory);
	}

}
