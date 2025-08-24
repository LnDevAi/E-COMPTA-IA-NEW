@echo off
echo ========================================
echo   E-COMPTA-IA INTERNATIONAL BACKEND
echo ========================================
echo.

echo 🚀 Démarrage du backend Spring Boot...
echo.

REM Vérifier si Maven est installé
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Maven n'est pas installé ou n'est pas dans le PATH
    echo 📥 Veuillez installer Maven depuis: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo ✅ Maven détecté
echo.

REM Nettoyer et compiler le projet
echo 🔧 Compilation du projet...
call mvn clean compile -q
if %errorlevel% neq 0 (
    echo ❌ Erreur lors de la compilation
    pause
    exit /b 1
)

echo ✅ Compilation réussie
echo.

REM Démarrer l'application
echo 🌐 Démarrage de l'application...
echo 📊 L'application sera disponible sur: http://localhost:8080
echo 📚 Documentation API: http://localhost:8080/swagger-ui.html
echo 🗄️  Console H2: http://localhost:8080/h2-console
echo.

call mvn spring-boot:run

echo.
echo 👋 Arrêt du backend
pause
