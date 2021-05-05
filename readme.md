#spring
##整合mybatis
###原理
SqlSessionFactoryBean装载Mapper到IOC容器,
SQLSessionFactoryBean需要 --DataSource  
                         |-指定Mapper配置文件路径  -> MapperScannerConfigurer扫描Mapper接口所在包加入ICO容器 -> MyBatis使用动态代理技术动态产生Mapper文件对应的类对象   
                         |-mybatis-config.xml(非必须)
###mybatis配置流程  
>1. 在子工程中，导入具体的依赖
>2. 配置数据源(jdbc.properties)
>3. 创建spring的配置文件
>4. 在sping的配置文件中配置sqlsessionfactorybean
    1. 装配数据源 -> 在spring的配置文件中加载数据源文件
    2. 指定xxx-Mapper.xml文件的位置
    3. 指定mybatis全局配置文件的位置(可选)
>5. 配置MapScannerConfiger

####代码如下   
#####jdbc.properties
```properties
jdbc.user=root
jdbc.password=root
jdbc.url=jdbc:mysql://localhost:3306/crowd?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false
#坑点
#mysql驱动选择5.x版本
jdbc.driver=com.mysql.jdbc.Driver
#mysql驱动选择6.0及以上版本
jdbc.driver=com.mysql.cj.jdbc.Driver
```
#####spring-persist-mybatis.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-4.2.xsd">

    <!--加载外部属性文件 此处把jdbc.properties中的变量装载，以便配置数据源时使用-->
    <context:property-placeholder location="classpath:jdbc.properties"/> 

    <!--配置数据源-->
    <bean id="datasource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="username" value="${jdbc.user}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="driverClassName" value="${jdbc.driver}"/>
    </bean>

    <!--配置SqlSessionFactory整合mybatis
    此处的class名字必须要注意 切记切记
    如果使用mybatisPlus，必须配置为com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean
    如果使用mybatis，必须配置为org.mybatis.spring.SqlSessionFactoryBean
    -->
    <bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath*:../java/com/windvalley/crowd/mapper/xml/*Mapper.xml"/>
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--配置MapperScannerConfigurer扫描Mapper接口所在包 -->
    <bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.windvalley.crowd.mapper"/>
    </bean>
</beans>
```
