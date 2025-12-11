#!/bin/bash

# Attendre que Wildfly soit complètement démarré
echo "Waiting for Wildfly to start..."
until `/opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do
    sleep 1
done

echo "Wildfly started. Configuring Datasource..."

# 1. Télécharger le driver PostgreSQL (si pas déjà présent)
if [ ! -f /tmp/postgresql.jar ]; then
    curl -L -o /tmp/postgresql.jar https://jdbc.postgresql.org/download/postgresql-42.7.8.jar
fi

# 2. Commandes CLI pour installer le driver et la datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c <<EOF
# Ajouter le module module driver
module add --name=org.postgres --resources=/tmp/postgresql.jar --dependencies=javax.api,javax.transaction.api

# Enregistrer le driver
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)

# Créer la Datasource
data-source add --name=PostgresDS --jndi-name=java:/PostgresDS --driver-name=postgres --connection-url=jdbc:postgresql://localhost:5432/j2ee_db --user-name=j2ee --password=password --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter
EOF

echo "Configuration terminée !"