# Web Consultar Fecha - Frontend

Una página web simple para consultar si una fecha es feriado.

## Descripción

Este proyecto es un frontend web que permite a los usuarios:
- Ingresar una fecha
- Ingresar una cantidad de días
- Consultar si la fecha es feriado
- Ver un cuadro de diálogo con el resultado

## Características

- ✨ Interfaz moderna y responsiva
- 🎨 Diseño atractivo con gradientes y animaciones
- 📱 Compatible con dispositivos móviles
- 🔌 Conecta con el endpoint `/consultarFecha` del microservicio consultarfecha
- ⚡ Validaciones en cliente
- 🎯 Manejo de errores intuitivo

## Uso

### Requisitos
- Un navegador web moderno (Chrome, Firefox, Safari, Edge)
- El microservicio `consultarfecha` ejecutándose en `http://localhost:8081`

### Instrucciones

1. **Abrir la página web**
   - Abre el archivo `index.html` en tu navegador
   - O sirve los archivos con un servidor web (Python, Node.js, etc.)

2. **Completar el formulario**
   - Ingresa una fecha en el campo "Fecha"
   - Ingresa la cantidad de días (opcional, por defecto 0)

3. **Consultar**
   - Haz clic en el botón "Consultar"
   - Espera a que se procese la solicitud

4. **Ver resultado**
   - Se mostrará un cuadro de diálogo indicando si la fecha es feriado o no

## Estructura del proyecto

```
web-consultarfecha/
├── index.html       # Estructura HTML de la página
├── styles.css       # Estilos y diseño
├── script.js        # Lógica de la aplicación
└── README.md        # Este archivo
```

## API

El proyecto conecta con el endpoint:

**URL:** `http://localhost:8081/consultarFecha`
**Método:** POST
**Content-Type:** application/json

### Payload

```json
{
  "fecha": "2024-01-01",
  "cantidadDias": 0
}
```

### Respuesta esperada

```json
{
  "esFeriado": true,
  "nombre": "Año Nuevo",
  "dia": 1,
  "mes": 1,
  "año": 2024
}
```

## Notas técnicas

- El campo de fecha usa el formato ISO (YYYY-MM-DD)
- La cantidad de días es un número entero (puede ser 0 o mayor)
- Los errores de red se muestran en un modal
- La página es completamente funcional sin necesidad de compilación

## Servir la aplicación

### Opción 1: Usar Python
```bash
# Python 3
python -m http.server 8080

# Python 2
python -m SimpleHTTPServer 8080
```

### Opción 2: Usar Node.js
```bash
npx http-server
```

### Opción 3: Usar Live Server en VS Code
- Instala la extensión "Live Server"
- Haz clic derecho en `index.html` → "Open with Live Server"
