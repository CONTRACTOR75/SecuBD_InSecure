# ===========================
# Étape 1 : Build Maven
# ===========================
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /build

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

# Copier le wrapper Maven et les fichiers nécessaires
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copier tout le code source
COPY src ./src

# Compiler l'application
RUN ./mvnw clean package -DskipTests -B


# ===========================
# Étape 2 : Image finale
# ===========================
FROM eclipse-temurin:21-jre-alpine

# User sécurisé
RUN addgroup --system spring && adduser --system --ingroup spring spring
USER spring:spring

WORKDIR /app

# Copier le JAR construit
COPY --from=build /build/target/vulnerable-demo-0.0.1-SNAPSHOT.jar app.jar

# Port dynamique
EXPOSE ${PORT:-8080}

# Commande de lancement
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar /app/app.jar"]
