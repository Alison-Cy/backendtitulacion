# Yavigestion

## Description
Proyecto de gestion 
de fase pactica y vinculacion 
en la cual los estudiantes tendran una plataforma 
que les facilitara ver el progreso y subida de documentacion en la misma plataforma.

## Requisitos de levantamiento
- Java 22
- Gradle 
- Postgresql 15
- Docker (Opcional)

## Como levantar el proyecto
### Docker
```
    docker compose up -d 
```

o

```
    docker-compose up -d
```

### Local 
1. Cambiar los valores del ambiente local en el application.properties como es la configuracion de postgres
2. Compilar las dependencias con gradle usando el comando 
```
    ./gradlew clean build
```

3. Levantar el proyecto con java usando el comando 
```
./gradlew bootRun
```
Este comando es opcional si se levanta con un IDE de desarrollo.
