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
# On utilise "try-catch" implicite ou on ignore les erreurs si le module existe déjà pour éviter de bloquer le redémarrage
/opt/jboss/wildfly/bin/jboss-cli.sh -c <<EOF

# 1. Ajouter le module (si pas déjà fait)
# On essaie d'ajouter, si ça échoue (car existe déjà), ce n'est pas grave pour un script de dev
try
    module add --name=org.postgres --resources=/tmp/postgresql.jar --dependencies=javax.api,javax.transaction.api
catch
    echo "Module org.postgres existe peut-être déjà"
end-try

# 2. Enregistrer le driver
try
    /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)
catch
    echo "Driver postgres existe peut-être déjà"
end-try

# 3. Créer la Datasource
# On supprime l'ancienne si elle existe pour être sûr d'avoir la bonne config (optionnel mais propre pour le dev)
if (outcome == success) of /subsystem=datasources/data-source=TPJeebddDS:read-resource
    data-source remove --name=TPJeebddDS
end-if

data-source add \
    --name=TPJeebddDS \
    --jndi-name=java:/tpjeebdd \
    --driver-name=postgres \
    --connection-url=jdbc:postgresql://db:5432/tpjeebdd \
    --user-name=postgres \
    --password=postgres \
    --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker \
    --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter \
    --background-validation=true \
    --min-pool-size=5 \
    --max-pool-size=20

EOF

echo "Configuration terminée !"