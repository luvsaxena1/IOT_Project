package io.ennate.configuration;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

@Configuration
public class MorphiaConfiguration {

	@Value("${package.name}")
	private String packageName;

	@Autowired
	private MongoProperties mongoProperties;

	private Morphia morphia() {
		final Morphia morphia = new Morphia();
		morphia.mapPackage(packageName);
		return morphia;
	}

	@Bean
	public Datastore datastore(MongoClient mongoClient) {
		final Datastore datastore = morphia().createDatastore(mongoClient, mongoProperties.getDatabase());
		datastore.ensureIndexes();
		return datastore;
	}

}
