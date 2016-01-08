package cworks.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentValueRepository extends MongoRepository<ComponentValue, String> {
    ComponentValue findByValue(String value);
}
