# Smart Gallery App

Esta es una aplicación de galería de fotos nativa para Android, construida con tecnologías modernas. El objetivo es crear una galería inteligente, bien arquitecturada y escalable.

Este proyecto es la primera fase, que sienta las bases de la aplicación.

## Características Actuales (Fase 1)

*   **Arquitectura Limpia + MVVM**: El código está organizado en capas (UI, Domain, Data) para una máxima mantenibilidad y escalabilidad.
*   **Jetpack Compose y Material 3**: La interfaz de usuario está construida de forma declarativa con el moderno toolkit de UI de Android.
*   **Visualización de Galería Local**: La aplicación solicita permisos para leer el almacenamiento multimedia y muestra las fotos del dispositivo en una cuadrícula.
*   **Manejo de Permisos**: Gestiona de forma robusta los permisos `READ_MEDIA_IMAGES` (Android 13+) y `READ_EXTERNAL_STORAGE` (versiones anteriores).
*   **Carga Asíncrona de Imágenes**: Utiliza la librería [Coil](https://coil-kt.github.io/coil/) para cargar imágenes de forma eficiente.

## Cómo Compilar y Ejecutar

1.  **Clonar el Repositorio**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    ```
2.  **Abrir en Android Studio**
    *   Abre Android Studio (versión Iguana o superior recomendada).
    *   Selecciona `File > Open` y elige la carpeta del proyecto clonado.
    *   Android Studio se encargará de sincronizar el proyecto con Gradle automáticamente.

3.  **Ejecutar la Aplicación**
    *   Selecciona un emulador o un dispositivo físico.
    *   Presiona el botón `Run 'app'` (o `Shift + F10`).
    *   La aplicación se instalará y se ejecutará. Te pedirá permiso para acceder a tus fotos.

## Futuras Funcionalidades (Próximas Fases)

La arquitectura actual está diseñada para facilitar la adición de nuevas características complejas, tales como:

*   **Reconocimiento Facial**: Agrupar fotos por personas usando ML Kit.
*   **Búsqueda Inteligente**: Búsqueda por objetos o texto dentro de las imágenes (OCR).
*   **Integración con la Nube**: Sincronización con Google Drive, OneDrive, etc.
*   **Carpeta Privada**: Una sección segura y cifrada.

### Configuración de APIs en la Nube (Platzhalter)

Cuando se implemente la integración con la nube, deberás obtener tus propias claves de API y configurarlas aquí.

*   **Google Drive**:
    1.  Ve a la [Consola de APIs de Google](https://console.developers.google.com/).
    2.  Crea un nuevo proyecto y activa la API de Google Drive.
    3.  Crea credenciales de tipo "OAuth 2.0 Client ID" para una aplicación de Android.
    4.  Añade las credenciales al archivo `local.properties` (este archivo no debe ser subido al control de versiones).

*   **OneDrive / Dropbox**:
    *   El proceso será similar, siguiendo la documentación oficial de cada plataforma para desarrolladores.