import java.util.InputMismatchException;
import java.util.Scanner;

import data.Conn;
import data.ItemsData;
import entidades.Items;

public class Main {
    public static void main(String[] args) {
        ItemsData data = new ItemsData();
        Scanner input = new Scanner(System.in);
        int opt = 0;
        
        do {
            System.out.println("***** CRUD PERSON *****");
            System.out.println(
            "1 = Lista(list) \n"+
            "2 = Nuevo(New) \n"+
            "3 = Borrar(Delete) \n"+
            "4 = obtener item(get) \n"+
            "5 = actualizar item(update) \n"+
            "0 = Salida(Exit) \n"+
            "elegir opcion / choise option: ");
            opt = input.nextInt();
            System.out.println("tu elegiste / You chosed: " + opt);
            input.nextLine(); // Limpiar el buffer
            switch (opt) {
                case 1:
                    System.out.println("Listado de personas ");
                    for (Items d : data.list("")) {
                        System.out.println(d.getId()
                         + "\t" + d.getNombre() 
                        + "\t" + d.getCategoria() 
                        + "\t" + d.getFamilia() 
                        + "\t" + d.getPrecio());
                    }
                    break;
                case 2:
                    System.out.println("Nueva item ");
                    Items p = new Items();
                    System.out.print("nombre: ");
                    p.setNombre(input.nextLine());
                    System.out.print("categoria: ");
                    p.setCategoria(input.nextLine());
                    System.out.print("familia: ");
                    p.setFamilia(input.nextLine());
                    

                    System.out.print("precio: ");
                    try {
                        p.setPrecio(input.nextInt());
                        data.create(p);
                    } catch (Exception e) {
                        input.nextLine(); // Limpiar el buffer
                        System.out.print("el precio tiene que ser un numero");
                    }
                    System.out.print("familia: ");
                    p.setFamilia(input.nextLine());
                    break;
                case 3:
                    System.out.println("Eliminar item ");
                    System.out.print("id: ");
                    data.delete(input.nextInt());
                    break;
                case 4:
                    int b4 = 1;
                    do {
                        System.out.println("obetener item ");
                        System.out.print("id: ");
                        int id = 0;
                        try {
                            b4 = 0;
                            id = input.nextInt();
                            Items d = data.get(id);
                            if (d != null) {
                                System.out.println("Id: " + d.getId());
                                System.out.println("Nombre: " + d.getNombre());
                            } else {
                                System.out.print("el item no existe");
                            }
                        } catch (Exception e) {
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("id no valido, debe ser un numero");
                            b4 = 1;
                        }

                    } while (b4 != 0);

                    break;
                case 5:
                    System.out.println("actualizar item ");
                    System.out.print("id: ");

                    Items per = data.get(input.nextInt());

                    if (per != null) {
                        System.out.println("nombre de item actual: " + per.getNombre());
                        System.out.println("categoria de item actual: " + per.getCategoria());
                        System.out.println("familia de item actual: " + per.getFamilia());
                        System.out.println("precio de item actual : " + per.getPrecio());

                        input.nextLine(); // Limpiar el buffer
                        System.out.print("nuevo nombre de item: ");
                        per.setNombre(input.nextLine());

                        System.out.print("nuevo categoria de item: ");
                        per.setCategoria(input.nextLine());

                        System.out.print("nuevo familia de item: ");
                        per.setCategoria(input.nextLine());

                        System.out.print("nueva precio de item: ");
                        try {
                            per.setPrecio(input.nextInt());
                            data.update(per);
                        } catch (Exception e) {
                            // per.setPrecio(0);
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("precio debe ser numero");
                        }

                    } else {
                        System.out.println("NO encontrado");
                    }

                    break;

                default:
                    System.out.println("Opcion no valida");
            }
        } while (opt != 0);
    }
}


