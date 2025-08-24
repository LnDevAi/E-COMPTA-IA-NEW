#!/bin/bash

echo "========================================"
echo "  E-COMPTA-IA INTERNATIONAL BACKEND"
echo "========================================"
echo

echo "🚀 Démarrage du backend Spring Boot..."
echo

# Vérifier si Maven est installé
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven n'est pas installé ou n'est pas dans le PATH"
    echo "📥 Veuillez installer Maven depuis: https://maven.apache.org/download.cgi"
    exit 1
fi

echo "✅ Maven détecté"
echo

# Nettoyer et compiler le projet
echo "🔧 Compilation du projet..."
mvn clean compile -q
if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la compilation"
    exit 1
fi

echo "✅ Compilation réussie"
echo

# Démarrer l'application
echo "🌐 Démarrage de l'application..."
echo "📊 L'application sera disponible sur: http://localhost:8080"
echo "📚 Documentation API: http://localhost:8080/swagger-ui.html"
echo "🗄️  Console H2: http://localhost:8080/h2-console"
echo

mvn spring-boot:run

echo
echo "�� Arrêt du backend"
