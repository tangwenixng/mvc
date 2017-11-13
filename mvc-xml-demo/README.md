## logback配置

1. 在pom.xml中增加如下依赖
    ```xml
    <!-- *****************logback************* -->
      <dependency>
          <groupId>org.slf4j</groupId>
          <artifactId>jcl-over-slf4j</artifactId>
          <version>1.7.21</version>
      </dependency>
      <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
          <version>1.2.3</version>
      </dependency>
    ```

    另外需要排除springmvc自带的log
    ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>4.3.10.RELEASE</version>
          <exclusions>
              <exclusion>
                  <groupId>commons-logging</groupId>
                  <artifactId>commons-logging</artifactId>
              </exclusion>
          </exclusions>
    </dependency>
    ```

2. 在src/main/resources/ 下新建logback.xml文件。

---

## 配置Spring Security

1. 在pom.xml中增加如下依赖：
    ```xml
    <dependency>
          <groupId>org.springframework.security</groupId>
          <artifactId>spring-security-config</artifactId>
          <version>4.2.0.RELEASE</version>
    </dependency>
    <dependency>
          <groupId>org.springframework.security</groupId>
          <artifactId>spring-security-web</artifactId>
          <version>4.2.0.RELEASE</version>
    </dependency>
    ```
    
 2. 在web.xml中添加
    ```xml
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy
        </filter-class>
      </filter>
    
      <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
      </filter-mapping>
    ```
    
 3. 新建 com.twx.config.SecurityConfig.java
    ```java
    package com.twx.config;
    
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    
    /**
     * Created by twx on 2017/11/4.
     */
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/list/**").access("hasRole('ADMIN')")
                    .and().formLogin();
        }
    
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER").and()
                    .withUser("admin").password("admin").roles("USER","ADMIN");
        }
    }

    ```
    
 4. 让应用上下文扫描到SecurityConfig，在applicationContext.xml中添加如下：
    ```xml
    <context:annotation-config/>
    
    <bean class="com.twx.config.SecurityConfig"/>
    ```
    
 ## Spring profiles使用
 
 1. 新建一个profiles.xml文件
    ```xml
         <beans profile="dev">
            <context:property-placeholder location="classpath:dev/jdbc.properties,classpath:dev/SEVS.properties" />
        </beans>
    
        <beans profile="test">
            <context:property-placeholder location="classpath:test/jdbc.properties,classpath:test/SEVS.properties" />
        </beans>
    
        <beans profile="prod">
            <context:property-placeholder location="classpath:prod/jdbc.properties,classpath:prod/SEVS.properties" />
        </beans>
    ```
 2. 把profiles.xml 导入到applicationContext.xml中
    ```xml
        <!--数据库jdbc配置文件-->
        <import resource="classpath:profiles.xml" />
    
        <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource"
              p:driverClassName="${jdbc.driverClassName}"
              p:url="${jdbc.url}"
              p:username="${jdbc.username}"
              p:password="${jdbc.password}" />
    ```
    
 3. 在web.xml中激活
    ```xml
    <context-param>
        <param-name>spring.profiles.active</param-name>
        <param-value>dev</param-value>
    </context-param>
    ```
    
    
 ## Maven profile使用
 
 1. 在pom.xml中添加
    ```xml
    <project>
        ...
        ...
        <profiles>
            <profile>
                <id>dev</id>
                <properties>
                    <profiles.active>dev</profiles.active>
                </properties>
                <activation>
                    <activeByDefault>true</activeByDefault>
                </activation>
            </profile>
            <profile>
                <id>test</id>
                <properties>
                    <profiles.active>test</profiles.active>
                </properties>
            </profile>
        </profiles>
    
        <build>
              <resources>
                  <resource>
                      <directory>src/main/resources</directory>
                      <includes>
                          <include>*.xml</include>
                          <include>log4j.properties</include>
                          <include>${profiles.active}/*</include>
                      </includes>
                  </resource>
              </resources>
        </build>
        ...
        ...
    </project>
    ```
    
 2. 当前类路径的目录结构是  
     src/main/resources/ <br>
      -   dev
           - jdbc.properties
           - SEVS.properties
      -   test
            -  jdbc.properties
            - SEVS.properties
       -  prod
            -  jdbc.properties
            - SEVS.properties
       -  log4j.properties 
       -  applicationContext.xml <br>
       -  dispatcher-servlet.xml <br>
         
 3. 执行命令 ```mvn clean package -DskipTests -Pdev```
 
    -DskipTests 跳过单元测
    
    -Pdev 选择激活dev环境（第一步中默认激活了dev环境）
    
 4. 在生成的war包中，查看classes目录下只有dev子目录，没有test prod等无关的目录。
 
 5. 这种方法需要手动更改web.xml中的spring.profiles.active
 
 
 ## Spring和Maven Profiles的区别
 
 Spring Profiles 是在运行起作用
 
 Maven Profiles 是在打包时起作用，为了排除不必要的环境配置文件
 
 两者用途不一样。