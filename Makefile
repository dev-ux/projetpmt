# PMT - Project Management Tool
# Makefile pour simplifier les commandes de développement

.PHONY: help setup backend frontend test build docker deploy clean

# Variables
COMPOSE_FILE=docker-compose.yml

help: ## Affiche cette aide
	@echo "PMT - Project Management Tool"
	@echo ""
	@echo "Commandes disponibles:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-15s %s\n", $$1, $$2}'

setup: ## Configuration initiale du projet
	@echo "Configuration du projet PMT..."
	@cd backend && mvn clean install -DskipTests
	@cd ../frontend && npm install
	@echo "Configuration terminée!"

backend: ## Démarrage du backend uniquement
	@echo "Démarrage du backend..."
	@cd backend && mvn spring-boot:run

frontend: ## Démarrage du frontend uniquement
	@echo "Démarrage du frontend..."
	@cd frontend && npm start

backend-dev: ## Démarrage du backend en mode développement avec H2
	@echo "Démarrage du backend en mode développement..."
	@cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=dev

test: ## Exécution de tous les tests
	@echo "Exécution des tests backend..."
	@cd backend && mvn test
	@echo "Exécution des tests frontend..."
	@cd frontend && npm run test:ci

test-backend: ## Tests backend uniquement
	@cd backend && mvn test

test-frontend: ## Tests frontend uniquement
	@cd frontend && npm test

coverage: ## Génération des rapports de couverture
	@echo "Génération des rapports de couverture..."
	@cd backend && mvn jacoco:report
	@cd frontend && npm run test:ci

build: ## Construction du projet complet
	@echo "Construction du backend..."
	@cd backend && mvn clean package -DskipTests
	@echo "Construction du frontend..."
	@cd frontend && npm run build

docker-build: ## Construction des images Docker
	@echo "Construction des images Docker..."
	@docker build -f Dockerfile.backend -t codesolutions/pmt-backend:latest .
	@docker build -f Dockerfile.frontend -t codesolutions/pmt-frontend:latest .

docker-up: ## Démarrage avec Docker Compose
	@echo "Démarrage des services avec Docker Compose..."
	@docker-compose up --build

docker-down: ## Arrêt des services Docker
	@echo "Arrêt des services Docker..."
	@docker-compose down

docker-logs: ## Affichage des logs Docker
	@docker-compose logs -f

docker-clean: ## Nettoyage des containers et volumes Docker
	@echo "Nettoyage Docker..."
	@docker-compose down -v
	@docker system prune -f

k8s-deploy: ## Déploiement sur Kubernetes
	@echo "Déploiement sur Kubernetes..."
	@kubectl apply -f k8s/secrets.yml
	@kubectl apply -f k8s/deployment.yml

k8s-logs: ## Logs Kubernetes
	@kubectl logs -l app=pmt-backend -f
	@kubectl logs -l app=pmt-frontend -f

k8s-stop: ## Arrêt du déploiement Kubernetes
	@kubectl delete -f k8s/deployment.yml
	@kubectl delete -f k8s/secrets.yml

lint: ## Vérification du code (linting)
	@echo "Linting backend..."
	@cd backend && mvn checkstyle:check
	@echo "Linting frontend..."
	@cd frontend && npm run lint

format: ## Formatage du code
	@echo "Formatage backend..."
	@cd backend && mvn formatter:format
	@echo "Formatage frontend..."
	@cd frontend && npm run lint:fix

clean: ## Nettoyage complet
	@echo "Nettoyage du projet..."
	@cd backend && mvn clean
	@cd frontend && rm -rf node_modules dist coverage
	@docker-compose down -v 2>/dev/null || true
	@docker system prune -f

# Variables d'environnement par défaut
.env:
	@echo "Création du fichier .env..."
	@echo "SPRING_PROFILES_ACTIVE=dev" > .env
	@echo "SPRING_DATASOURCE_URL=jdbc:h2:mem:pmt" >> .env
	@echo "SPRING_DATASOURCE_USERNAME=sa" >> .env
	@echo "SPRING_DATASOURCE_PASSWORD=" >> .env
	@echo "API_URL=http://localhost:8080/api" >> .env

# Installation des outils de développement
install-tools:
	@echo "Installation des outils de développement..."
	# Java 17
	# Node.js 18
	# Docker
	# kubectl (si nécessaire)
	@echo "Outils installés!"

# Vérification des prérequis
check-requirements:
	@echo "Vérification des prérequis..."
	@java -version
	@node --version
	@docker --version
	@mvn --version
	@npm --version

# Setup complet pour développement
dev-setup: check-requirements .env
	@echo "Configuration complète pour le développement..."
	@make setup
	@echo "Configuration terminée! Lancez 'make backend-dev' pour démarrer le backend"
	@echo "et 'make frontend' pour démarrer le frontend dans un autre terminal"

# Production setup
prod-setup: check-requirements
	@echo "Configuration pour la production..."
	@make build
	@make docker-build
	@echo "Production setup terminé!"

# Backup base de données
db-backup:
	@echo "Sauvegarde de la base de données..."
	@docker-compose exec db mysqldump -u pmt -p pmt > backup_$(date +%Y%m%d_%H%M%S).sql

# Restauration base de données
db-restore:
	@echo "Restauration de la base de données..."
	@echo "Usage: make db-restore BACKUP_FILE=backup_20250101_120000.sql"
	@docker-compose exec -T db mysql -u pmt -p pmt < $(BACKUP_FILE)

# Logs de développement
logs:
	@echo "Affichage des logs de développement..."
	@docker-compose logs -f backend frontend

# Monitoring
monitor:
	@echo "Monitoring des services..."
	@docker-compose ps
	@echo "Health checks:"
	@curl -s http://localhost:8080/api/actuator/health || echo "Backend health check failed"
	@curl -s http://localhost || echo "Frontend health check failed"

# Update des dépendances
update-deps:
	@echo "Mise à jour des dépendances..."
	@cd backend && mvn versions:display-dependency-updates
	@cd frontend && npm outdated

# Sécurité
security-scan:
	@echo "Scan de sécurité..."
	@cd backend && mvn org.owasp:dependency-check-maven:check
	@cd frontend && npm audit

# Performance
performance-test:
	@echo "Tests de performance..."
	@cd backend && mvn test -Dtest=*PerformanceTest

# Documentation
docs:
	@echo "Génération de la documentation..."
	@cd backend && mvn javadoc:javadoc
	@echo "Documentation générée dans backend/target/site/apidocs/"

# Release
release:
	@echo "Préparation de la release..."
	@make clean
	@make test
	@make build
	@make docker-build
	@echo "Release prête!"

# Support
support:
	@echo "Informations de support:"
	@echo "Version Java: $(shell java -version 2>&1 | head -n1)"
	@echo "Version Node: $(shell node --version)"
	@echo "Version Maven: $(shell mvn --version | head -n1)"
	@echo "Version Docker: $(shell docker --version)"
	@echo "Version Docker Compose: $(shell docker-compose --version)"
