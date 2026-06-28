@echo off

echo ===================================================
echo      INICIANDO MICROSERVICIOS DE TIENDA MANGA
echo ===================================================

echo [1/5] Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)...
cd eureka-server
start cmd /k "mvnw spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > null

echo [2/5] Iniciando API Gateway...
cd ../api-gateway
start cmd /k "mvnw spring-boot:run"

echo Esperando 5 segundos antes de lanzar los microservicios...
timeout /t 5 /nobreak > null

echo [3/5] Iniciando Microservicio Usuarios...
cd ../usuarios
start cmd /k "mvnw spring-boot:run"

echo [4/5] Iniciando Microservicio Mangas...
cd ../mangas
start cmd /k "mvnw spring-boot:run"

echo [5/5] Iniciando Microservicio Autores...
cd ../autores
start cmd /k "mvnw spring-boot:run"


echo Ecosistema lanzado. Dashboard disponible en http://localhost:8761