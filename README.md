# Food Store – Sistema de Gestión de Pedidos

Trabajo Práctico Integrador – Programación 2  
UTN – Tecnicatura Universitaria en Programación  
Año 2026

---
## Descripción

Sistema de gestión de pedidos de comida desarrollado en Java 21, orientado a objetos, con interfaz de consola. Permite administrar categorías, productos, usuarios y pedidos mediante un menú CRUD completo. Toda la información se almacena en memoria durante la ejecución mediante Colecciones.

---
## Estructura del proyecto

```
tpi-proga2/
├── Main.java
├── clases/
│   ├── Base.java
│   ├── Categoria.java
│   ├── Producto.java
│   ├── Usuario.java
│   ├── Pedido.java
│   └── DetallePedido.java
├── enums/
│   ├── Rol.java
│   ├── Estado.java
│   └── FormaPago.java
├── exceptions/
│   ├── EntidadNoEncontradaException.java
│   └── StockInvalidoException.java
└── interfaces/
    └── Calculable.java
```

---
## Funcionalidades

- **Categorías** – Listar, crear (sin duplicados), editar y eliminar (baja lógica con confirmación)

- **Productos** – Listar, crear (asociado a categoría), editar y eliminar (baja lógica con confirmación)

- **Usuarios** – Listar, crear (mail único), editar y eliminar (baja lógica con confirmación)

- **Pedidos** – Listar, crear con detalles (productos y cantidades), editar estado/forma de pago y eliminar (baja lógica con confirmación)

---
## Video demostrativo

https://www.youtube.com/watch?v=wKrYzXt-Erw

---
## Documentación PDF

https://drive.google.com/file/d/1X4-Tgc9tRdv-lecnJbLIbrgGBV1NfPyP/view?usp=drive_link

---
## Autor

Nombre: Ignacio Beltramino Martinez
Grupo: 76
