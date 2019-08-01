package ru.netrax.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class HibernateSessionFactoryUtil {

    public static SessionFactory getSessionFactory(String configurationFile, Class<?>... t) {
        Configuration configuration = new Configuration()
                .configure(configurationFile);

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        for (Class<?> i : t) {
            metadataSources.addAnnotatedClass(i);
        }
        Metadata metadata = metadataSources.getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }
}