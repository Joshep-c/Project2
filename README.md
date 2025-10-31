
---

#  ShopPly
 – Catálogo de Productos para Tiendas

🔗 **Repositorio público:**
[https://github.com/Joshep-c/ShopPly.git](https://github.com/Joshep-c/ShopPly.git)

---

## Resumen

ShopPly
 es una aplicación móvil desarrollada en **Kotlin** para Android cuyo objetivo es permitir que pequeños y medianos comercios puedan digitalizar su catálogo de productos de manera rápida, económica y sin necesidad de plataformas complejas.

La aplicación permite que un usuario pueda:

* Navegar por un catálogo de productos.
* Visualizar detalles como imagen, precio, descripción y stock.
* Guardar productos favoritos.
* Recibir notificaciones de promociones o descuentos.

Su enfoque se basa en la **simplicidad, velocidad y experiencia visual clara**, diferenciándose de otras aplicaciones comerciales saturadas o complejas para el usuario final.

---

## Análisis de Aplicaciones Similares (Entrega 1)

Para el diseño del proyecto se analizaron tres aplicaciones existentes con propósitos similares:

### Amazon Shopping

* Permite buscar, comparar y comprar productos en múltiples categorías.
* Filtros avanzados, listas de deseos, seguimiento de pedidos.
* Integración con Amazon Pay, Alexa y funciones de inteligencia artificial.

### Shein

* Plataforma enfocada en moda y accesorios.
* Filtros por talla, color, estilo; reseñas con fotos reales.
* Alta presencia de promociones, dinámicas y notificaciones constantes.

### Yape Tienda

* Marketplace dentro de la app Yape (Perú).
* Pago integrado mediante la wallet Yape.
* Filtros básicos, categorías y seguimiento de compras realizado.

### Tabla comparativa

| Criterio               | Amazon Shopping                         | Shein                                | Yape Tienda                      |
| ---------------------- | --------------------------------------- | ------------------------------------ | -------------------------------- |
| Usabilidad             | Completa pero saturada                  | Intuitiva y rápida                   | Extremadamente simple            |
| Diseño visual          | Minimalista                             | Visual, social y atractivo           | Sencillo y directo               |
| Funcionalidades        | Catálogo avanzado, reseñas, seguimiento | Promociones, fotos reales, favoritos | Pago integrado y filtros básicos |
| Persistencia de datos  | Historial completo y personalización    | Favoritos e historial                | Historial de compras             |
| Experiencia de usuario | Personalización avanzada                | Atractiva y orientada a tendencias   | Práctica, rápida y enfocada      |

---

## Propuesta de la Aplicación (Proyecto)

La aplicación propuesta consiste en una plataforma móvil orientada a la gestión de un catálogo de productos para pequeños y medianos comercios, permitiendo mostrar productos con detalles claros (imagen, precio y disponibilidad) y ofreciendo al usuario una experiencia visual simple.

**Problema identificado:**
Los pequeños comercios no cuentan con una herramienta simple para mostrar sus productos sin recurrir a plataformas complejas como Amazon o Shein.

**Valor agregado de ShopPly:**

| Aspecto         | Valor diferencial                                |
| --------------- | ------------------------------------------------ |
| Simplicidad     | Interfaz ligera y navegación intuitiva           |
| Catálogo visual | Fichas de producto con imágenes en buena calidad |
| Persistencia    | Lista de favoritos e historial básico            |
| Notificaciones  | Relevantes y no invasivas                        |
| Enfoque local   | Adaptada a comercios de la comunidad             |

---

## Interfaces Implementadas

| Interfaz / Pantalla | Propósito                         | Comportamiento                                        |
| ------------------- | --------------------------------- | ----------------------------------------------------- |
| `MainActivity`      | Punto de inicio de la app         | Gestiona la navegación hacia las demás pantallas.     |
| `LoginFragment`     | Autenticación                     | Valida credenciales y permite el ingreso al catálogo. |
| `DashboardFragment` | Catálogo de productos             | Lista productos con información general.              |
| `DetailActivity`    | Detalle del producto seleccionado | Muestra descripción, imagen, precio y disponibilidad. |
| `FavoritesFragment` | Persistencia local                | Permite guardar productos como favoritos.             |
| `SettingsFragment`  | Configuración                     | Permite administrar preferencias del usuario.         |

---

## Instrucciones para ejecutar el proyecto

### Requisitos

| Tecnología     | Versión requerida   |
| -------------- | ------------------- |
| Android Studio | 2023.1.1 o superior |
| JDK            | 17                  |
| SDK mínima     | API 21              |
| Lenguaje       | Kotlin              |

### Pasos para ejecutar

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Joshep-c/ShopPly.git
   ```
2. Abrir Android Studio → **Open an existing project** → seleccionar `ShopPly`.
3. Esperar la sincronización automática de Gradle.
4. Conectar un dispositivo físico o elegir un emulador.
5. Presionar **Run (Shift + F10)** para ejecutar la app.

---
