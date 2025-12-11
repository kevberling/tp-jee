FROM quay.io/wildfly/wildfly:latest

# On passe en root pour l'installation
USER root

# Installation de Maven (Via le dépôt officiel Archive Apache)
# C'est la source la plus fiable pour éviter les erreurs "Could not resolve host"
ARG MAVEN_VERSION=3.9.6
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz -o /tmp/maven.tar.gz \
    && tar -xzf /tmp/maven.tar.gz -C /usr/share \
    && mv /usr/share/apache-maven-${MAVEN_VERSION} /usr/share/maven \
    && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn \
    && rm /tmp/maven.tar.gz

ENV MAVEN_HOME /usr/share/maven

# On repasse en utilisateur jboss
USER jboss

# Commande de démarrage
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]