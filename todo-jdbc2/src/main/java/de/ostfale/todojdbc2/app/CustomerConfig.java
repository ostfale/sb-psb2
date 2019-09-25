package de.ostfale.todojdbc2.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Finally, we need to configure the ApplicationContext to enable the creation of repositories.
 * EnableJdbcRepositories enables the creation of repositories. Since it requires the presence
 * of some beans, we need the rest of the configuration.
 * Extending JdbcConfiguration adds some default beans to the ApplicationContext.
 * You can overwrite its methods to customize some of the behavior of Spring Data JDBC.
 * Created :  25.09.2019
 *
 * @author : Uwe Sauerbrei
 */
@Configuration
@EnableJdbcRepositories
public class CustomerConfig extends JdbcConfiguration {

    /*
     * The really important part is NamedParameterJdbcOperations,
     * which is used internally to submit SQL statements to the database.
     */
    @Bean
    NamedParameterJdbcTemplate operations() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    /*
     * The transaction manager is, strictly speaking, not necessary.
     * But you’d be working without support for transactions that span more than a single statement,
     * and nobody wants that, right?
     */
    @Bean
    PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /*
     * Spring Data JDBC doesn’t directly use the DataSource, but, since the TransactionManager
     * and the NamedParameterJdbcOperations need it, registering it as a bean is an easy
     * way to ensure both use the same instance.
     */
    @Bean
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-customer-schema.sql")
                .build();
    }
}
