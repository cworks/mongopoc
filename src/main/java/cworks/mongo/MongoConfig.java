package cworks.mongo;

import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static cworks.mongo.utils.JdkDateConverters.DateToLocalDateConverter;
import static cworks.mongo.utils.JdkDateConverters.DateToLocalDateTimeConverter;
import static cworks.mongo.utils.JdkDateConverters.DateToZonedDateTimeConverter;
import static cworks.mongo.utils.JdkDateConverters.LocalDateTimeToDateConverter;
import static cworks.mongo.utils.JdkDateConverters.LocalDateToDateConverter;
import static cworks.mongo.utils.JdkDateConverters.ZonedDateTimeToDateConverter;

@Configuration
@EnableMongoRepositories
@Import(value = MongoAutoConfiguration.class)
public class MongoConfig extends AbstractMongoConfiguration {

    private final Logger log = LoggerFactory.getLogger(MongoConfig.class);

    @Inject
    private Mongo mongo;

    @Inject
    private MongoProperties mongoProperties;

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Override
    public Mongo mongo() throws Exception {
        return mongo;
    }

    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(DateToZonedDateTimeConverter.INSTANCE);
        converters.add(ZonedDateTimeToDateConverter.INSTANCE);
        converters.add(DateToLocalDateConverter.INSTANCE);
        converters.add(LocalDateToDateConverter.INSTANCE);
        converters.add(DateToLocalDateTimeConverter.INSTANCE);
        converters.add(LocalDateTimeToDateConverter.INSTANCE);
        return new CustomConversions(converters);
    }


}
