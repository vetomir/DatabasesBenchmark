package pl.gregorymartin.as9.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataMongoRepository extends MongoRepository<DataMongo, Integer> {
}
