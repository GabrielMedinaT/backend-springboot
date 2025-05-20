package com.ieselrincon.repository.mongo;

import com.ieselrincon.model.MeasurementDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MeasurementMongoRepository extends MongoRepository<MeasurementDocument, String> {
}