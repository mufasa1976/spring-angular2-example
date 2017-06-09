# How to reproduce
1. Create a Multi-Module Maven Project with the following ``pom.xml``:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>io.github.mufasa1976</groupId>
  <artifactId>spring-angular2-example</artifactId>
  <version>1.0.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <modules>
    <module>frontend</module>
    <module>backend</module>
  </modules>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>1.8</java.version>
    <maven.compiler.source>${java.version}</maven.compiler.source>
    <maven.compiler.compilerVersion>${maven.compiler.source}</maven.compiler.compilerVersion>
    <maven.compiler.target>${java.version}</maven.compiler.target>

    <!-- Dependency Versions -->
    <spring-boot.version>1.5.3.RELEASE</spring-boot.version>
    <thymeleaf.version>3.0.6.RELEASE</thymeleaf.version>

    <!-- Plugin Versions -->
    <frontend-plugin.version>1.3</frontend-plugin.version>
    <querydsl-plugin.version>1.1.3</querydsl-plugin.version>

    <!-- Properties for Frontend Plugin -->
    <node.version>v7.9.0</node.version>
    <npm.version>4.2.0</npm.version>
  </properties>

  <dependencyManagement>
    <dependencies>
      <!-- Spring Boot BOM -->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${spring-boot.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>

      <!-- Thymeleaf -->
      <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf</artifactId>
        <version>${thymeleaf.version}</version>
      </dependency>
      <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring4</artifactId>
        <version>${thymeleaf.version}</version>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <build>
    <pluginManagement>
      <plugins>
        <!-- Build Helper Plugin -->
        <plugin>
          <groupId>org.codehaus.mojo</groupId>
          <artifactId>build-helper-maven-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>

        <!-- Sprint Boot Plugin -->
        <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
          <version>${spring-boot.version}</version>
        </plugin>

        <!-- Frontend Plugin -->
        <plugin>
          <groupId>com.github.eirslett</groupId>
          <artifactId>frontend-maven-plugin</artifactId>
          <version>${frontend-plugin.version}</version>
          <configuration>
            <nodeVersion>${node.version}</nodeVersion>
            <npmVersion>${npm.version}</npmVersion>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```
2. Create a Directory for ``backend``and ``frontend``.
3. Create the following ``backend/pom.xml``:
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>io.github.mufasa1976</groupId>
    <artifactId>spring-angular2-example</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <relativePath>..</relativePath>
  </parent>

  <artifactId>spring-angular2-example-backend</artifactId>

  <dependencies>
    <!-- Compile Dependencies -->

    <!-- Spring Boot -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Provided Dependencies -->

    <!-- Lombok -->
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <scope>provided</scope>
    </dependency>

    <!-- Runtime Dependencies -->

    <!-- Frontend -->
    <dependency>
      <groupId>${project.groupId}</groupId>
      <artifactId>spring-angular2-example-frontend</artifactId>
      <version>${project.version}</version>
      <scope>runtime</scope>
    </dependency>
  </dependencies>

  <build>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
      </resource>
    </resources>

    <plugins>
      <!-- Maven Resources Plugin -->
      <plugin>
        <artifactId>maven-resources-plugin</artifactId>
        <configuration>
          <delimiters>
            <delimiter>@</delimiter>
          </delimiters>
          <useDefaultDelimiters>false</useDefaultDelimiters>
        </configuration>
      </plugin>

      <!-- Spring Boot Plugin -->
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <executions>
          <execution>
            <goals>
              <goal>repackage</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```
4. Create the following ``frontend/pom.xml``:
```xml
To be continued
```
