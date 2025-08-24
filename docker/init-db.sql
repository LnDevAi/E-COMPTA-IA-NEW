-- Script d'initialisation de la base de données PostgreSQL pour E-COMPTA-IA
-- Ce script sera exécuté automatiquement lors du premier démarrage du conteneur PostgreSQL

-- Créer la base de données si elle n'existe pas déjà
-- (PostgreSQL crée automatiquement la DB spécifiée dans POSTGRES_DB)

-- Créer des extensions utiles
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Créer un schéma pour l'application
CREATE SCHEMA IF NOT EXISTS ecomptaia;

-- Donner les permissions à l'utilisateur ecomptaia
GRANT ALL PRIVILEGES ON SCHEMA ecomptaia TO ecomptaia;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA ecomptaia TO ecomptaia;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA ecomptaia TO ecomptaia;

-- Définir le schéma par défaut
ALTER USER ecomptaia SET search_path TO ecomptaia, public;

-- Message de confirmation
SELECT 'Base de données E-COMPTA-IA initialisée avec succès!' as message;
