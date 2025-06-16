# FrontStore - Aplicación de Tienda en Línea para Android

**Autores:**
- José de Jesús Almanza Contreras
- Pablo Emilio Alonso Romero
- Leonardo Gael Durán Torres
- Victor Hassiel Avila Monjaraz

## Descripción del Proyecto

FrontStore es una aplicación de tienda en línea para Android desarrollada con tecnologías modernas como Jetpack Compose, Kotlin, y siguiendo una arquitectura limpia (Clean Architecture). La aplicación permite a los usuarios explorar productos, crear una cuenta, iniciar sesión, gestionar un carrito de compras y realizar pedidos.

## Funcionalidades Principales

- **Autenticación de usuarios**: Registro e inicio de sesión
- **Catálogo de productos**: Visualización de artículos por categorías
- **Gestión de carrito**: Añadir, eliminar y modificar productos en el carrito
- **Procesamiento de pedidos**: Creación y seguimiento de pedidos
- **Perfil de usuario**: Visualización y edición de información personal
- **Búsqueda y filtrado**: Funcionalidad para encontrar productos específicos

## Arquitectura y Tecnologías

### Arquitectura

El proyecto sigue el patrón de arquitectura Clean Architecture, separando la aplicación en tres capas principales:

1. **Capa de Presentación**: Contiene las vistas (Compose UI), ViewModels y estados de UI
2. **Capa de Dominio**: Contiene la lógica de negocio, casos de uso y entidades
3. **Capa de Datos**: Gestiona el acceso a datos, incluyendo API REST y almacenamiento local

### Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal
- **Jetpack Compose**: Framework moderno para el desarrollo de interfaces de usuario
- **Hilt**: Para la inyección de dependencias
- **Retrofit**: Cliente HTTP para la comunicación con la API REST
- **Kotlin Serialization**: Para la serialización/deserialización de JSON
- **DataStore**: Para el almacenamiento persistente de datos del usuario
- **Navigation Compose**: Para la navegación entre pantallas
- **Coil**: Para la carga eficiente de imágenes

## Proceso de Desarrollo

### 1. Planificación y Diseño

- Definición de requisitos funcionales y no funcionales
- Diseño de la arquitectura de la aplicación
- Creación de mockups de la interfaz de usuario
- Definición de modelos de datos y entidades

### 2. Configuración del Proyecto

- Configuración del proyecto en Android Studio
- Integración de dependencias y librerías
- Configuración de Gradle y gestión de versiones
- Implementación de la estructura básica siguiendo Clean Architecture

### 3. Desarrollo del Backend y API

- Creación de la API REST para la comunicación con el servidor
- Definición de endpoints y contratos de datos
- Implementación de servicios y repositorios

### 4. Desarrollo de la Interfaz de Usuario

- Implementación de componentes Compose reutilizables
- Desarrollo de pantallas principales: inicio, catálogo, perfil, carrito
- Implementación de la navegación entre pantallas

### 5. Integración y Pruebas

- Integración de las diferentes capas de la aplicación
- Pruebas unitarias de componentes críticos
- Pruebas de integración de flujos principales
- Pruebas de interfaz de usuario

### 6. Optimización y Refinamiento

- Mejoras de rendimiento
- Corrección de bugs y problemas reportados
- Optimización del consumo de recursos
- Mejoras en la experiencia de usuario

## Estructura del Proyecto

```
com.example.frontstore/
├── App.kt                  # Aplicación principal y configuración
├── MainActivity.kt         # Actividad principal que contiene la UI
├── data/                   # Capa de datos
│   ├── model/              # DTOs y modelos de datos
│   ├── remote/             # API y servicios remotos
│   ├── repository/         # Implementaciones de repositorios
│   └── UserPreferences.kt  # Gestión de preferencias del usuario
├── di/                     # Módulos de inyección de dependencias
├── domain/                 # Capa de dominio (lógica de negocio)
├── presentation/           # ViewModels y estados de UI
└── ui/                     # Componentes y pantallas Compose
```

## Retos y Soluciones

### Retos Enfrentados

1. **Implementación de la arquitectura Clean con Compose**: Adaptación del patrón MVVM con Jetpack Compose manteniendo la separación de responsabilidades.
   
   *Solución*: Uso de estados inmutables y flujo unidireccional de datos.

2. **Gestión del estado de la aplicación**: Mantener el estado consistente entre diferentes pantallas.
   
   *Solución*: Uso de StateFlow y SharedViewModel donde fue necesario.

3. **Optimización de la carga de imágenes**: Manejo eficiente de la carga de imágenes en listas y grids.
   
   *Solución*: Implementación de Coil con técnicas de carga diferida y caché.

4. **Seguridad en la autenticación**: Almacenamiento seguro de tokens y credenciales.
   
   *Solución*: Uso de DataStore encriptado para credenciales sensibles.

### Lecciones Aprendidas

- La importancia de una buena planificación y división clara de responsabilidades
- Beneficios de la inyección de dependencias para el testing y la modularidad
- Ventajas de Jetpack Compose para el desarrollo ágil de interfaces de usuario
- Valor de seguir las mejores prácticas de Clean Architecture para proyectos escalables

## Instalación y Configuración

1. Clonar el repositorio:
   ```
   git clone [URL_del_repositorio]
   ```

2. Abrir el proyecto en Android Studio

3. Sincronizar el proyecto con los archivos Gradle

4. Ejecutar la aplicación en un dispositivo o emulador con API 29 o superior

## Planes Futuros

- Implementación de análisis y seguimiento con Firebase Analytics
- Soporte para notificaciones push
- Modo oscuro y temas personalizables
- Optimización para tablets y dispositivos plegables
- Integración con más métodos de pago

---

© 2025 FrontStore - Todos los derechos reservados
