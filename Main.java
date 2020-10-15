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
            "4 = obtener persona(get) \n"+
            "5 = actualizar persona(update) \n"+
            "0 = Salida(Exit) \n"+
            "elegir opcion / choise option: ");
            opt = input.nextInt();
            System.out.println("tu elegiste / You chosed: " + opt);
            input.nextLine(); // Limpiar el buffer
            switch (opt) {
                case 1:
                    System.out.println("Listado de personas ");
                    for (Items d : data.list("")) {
                        System.out.println(d.getId() + "\t" + d.getNombre() + "\t" + d.getCategoria() + "\t" + d.getFamilia() + "\t" + d.getPrecio());
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
                    try {
                        p.setFamilia(input.next());
                        data.create(p);
                    } catch (Exception e) {
                        input.nextLine(); // Limpiar el buffer
                        System.out.print("Edad debe ser entero, no se guardo");
                    }

                    break;
                case 3:
                    System.out.println("Eliminar persona ");
                    System.out.print("id: ");
                    data.delete(input.nextInt());
                    break;
                case 4:
                    int b4 = 1;
                    do {
                        System.out.println("get persona ");
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
                    System.out.println("update persona ");
                    System.out.print("id: ");

                    Items per = data.get(input.nextInt());

                    if (per != null) {
                        System.out.println("Name current: " + per.getNombre());
                        System.out.println("Sex current: " + per.getCategoria());

                        input.nextLine(); // Limpiar el buffer
                        System.out.print("new name: ");
                        per.setNombre(input.nextLine());

                        System.out.print("new sex: ");
                        per.setCategoria(input.nextLine());

                        System.out.print("edad: ");
                        try {
                            per.setFamilia(input.next());
                            data.update(per);
                        } catch (Exception e) {
                            // per.setAge(0);
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("Edad debe ser entero, no se guardo");
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


