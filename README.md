# Spring JPA Support

System        | Status
--------------|------------------------------------------------        
CI master     | [![Build Status][travis-master]][travis-url]
CI develop    | [![Build Status][travis-develop]][travis-url]
Dependency    | [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.namics.oss.spring.support.jpa/spring-jpa-support/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.namics.oss.spring.support.jpa/spring-jpa-support)

The module provides helper classes for working with jpa.


## Usage

### Maven Dependency (Latest Version in `pom.xml`):

	<dependency>
		<groupId>com.namics.oss.spring.support.jpa</groupId>
		<artifactId>spring-jpa-support</artifactId>
		<version>1.0.0</version>
	</dependency>
	
### Requirements	

Java: JDK 8 

### Usage options

Although the module is basically independent of Spring but is in our environment the most frequent application. 

#### Use Naming Strategy with Spring Boot and jpa

First: Set the project prefix for all table names:
	
	public class YourProjcetDatabaseNamingStrategy extends com.namics.oss.spring.support.jpa.DatabaseNamingStrategy {
		@Override
		protected String getTablePrefix() {
			return "abc_";
		}
	}
	
Second: Set your created strategy for Spring Boot with following property

	`spring.jpa.hibernate.naming.physical-strategy=com.namics.packageToYourStrategy.YourProjcetDatabaseNamingStrategy`


#### Use Base Entity
You could extend your entities from com.namics.oss.spring.support.jpa.BaseEntity to get some basic function such as creation timestamp and last modified timestamp.



[travis-master]: https://travis-ci.org/namics/spring-jpa-support.svg?branch=master
[travis-develop]: https://travis-ci.org/namics/spring-jpa-support.svg?branch=develop
[travis-url]: https://travis-ci.org/namics/spring-jpa-support
