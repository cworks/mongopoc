package cworks.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends MongoRepository<Component, String> {
    Component findByName(String name);
    List<Component> findAllByName(String name);
}
