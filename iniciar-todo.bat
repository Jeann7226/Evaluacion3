@echo off

echo ===================================================
echo      INICIANDO MICROSERVICIOS DE TIENDA MANGA
echo ===================================================

echo [1/3] Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)...
cd eureka-server
start cmd /k "mvnw spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > null

echo [2/3] Iniciando API Gateway...
cd ../api-gateway
start cmd /k "mvnw spring-boot:run"

echo Esperando 5 segundos antes de lanzar el microservicio...
timeout /t 5 /nobreak > null

echo [3/3] Iniciando Microservicio Usuarios...
cd ../usuarios
start cmd /k "mvnw spring-boot:run"

echo Ecosistema lanzado. Dashboard disponible en http://localhost:8761