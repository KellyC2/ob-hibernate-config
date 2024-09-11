package com.improvingskills.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;//Maneja la configuración de hibernate y prepara todo lo necesario para conectarse a la base de datos.
    private static SessionFactory sessionFactory;//representa la conexion con la base de datos.
    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try {

                //Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();//Carga la configuración de hibernate.cfg.xml y crea un registro de servicios.
                System.out.println("Creo el registro de servicios con los datos de hibernateConfig");
                //Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);//Se utiliza para recopilar la información de mapeo(de las entidades) desde un archivo de configuración o anotaciones.
                System.out.println("Mappeo las entidades desde un archivo de configuración o anotaciones");

                //Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();//Procesa el objeto metadata que contiene todos los detalles sobre las entidades, relaciones y esquema de base de datos
                System.out.println("Proceso la metadata");
                //Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();// construye la session a partir del metadato
                System.out.println("Construyó la session con toda la informacion");
            }catch(Exception e){
                e.printStackTrace();
                if (registry!=null){
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;

    }

    public static void shutdown(){
        if(registry!=null)
            StandardServiceRegistryBuilder.destroy(registry);// Se destruye el registro de servicios, liberando recursos conectados a la base de datos y a hibernate
    }
}
