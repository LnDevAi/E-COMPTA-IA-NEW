# E-COMPTA-IA INTERNATIONAL Backend

## 🚀 Démarrage rapide

### Prérequis
- **Java 17** ou supérieur
- **Maven 3.6** ou supérieur
- **Git**

### Installation et démarrage

#### Option 1: Script automatique (Recommandé)

**Windows:**
```bash
start-backend.bat
```

**Linux/Mac:**
```bash
chmod +x start-backend.sh
./start-backend.sh
```

#### Option 2: Commande Maven manuelle

```bash
# Compiler le projet
mvn clean compile

# Démarrer l'application
mvn spring-boot:run
```

### 🌐 Accès à l'application

Une fois démarré, l'application sera disponible sur :

- **Application principale** : http://localhost:8080
- **Documentation API (Swagger)** : http://localhost:8080/swagger-ui.html
- **Console H2 (Base de données)** : http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: `password`

## 📋 Fonctionnalités

### 🔐 Authentification et sécurité
- JWT Token Authentication
- Spring Security
- Gestion des rôles (USER, ADMIN)

### 🌍 Gestion des pays OHADA
- Initialisation automatique des 17 pays OHADA
- CRUD complet pour les pays
- Validation des données

### 👥 Gestion des tiers
- Clients, fournisseurs, banques
- Recherche avancée
- Gestion des contacts
- Validation des données

### 📊 API REST
- Endpoints RESTful complets
- Documentation OpenAPI/Swagger
- Validation des données
- Gestion des erreurs

## 🛠️ Configuration

### Profils disponibles

**Développement (par défaut):**
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

**Production:**
```bash
mvn spring-boot:run -Dspring.profiles.active=prod
```

### Base de données

**Développement (H2 - en mémoire):**
- Base de données automatiquement créée
- Données perdues au redémarrage

**Production (PostgreSQL):**
- Créer une base de données PostgreSQL
- Configurer les variables d'environnement :
  - `DB_URL`
  - `DB_USERNAME`
  - `DB_PASSWORD`
  - `JWT_SECRET`

## 📁 Structure du projet

```
src/main/java/com/ecomptaia/
├── config/           # Configurations Spring
├── controller/       # Contrôleurs REST
├── dto/             # Objets de transfert de données
├── entity/          # Entités JPA
├── repository/      # Repositories Spring Data
├── security/        # Configuration sécurité
└── service/         # Services métier
```

## 🔧 Développement

### Ajouter une nouvelle entité

1. Créer l'entité dans `entity/`
2. Créer le repository dans `repository/`
3. Créer le service dans `service/`
4. Créer le DTO dans `dto/`
5. Créer le contrôleur dans `controller/`

### Tests

```bash
# Exécuter tous les tests
mvn test

# Exécuter les tests avec couverture
mvn test jacoco:report
```

## 🚨 Dépannage

### Erreur "Port 8080 déjà utilisé"
```bash
# Changer le port dans application.properties
server.port=8081
```

### Erreur de compilation Java
- Vérifier que Java 17+ est installé
- Vérifier la variable JAVA_HOME

### Erreur Maven
```bash
# Nettoyer le cache Maven
mvn clean
rm -rf ~/.m2/repository
mvn dependency:resolve
```

## 📞 Support

Pour toute question ou problème :
- 📧 Email : contact@ecomptaia.com
- 📚 Documentation : http://localhost:8080/swagger-ui.html
- 🐛 Issues : Créer une issue sur le repository

## 📄 Licence

MIT License - Voir le fichier LICENSE pour plus de détails.
