# PMT - Project Management Tool

Une plateforme de gestion de projet collaborative développée pour les équipes de développement logiciel.

## 🚀 Fonctionnalités

- **Gestion des utilisateurs** : Authentification et profils utilisateurs
- **Gestion des équipes** : Création et gestion d'équipes de travail
- **Gestion des projets** : Planification, suivi et collaboration sur des projets
- **Gestion des tâches** : Création, assignation et suivi des tâches
- **Système de commentaires** : Communication sur projets et tâches
- **Gestion des pièces jointes** : Upload et organisation de fichiers
- **Tableaux de bord** : Vue d'ensemble des projets et activités

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework** : Spring Boot 3.2.0
- **Base de données** : MySQL / H2 (développement)
- **ORM** : Spring Data JPA
- **Documentation** : OpenAPI/Swagger
- **Tests** : JUnit 5, Mockito, Testcontainers
- **Coverage** : JaCoCo (≥60% requis)

### Frontend (Angular)
- **Framework** : Angular 17
- **UI** : Angular Material
- **Routing** : Lazy loading
- **Tests** : Jasmine, Karma
- **Coverage** : ≥60% requis

### DevOps
- **Containerisation** : Docker
- **Orchestration** : Docker Compose
- **CI/CD** : GitHub Actions
- **Monitoring** : Health checks

## 📋 Prérequis

- Java 17+
- Node.js 18+
- MySQL 8.0+
- Docker & Docker Compose
- Maven 3.6+

## 🚀 Installation et Déploiement

### 1. Clonage du repository

```bash
git clone <repository-url>
cd projetpmt
```

### 2. Configuration de la base de données

Créez une base de données MySQL et mettez à jour les configurations dans `backend/src/main/resources/application.yml` :

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pmt
    username: pmt
    password: your_password
```

### 3. Démarrage avec Docker Compose (Recommandé)

```bash
# Construction et démarrage de tous les services
docker-compose up --build

# Ou en arrière-plan
docker-compose up -d --build
```

Services disponibles :
- **Backend** : http://localhost:8080
- **Frontend** : http://localhost:80
- **MySQL** : localhost:3306
- **H2 Console** : http://localhost:8080/api/h2-console

### 4. Démarrage manuel

#### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### Frontend
```bash
cd frontend
npm install
npm start
```

## 🧪 Tests

### Tests Backend
```bash
cd backend
mvn test
mvn test jacoco:report  # Rapport de couverture
```

### Tests Frontend
```bash
cd frontend
npm test
npm run test:ci  # Tests en CI
```

## 📊 Couverture de Code

Le projet respecte les exigences de couverture :
- **Backend** : ≥60% (instructions et branches)
- **Frontend** : ≥60% (instructions et branches)

Génération des rapports :
```bash
# Backend
cd backend && mvn jacoco:report

# Frontend
cd frontend && npm run test:ci
```

## 🐳 Docker

### Construction des images

```bash
# Backend
docker build -f Dockerfile.backend -t codesolutions/pmt-backend:latest .

# Frontend
docker build -f Dockerfile.frontend -t codesolutions/pmt-frontend:latest .
```

### Variables d'environnement

#### Backend
- `SPRING_PROFILES_ACTIVE` : Profil Spring (dev/prod)
- `SPRING_DATASOURCE_URL` : URL de la base de données
- `SPRING_DATASOURCE_USERNAME` : Utilisateur DB
- `SPRING_DATASOURCE_PASSWORD` : Mot de passe DB

#### Frontend
- `API_URL` : URL de l'API backend

## 🚀 CI/CD

Le projet utilise GitHub Actions pour :
- Tests automatisés (backend & frontend)
- Analyse de couverture de code
- Construction et publication d'images Docker
- Déploiement automatique

### Secrets requis
- `DOCKER_USERNAME` : Nom d'utilisateur Docker Hub
- `DOCKER_PASSWORD` : Mot de passe Docker Hub

## 📁 Structure du Projet

```
projetpmt/
├── backend/                 # Application Spring Boot
│   ├── src/
│   │   ├── main/java/com/codesolutions/pmt/
│   │   │   ├── controller/  # Contrôleurs REST
│   │   │   ├── dto/         # DTOs
│   │   │   ├── model/       # Entités JPA
│   │   │   ├── repository/  # Repositories
│   │   │   ├── service/     # Services
│   │   │   └── config/      # Configuration
│   │   └── test/           # Tests
│   └── pom.xml
├── frontend/               # Application Angular
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/ # Composants réutilisables
│   │   │   ├── pages/      # Pages/routes
│   │   │   ├── services/   # Services API
│   │   │   └── models/     # Interfaces TypeScript
│   ├── assets/            # Ressources statiques
│   └── environments/      # Configuration environnement
├── database/              # Scripts SQL
├── docker-compose.yml     # Orchestration Docker
├── Dockerfile.backend     # Image backend
├── Dockerfile.frontend    # Image frontend
└── .github/workflows/     # Pipelines CI/CD
```

## 🔧 Configuration

### Ports par défaut
- Frontend : 80
- Backend : 8080
- MySQL : 3306

### Logs
- Backend : `backend/logs/`
- Frontend : Console du navigateur
- Docker : `docker-compose logs`

## 🤝 Contribution

1. Fork le projet
2. Créez une branche feature (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📝 User Stories Implémentées

### Utilisateurs
- [x] Création et gestion des comptes utilisateurs
- [x] Authentification et autorisation
- [x] Profils utilisateurs avec avatars

### Équipes
- [x] Création d'équipes
- [x] Gestion des membres d'équipe
- [x] Rôles et permissions

### Projets
- [x] Création et gestion de projets
- [x] Assignation d'équipes aux projets
- [x] Statuts de projet (Planning, In Progress, etc.)

### Tâches
- [x] Création et assignation de tâches
- [x] Statuts et priorités
- [x] Dates d'échéance et estimations

### Collaboration
- [x] Système de commentaires
- [x] Pièces jointes
- [x] Notifications d'activité

## 🔐 Sécurité

- Authentification JWT
- Validation des entrées
- Protection CSRF
- Headers de sécurité
- Logs d'audit

## 📈 Monitoring

- Health checks automatiques
- Métriques d'application
- Logs centralisés
- Alertes de performance

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👥 Équipe

- **CEO** : John Doe
- **Product Owner** : Nicolas
- **Tech Lead** : Mariana
- **Développeur** : [Votre nom]

## 📞 Support

Pour toute question ou problème :
- Email : support@codesolutions.com
- Issues : [GitHub Issues](https://github.com/codesolutions/pmt/issues)
- Documentation : [Wiki](https://github.com/codesolutions/pmt/wiki)

---

**PMT** - Plateforme de gestion de projet collaborative pour équipes de développement.
