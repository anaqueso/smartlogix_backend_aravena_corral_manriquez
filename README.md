# SmartLogix - Backend

Repositorio backend del proyecto SmartLogix.  
Integrantes: Catrina Corral · Anais Aravena · Fernanda Manríquez  
Asignatura: Desarrollo Fullstack III · DuocUC



## Descripción del proyecto

SmartLogix es una plataforma de gestión logística. Este repositorio contiene los componentes backend construidos con arquitectura de microservicios usando Spring Boot y Maven.



## Arquitectura


smartlogix-backend/
├── apigateway/          → BFF (Backend For Frontend) - maneja autenticación JWT y enrutamiento
└── serviciousuarios/    → Microservicio de gestión de usuarios
```



## Tecnologías

- Java 17
- Spring Boot 3.x
- Maven
- JWT (JSON Web Tokens)
- Spring Security



## Requisitos previos

- Java 17 o superior instalado
- Maven instalado (o usar el `mvnw` incluido)
- Git

Verificar versiones:
```bash
java -version
mvn -version
```



## Cómo ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/anaqueso/smartlogix-backend-corral-aravena-manriquez.git
cd smartlogix-backend-corral-aravena-manriquez
```

### 2. Ejecutar el microservicio de usuarios

```bash
cd serviciousuarios
./mvnw spring-boot:run
```

El servicio queda disponible en: `http://localhost:8081`

### 3. Ejecutar el API Gateway (BFF)

Abrir una nueva terminal:

```bash
cd apigateway
./mvnw spring-boot:run
```

El gateway queda disponible en: `http://localhost:8080`

>  Iniciar primero `serviciousuarios` y luego `apigateway`.


## Endpoints principales

### API Gateway (puerto 8080)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/auth/login` | Iniciar sesión, retorna token JWT |
| GET | `/usuarios` | Listar usuarios (requiere token) |
| POST | `/usuarios` | Crear usuario |



## Estrategia de branching (GitHub Flow)

Cada integrante trabaja en su propia rama y hace PR a main:

| Integrante | Rama | Responsabilidad |
|------------|------|-----------------|
| Catrina Corral | `feature/apigateway-catrina` | API Gateway / BFF |
| Anais Aravena | `feature/serviciousuarios-anais` | Microservicio usuarios |
| Fernanda Manríquez | `feature/docs-fer` | Documentación y configuración |

Flujo de trabajo:
1. Crear rama desde main
2. Trabajar y hacer commits descriptivos
3. Push a GitHub
4. Crear Pull Request
5. Otra integrante revisa y aprueba
6. Merge a main
7. Eliminar rama



## Equipo

| Nombre | GitHub |
|--------|--------|
| Catrina Corral | @CatrinaCorral |
| Anais Aravena | @anaqueso |
| Fernanda Manríquez | @FernandaManr |
