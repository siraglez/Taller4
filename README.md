# Taller4
 
Link al repositorio: https://github.com/siraglez/Taller4.git

## Descripción General

Esta aplicación Android está diseñada para explorar varias funcionalidades avanzadas que enriquecen la experiencia del usuario. Se basa en una arquitectura modular utilizando fragmentos, sensores y widgets, y permite una interacción dinámica y personalizada.

## Funcionalidades

### 1. Pantalla de Inicio
- **MainActivity**: Muestra un saludo personalizado que varía según la hora del día (Buenos días, Buenas tardes o Buenas noches) y un botón que lleva al usuario a la pantalla principal de la aplicación.

### 2. Pantalla Principal
- **ActividadPrincipal**: Implementa una interfaz dividida en dos fragmentos:
  - **ListaFragment**: Muestra una lista de elementos (título y descripción). Los usuarios pueden añadir elementos, los cuales se guardan localmente, y seleccionar uno para ver más detalles.
  - **DetalleFragment**: Muestra los detalles del elemento seleccionado en la lista. Incluye un botón para cerrar el fragmento.

### 3. Interacción con Sensores
- **SensorActivity**: Utiliza el acelerómetro del dispositivo para detectar movimientos bruscos. Cuando se detecta un movimiento, cambia el color de fondo de la pantalla. También incluye un botón para volver a la pantalla principal.

### 4. Widget de Aplicación
- **WidgetProvider**: Proporciona un widget para la pantalla de inicio. Este muestra los elementos de la lista y permite actualizarlos con un botón. La información del widget se sincroniza con la lista almacenada en las preferencias compartidas de la aplicación.

## Detalles Técnicos

### 1. Gestión de Fragmentos
- La **ActividadPrincipal** maneja la comunicación entre `ListaFragment` y `DetalleFragment`. Al seleccionar un elemento en la lista, los detalles se pasan dinámicamente al fragmento de detalle.

### 2. Persistencia de Datos
- Los elementos de la lista se almacenan en las **SharedPreferences** del dispositivo para asegurar que la información persista entre sesiones.

### 3. Sensores
- La **SensorActivity** utiliza el **acelerómetro** para detectar movimientos basándose en la magnitud del vector de aceleración. Si el movimiento supera un umbral, el fondo cambia de color.

### 4. Widget
- El widget obtiene su información desde las **SharedPreferences**, asegurando la sincronización con la lista de elementos de la aplicación principal.

## Uso
1. **Pantalla de Inicio**:
   - Observa el saludo personalizado basado en la hora del día.
   - Haz clic en "Ir a Actividad Principal" para acceder a las funcionalidades principales.

2. **Pantalla Principal**:
   - Añade nuevos elementos ingresando un título y una descripción.
   - Haz clic en un elemento de la lista para ver más detalles.
   - Usa el botón "Sensor" para explorar la actividad interactiva basada en el acelerómetro.

3. **SensorActivity**:
   - Mueve el dispositivo para ver cambios dinámicos en el color del fondo.
   - Haz clic en "Volver" para regresar a la pantalla principal.

4. **Widget**:
   - Consulta la lista de elementos desde la pantalla de inicio del dispositivo.
   - Usa el botón del widget para actualizar la información mostrada.
