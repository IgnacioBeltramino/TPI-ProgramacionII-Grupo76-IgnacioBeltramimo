import java.util.ArrayList;
import java.util.Scanner;

import clases.Categoria; 
import clases.Producto;
import clases.Usuario;
import clases.Pedido;

public class Main {

    // una lista por cada clase
    static ArrayList<Categoria> categorias = new ArrayList<>(); 
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();

    // UTILIDADES DE CONSOLA
    static void limpiarPantalla() { // metodo creado para mejorar la vista de la consola 
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("\n\n\n");
        }
    }

    static void volverAlMenu() { // metodo creado para facilitar la vuelta al menu 
        System.out.print("Presione ENTER para volver al menu...");
        scanner.nextLine();
        limpiarPantalla();
    }

    static int leerOpcion() { // devuelve -1 si es algo que no es una opcion 
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // TODO LO RELACIONADO A CATEGORIAS
    static void listarCategorias() { // esto valida que no esten todas las categorias eliminadas / exista alguna 
        System.out.println("== Lista de categorias ==");
        System.out.println("=======================================");
        boolean hayAlguna = false; 
        for (Categoria c : categorias) {
            if (!c.isEliminado()) {
                System.out.println(c);
                System.out.println("=======================================");
                hayAlguna = true;
            }
        }
        if (!hayAlguna) {
            System.out.println("No existen categorias aun.");
            System.out.println("=======================================");
        }
    }

    static void crearCategoria() { // validaciones en cada una de los campos 
        limpiarPantalla();
        System.out.println("== Creacion de categoria ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            volverAlMenu();
            return;
        }
        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("La descripcion no puede estar vacia.");
            volverAlMenu();
            return;
        }
        for (Categoria c : categorias) { // validacion de que no exista una categoria con ese nombre 
            if (c.getNombre().equalsIgnoreCase(nombre) && !c.isEliminado()) {
                System.out.println("Ya existe una categoria con ese nombre.");
                volverAlMenu();
                return;
            }
        }
        Categoria nueva = new Categoria(nombre, descripcion);
        categorias.add(nueva); // agrega la cateogoria a la lista 
        System.out.println("=======================================");
        System.out.println("Categoria creada correctamente.");
        System.out.println("=======================================");
        volverAlMenu();
    }

    static void editarCategoria(){ // muestra toda las categorias y permite elegir al usuario cual quiere editar 
        limpiarPantalla();
        System.out.println("== Editar categorias ==");
        listarCategorias();
        System.out.print("Ingrese el id de la categoria a editar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Categoria encontrada = null;

        for (Categoria c : categorias) {
            if (c.getId().equals(id) && !c.isEliminado()) {
                encontrada = c;
            }
        }

        try {
            if (encontrada == null) {
                throw new exceptions.EntidadNoEncontradaException("Categoria con id " + id + " no encontrada.");
            }
            System.out.print("Nuevo nombre (Enter para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                encontrada.setNombre(nuevoNombre);
            }
            System.out.print("Nueva descripcion (Enter para mantener): ");
            String nuevaDesc = scanner.nextLine();
            if (!nuevaDesc.isEmpty()) {
                encontrada.setDescripcion(nuevaDesc);
            }
            System.out.println("Categoria editada correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    static void eliminarCategoria() {
        limpiarPantalla();
        System.out.println("== Elminar categoria ==");
        listarCategorias();
        System.out.print("Ingrese el id de la categoria que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido."); // valida que se le pase un id que existe 
            volverAlMenu();
            return;
        }
        
        Categoria encontrada = null;
        for (Categoria c : categorias) {
            if (c.getId().equals(id) && !c.isEliminado()) {
                encontrada = c;
            }
        }
        try {
            if (encontrada == null) {
                throw new exceptions.EntidadNoEncontradaException("Categoria con id " + id + " no encontrada."); // excepcion creada como parte de la consigna 
            }
            System.out.print("¿Esta seguro que desea eliminar \"" + encontrada.getNombre() + "\"? (S/N): "); // consulta como pide la consigna 
            String confirmacion = scanner.nextLine().trim();
            if (!confirmacion.equalsIgnoreCase("S")) {
                System.out.println("Operacion cancelada.");
                volverAlMenu();
                return;
            }
            encontrada.setEliminado(true);
            System.out.println("Categoria eliminada correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    // menu categoria
    static void menuCategorias() {
        boolean en_ejeccuionMenu = true;
        while (en_ejeccuionMenu){
            limpiarPantalla();
            System.out.println("\n=== MENU CATEGORIAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.println("=======================");
            System.out.print("Seleccione: ");
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> { limpiarPantalla(); listarCategorias(); volverAlMenu(); }
                case 2 -> crearCategoria();
                case 3 -> editarCategoria();
                case 4 -> eliminarCategoria();
                case 0 -> en_ejeccuionMenu = false;
                default -> { System.out.println("Opcion invalida, ingresa una opcion correcta por favor."); volverAlMenu(); } // CORRECCIÓN: volverAlMenu() para que el mensaje no desaparezca
            }

        }
    }

    // METODOS - ATRIBUTOS RELACIONADOS A PRODUCTOS

    // funciones dentro del menu categoria
    static void listarProductos() {
        System.out.println("== Lista de productos ==");
        System.out.println("=======================================");
        boolean hayAlguno = false;
        for (Producto p : productos) {
            if (!p.isEliminado()) {
                System.out.println(p);
                System.out.println("=======================================");
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("No existen productos aun.");
            System.out.println("=======================================");
        }
    }

    static void crearProducto() {
        limpiarPantalla();
        System.out.println("== Creacion de producto ==");
        listarCategorias();
        boolean hayCategoria = categorias.stream().anyMatch(c -> !c.isEliminado()); // busco una categoria que no este eliminada, devuelve true si todos estan eliminados o la lista esta vacia
        if (!hayCategoria) {
            System.out.println("Debe existir al menos una categoria para crear un producto.");
            volverAlMenu();
            return;
        }
        System.out.print("Ingrese el id de la categoria a la que pertenece el producto nuevo: ");
        Long idCategoria;
        try {
            idCategoria = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Categoria categoriaEncontrada = null;
        for (Categoria c : categorias) {
            if (c.getId().equals(idCategoria) && !c.isEliminado()) {
                categoriaEncontrada = c;
            }
        }

        if (categoriaEncontrada == null) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            volverAlMenu();
            return;
        }

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine().trim();
        if (descripcion.isEmpty()) {
            System.out.println("La descripcion no puede estar vacia.");
            volverAlMenu();
            return;
        }

        System.out.print("Precio: ");
        double precio;
        try {
            precio = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El precio ingresado no es valido.");
            volverAlMenu();
            return;
        }

        System.out.print("Stock: ");
        int stock;
        try {
            stock = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El stock ingresado no es valido.");
            volverAlMenu();
            return;
        }

        System.out.print("Imagen: ");
        String imagen = scanner.nextLine().trim();

        System.out.print("Disponible (true/false): ");
        String dispInput = scanner.nextLine().trim().toLowerCase();
        if (!dispInput.equals("true") && !dispInput.equals("false")) {
            System.out.println("Valor invalido. Ingrese true o false.");
            volverAlMenu();
            return;
        }
        boolean disponible = dispInput.equals("true");

        try {
            if (precio < 0 || stock < 0) {
                throw new exceptions.StockInvalidoException("El precio y el stock no pueden ser negativos."); // excepcion creada como parte de la consigna 
            }
            Producto nuevo = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoriaEncontrada);
            productos.add(nuevo);
            categoriaEncontrada.agregarProducto(nuevo);
            System.out.println("Producto creado correctamente.");
        } catch (exceptions.StockInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    static void editarProducto() {
        limpiarPantalla();
        System.out.println("== Editar producto ==");
        listarProductos();
        System.out.print("Ingrese el id del producto a editar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Producto encontrada = null;

        for (Producto p : productos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                encontrada = p;
            }
        }

        try {
            if (encontrada == null) {
                throw new exceptions.EntidadNoEncontradaException("Producto con id " + id + " no encontrado.");
            }
            System.out.print("Nuevo nombre (Enter para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                encontrada.setNombre(nuevoNombre);
            }
            System.out.print("Nueva descripcion (Enter para mantener): ");
            String nuevaDesc = scanner.nextLine();
            if (!nuevaDesc.isEmpty()) {
                encontrada.setDescripcion(nuevaDesc);
            }
            System.out.print("Nuevo precio (Enter para mantener): ");
            String precioInput = scanner.nextLine().trim();
            if (!precioInput.isEmpty()) {
                try {
                    double nuevoPrecio = Double.parseDouble(precioInput);
                    if (nuevoPrecio < 0) {
                        System.out.println("El precio no puede ser negativo.");
                        volverAlMenu();
                        return;
                    }
                    encontrada.setPrecio(nuevoPrecio);
                } catch (NumberFormatException ex) {
                    System.out.println("El precio ingresado no es valido.");
                    volverAlMenu();
                    return;
                }
            }
            System.out.print("Nuevo stock (Enter para mantener): ");
            String stockInput = scanner.nextLine().trim();
            if (!stockInput.isEmpty()) {
                try {
                    int nuevoStock = Integer.parseInt(stockInput);
                    if (nuevoStock < 0) {
                        System.out.println("El stock no puede ser negativo.");
                        volverAlMenu();
                        return;
                    }
                    encontrada.setStock(nuevoStock);
                } catch (NumberFormatException ex) {
                    System.out.println("El stock ingresado no es valido.");
                    volverAlMenu();
                    return;
                }
            }
            System.out.println("Producto editado correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    static void eliminarProducto() {
        limpiarPantalla();
        System.out.println("== Eliminar producto ==");
        listarProductos();
        System.out.print("Ingrese el id del producto que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Producto encontradoProd = null;
        for (Producto p : productos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                encontradoProd = p;
            }
        }
        try {
            if (encontradoProd == null) {
                throw new exceptions.EntidadNoEncontradaException("Producto con id " + id + " no encontrado.");
            }
            System.out.print("¿Esta seguro que desea eliminar \"" + encontradoProd.getNombre() + "\"? (S/N): ");
            String confirmacion = scanner.nextLine().trim();
            if (!confirmacion.equalsIgnoreCase("S")) {
                System.out.println("Operacion cancelada.");
                volverAlMenu();
                return;
            }
            encontradoProd.setEliminado(true);
            System.out.println("Producto eliminado correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    // METODOS - ATRIBUTOS RELACIONADOS A USUARIOS

    static void listarUsuarios() {
        System.out.println("== Lista de usuarios ==");
        System.out.println("=======================================");
        boolean hayAlguno = false;
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                System.out.println(u);
                System.out.println("=======================================");
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("No existen usuarios aun.");
            System.out.println("=======================================");
        }
    }

    static void crearUsuario() {
        limpiarPantalla();
        System.out.println("== Creacion de usuario ==");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            volverAlMenu();
            return;
        }

        System.out.print("Apellido: ");
        String apellido = scanner.nextLine().trim();
        if (apellido.isEmpty()) {
            System.out.println("El apellido no puede estar vacio.");
            volverAlMenu();
            return;
        }

        System.out.print("Mail: ");
        String mail = scanner.nextLine().trim();
        if (mail.isEmpty()) {
            System.out.println("El mail no puede estar vacio.");
            volverAlMenu();
            return;
        }
        for (Usuario u : usuarios) {
            if (u.getMail().equals(mail) && !u.isEliminado()) {
                System.out.println("Ya existe un usuario con ese mail.");
                volverAlMenu();
                return;
            }
        }

        System.out.print("Celular: ");
        String celular = scanner.nextLine().trim();
        if (celular.isEmpty() || !celular.chars().allMatch(Character::isDigit)) { // verifica caracter por caracter si es un numero
            System.out.println("El celular solo puede contener numeros.");
            volverAlMenu();
            return;
        }

        System.out.print("Contrasenia: ");
        String contrasenia = scanner.nextLine().trim();

        System.out.println("Rol: 1. ADMIN  2. USUARIO");
        int opcionRol;
        try {
            opcionRol = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opcion de rol invalida.");
            volverAlMenu();
            return;
        }
        if (opcionRol != 1 && opcionRol != 2) {
            System.out.println("Opcion de rol invalida. Ingrese 1 o 2.");
            volverAlMenu();
            return;
        }
        enums.Rol rol = (opcionRol == 1) ? enums.Rol.ADMIN : enums.Rol.USUARIO;

        Usuario nuevo = new Usuario(nombre, apellido, mail, celular, contrasenia, rol);
        usuarios.add(nuevo);
        System.out.println("Usuario creado correctamente.");
        volverAlMenu();
    }

    static void editarUsuario() {
        limpiarPantalla();
        System.out.println("== Editar usuario ==");
        listarUsuarios();
        System.out.print("Ingrese el id del usuario a editar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                encontrado = u;
            }
        }

        try {
            if (encontrado == null) {
                throw new exceptions.EntidadNoEncontradaException("Usuario con id " + id + " no encontrado.");
            }
            System.out.print("Nuevo nombre (Enter para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) encontrado.setNombre(nuevoNombre);

            System.out.print("Nuevo apellido (Enter para mantener): ");
            String nuevoApellido = scanner.nextLine();
            if (!nuevoApellido.isEmpty()) encontrado.setApellido(nuevoApellido);

            System.out.print("Nuevo mail (Enter para mantener): ");
            String nuevoMail = scanner.nextLine();
            if (!nuevoMail.isEmpty()) {
                for (Usuario u : usuarios) {
                    if (u.getMail().equals(nuevoMail) && !u.isEliminado()) {
                        System.out.println("Ya existe un usuario con ese mail.");
                        volverAlMenu();
                        return;
                    }
                }
                encontrado.setMail(nuevoMail);
            }

            System.out.print("Nuevo celular (Enter para mantener): ");
            String nuevoCelular = scanner.nextLine().trim();
            if (!nuevoCelular.isEmpty()) {
                if (!nuevoCelular.matches("\\d+")) {
                    System.out.println("El celular solo puede contener numeros.");
                    volverAlMenu();
                    return;
                }
                encontrado.setCelular(nuevoCelular);
            }

            System.out.println("Usuario editado correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    static void eliminarUsuario() {
        limpiarPantalla();
        System.out.println("== Eliminar usuario ==");
        listarUsuarios();
        System.out.print("Ingrese el id del usuario que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Usuario encontradoUsr = null;
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                encontradoUsr = u;
            }
        }
        try {
            if (encontradoUsr == null) {
                throw new exceptions.EntidadNoEncontradaException("Usuario con id " + id + " no encontrado.");
            }
            System.out.print("¿Esta seguro que desea eliminar a \"" + encontradoUsr.getNombre() + " " + encontradoUsr.getApellido() + "\"? (S/N): ");
            String confirmacion = scanner.nextLine().trim();
            if (!confirmacion.equalsIgnoreCase("S")) {
                System.out.println("Operacion cancelada.");
                volverAlMenu();
                return;
            }
            encontradoUsr.setEliminado(true);
            System.out.println("Usuario eliminado correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    static void menuUsuarios() {
        limpiarPantalla();
        boolean en_ejeccuionMenu = true;
        while (en_ejeccuionMenu) {
            System.out.println("\n=== MENU USUARIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.println("========================================");
            System.out.print("Seleccione: ");
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> { limpiarPantalla(); listarUsuarios(); volverAlMenu(); }
                case 2 -> crearUsuario();
                case 3 -> editarUsuario();
                case 4 -> eliminarUsuario();
                case 0 -> en_ejeccuionMenu = false;
                default -> { System.out.println("Opcion invalida, ingresa una opcion correcta por favor."); volverAlMenu(); } // CORRECCIÓN: volverAlMenu() para que el mensaje no desaparezca
            }
        }
    }

    // METODOS - ATRIBUTOS RELACIONADOS A PEDIDOS

    static void listarPedidos() {
        System.out.println("== Lista de pedidos ==");
        System.out.println("=======================================");
        boolean hayAlguno = false;
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                System.out.println(p);
                System.out.println("=======================================");
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("No existen pedidos aun.");
            System.out.println("=======================================");
        }
    }

    static void crearPedido() {
        limpiarPantalla();
        System.out.println("== Creacion de pedido ==");
        listarUsuarios();
        System.out.print("Ingrese el id del usuario: ");
        Long idUsuario;
        try {
            idUsuario = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getId().equals(idUsuario) && !u.isEliminado()) {
                usuarioEncontrado = u;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        System.out.println("Forma de pago: 1. TARJETA  2. TRANSFERENCIA  3. EFECTIVO");
        int opcionPago;
        try {
            opcionPago = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opcion invalida.");
            volverAlMenu();
            return;
        }
        if (opcionPago < 1 || opcionPago > 3) {
            System.out.println("Opcion invalida. Ingrese 1, 2 o 3.");
            volverAlMenu();
            return;
        }
        enums.FormaPago formaPago;
        switch (opcionPago) {
            case 1 -> formaPago = enums.FormaPago.TARJETA;
            case 2 -> formaPago = enums.FormaPago.TRANSFERENCIA;
            default -> formaPago = enums.FormaPago.EFECTIVO;
        }

        Pedido nuevoPedido = new Pedido(java.time.LocalDate.now(), enums.Estado.PENDIENTE, formaPago, usuarioEncontrado);

        boolean agregandoDetalles = true;
        while (agregandoDetalles) {
            listarProductos();
            System.out.print("Ingrese el id del producto (0 para terminar): ");
            Long idProducto;
            try {
                idProducto = Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("El id ingresado no es valido.");
                continue;
            }

            if (idProducto == 0) {
                agregandoDetalles = false;
                continue;
            }

            Producto productoEncontrado = null;
            for (Producto p : productos) {
                if (p.getId().equals(idProducto) && !p.isEliminado()) {
                    productoEncontrado = p;
                }
            }

            if (productoEncontrado == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int cantidad;
            try {
                cantidad = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("La cantidad ingresada no es valida.");
                continue;
            }
            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor a 0.");
                continue;
            }
            nuevoPedido.addDetallePedido(cantidad, productoEncontrado);
        }

        nuevoPedido.calcularTotal();
        pedidos.add(nuevoPedido);
        usuarioEncontrado.agregarPedido(nuevoPedido);
        System.out.println("Pedido creado correctamente. Total: " + nuevoPedido.getTotal());
        volverAlMenu();
    }

    static void editarPedido() {
        limpiarPantalla();
        System.out.println("== Editar pedido ==");
        listarPedidos();
        System.out.print("Ingrese el id del pedido a editar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Pedido encontrado = null;
        for (Pedido p : pedidos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                encontrado = p;
            }
        }

        try {
            if (encontrado == null) {
                throw new exceptions.EntidadNoEncontradaException("Pedido con id " + id + " no encontrado.");
            }
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
            volverAlMenu();
            return;
        }

        System.out.println("Nuevo estado: 1. PENDIENTE  2. CONFIRMADO  3. TERMINADO  4. CANCELADO (0 para mantener)");
        int opcionEstado;
        try {
            opcionEstado = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opcion de estado invalida.");
            volverAlMenu();
            return;
        }
        switch (opcionEstado) {
            case 1 -> encontrado.setEstado(enums.Estado.PENDIENTE);
            case 2 -> encontrado.setEstado(enums.Estado.CONFIRMADO);
            case 3 -> encontrado.setEstado(enums.Estado.TERMINADO);
            case 4 -> encontrado.setEstado(enums.Estado.CANCELADO);
            case 0 -> { }
            default -> { System.out.println("Opcion de estado invalida."); volverAlMenu(); return; }
        }

        System.out.println("Nueva forma de pago: 1. TARJETA  2. TRANSFERENCIA  3. EFECTIVO (0 para mantener)");
        int opcionPago;
        try {
            opcionPago = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Opcion de forma de pago invalida.");
            volverAlMenu();
            return;
        }
        switch (opcionPago) {
            case 1 -> encontrado.setFormaPago(enums.FormaPago.TARJETA);
            case 2 -> encontrado.setFormaPago(enums.FormaPago.TRANSFERENCIA);
            case 3 -> encontrado.setFormaPago(enums.FormaPago.EFECTIVO);
            case 0 -> { }
            default -> { System.out.println("Opcion de forma de pago invalida."); volverAlMenu(); return; }
        }

        System.out.println("Pedido editado correctamente.");
        volverAlMenu();
    }

    static void eliminarPedido() {
        limpiarPantalla();
        System.out.println("== Eliminar pedido ==");
        listarPedidos();
        System.out.print("Ingrese el id del pedido que desea eliminar: ");
        Long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("El id ingresado no es valido.");
            volverAlMenu();
            return;
        }

        Pedido encontradoPed = null;
        for (Pedido p : pedidos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                encontradoPed = p;
            }
        }
        try {
            if (encontradoPed == null) {
                throw new exceptions.EntidadNoEncontradaException("Pedido con id " + id + " no encontrado.");
            }
            System.out.print("¿Esta seguro que desea eliminar el pedido #" + encontradoPed.getId() + "? (S/N): ");
            String confirmacion = scanner.nextLine().trim();
            if (!confirmacion.equalsIgnoreCase("S")) {
                System.out.println("Operacion cancelada.");
                volverAlMenu();
                return;
            }
            encontradoPed.setEliminado(true);
            System.out.println("Pedido eliminado correctamente.");
        } catch (exceptions.EntidadNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        volverAlMenu();
    }

    static void menuPedidos() {
        limpiarPantalla();
        boolean en_ejeccuionMenu = true;
        while (en_ejeccuionMenu) {
            System.out.println("\n=== MENU PEDIDOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.println("=======================");
            System.out.print("Seleccione: ");
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> { limpiarPantalla(); listarPedidos(); volverAlMenu(); }
                case 2 -> crearPedido();
                case 3 -> editarPedido();
                case 4 -> eliminarPedido();
                case 0 -> en_ejeccuionMenu = false;
                default -> { System.out.println("Opcion invalida, ingresa una opcion correcta por favor."); volverAlMenu(); } // CORRECCIÓN: volverAlMenu() para que el mensaje no desaparezca
            }
        }
    }

    static void salirDelPrograma () { // metodo creado para salir del programa 
        System.out.println("Saliendo del programa...");
        en_ejecuccion = false;
        
    }

    // menu productos
    static void menuProductos() {
        limpiarPantalla();
        boolean en_ejeccuionMenu = true;
        while (en_ejeccuionMenu){

            System.out.println("\n=== MENU PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.println("=======================");
            System.out.print("Seleccione: ");
            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> { limpiarPantalla(); listarProductos(); volverAlMenu(); }
                case 2 -> crearProducto();
                case 3 -> editarProducto();
                case 4 -> eliminarProducto();
                case 0 -> en_ejeccuionMenu = false;
                default -> { System.out.println("Opcion invalida, ingresa una opcion correcta por favor."); volverAlMenu(); } // CORRECCIÓN: volverAlMenu() para que el mensaje no desaparezca
            }

        }
    }




    static Scanner scanner = new Scanner(System.in);
    static boolean en_ejecuccion = true;

    public static void main(String[] args) {

        while (en_ejecuccion) {
            limpiarPantalla();
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.println("=======================");
            System.out.print("Seleccione: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> menuCategorias();
                case 2 -> menuProductos();
                case 3 -> menuUsuarios();
                case 4 -> menuPedidos();
                case 0 -> salirDelPrograma();
                default -> { System.out.println("Opcion invalida, ingresa una opcion correcta por favor."); volverAlMenu(); } // CORRECCIÓN: volverAlMenu() para que el mensaje no desaparezca
            }      
        }
    }
}
