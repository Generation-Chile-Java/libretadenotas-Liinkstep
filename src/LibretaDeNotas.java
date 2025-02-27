import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class LibretaDeNotas {
    //Aqui se almacenan las calificaciones de los estudiantes
    private HashMap<String, ArrayList<Integer>> estudiantes;

    public LibretaDeNotas() {
        estudiantes = new HashMap<>();//inicializar el HashMap vacio

    }

    //metodo para ingresar datos
    public void IngresarDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa la cantidad de estudiantes ");
        int numeroAlumnos = sc.nextInt();

        System.out.println("Ingresa la cantidad de notas por estudiantes ");
        int numeroNotas = sc.nextInt();

        //Aqui se piden los datos de cada estudiante
        for (int i = 0; i < numeroAlumnos; i++) {
            sc.nextLine();
            System.out.println("Ingresa el nombre del estudiante " + (i + 1) + ": ");
            String nombre = sc.nextLine();

            ArrayList<Integer> notas = new ArrayList<>();
            for (int j = 0; j < numeroNotas; j++) {
                System.out.println("Ingresa la nota " + (j + 1) + ": ");
                int nota = sc.nextInt();

                //Validacion de que la nota este en el rango de 0 a 10
                while (nota < 0 || nota > 10) {
                    System.out.println("Nota Invalida, porfavor ingresar una nota entre 0 y 10");
                    nota = sc.nextInt();

                }
                notas.add(nota);
            }
            estudiantes.put(nombre, notas);
        }
    }

    //Crear metodo para obtener el promedio de las notas
    public double calcularPromedio(ArrayList<Integer> notas) {
        int suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        return (double) suma / notas.size();
    }

    //Crear metodo para obtener la nota maxima
    public int obtenerNotaMaxima(ArrayList<Integer> notas) {
        int max = notas.get(0);
        for (int nota : notas) {
            if (nota > max) {
                max = nota;
            }
        }
        return max;

    }

    //Crear metodo para obtener la nota minima
    public int obtenerNotaMinima(ArrayList<Integer> notas) {
        int min = notas.get(0);
        for (int nota : notas) {
            if (nota < min) {
                min = nota;
            }
        }
        return min;
    }

    //Crear metodo para mostrar el promedio de cada estudiante
    public void mostrarPromedio() {
        for (String nombre : estudiantes.keySet()) {
            ArrayList<Integer> notas = estudiantes.get(nombre);
            double promedio = calcularPromedio(notas);
            System.out.println(nombre + ": promedio = " + promedio);

        }
    }

    //Crear el metodo para verificar si una nota es aprobatoria
    public void verificarAprobatoria() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar nombre del estudiante: ");
        String nombre = sc.nextLine();

        if (estudiantes.containsKey(nombre)) {
            System.out.println("Ingresar la nota: ");
            int nota = sc.nextInt();

            // Consideramos como nota de aprobacion 4.0
            if (nota >=4) {
                System.out.println("Aprueba.");
            } else {
                System.out.println("Reprueba.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    //Metodo para verificar si una nota esta sobre o por debajo del promedio del curso
    public void verificarPorEncimaDelPromedio() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar nombre del estudiante: ");
        String nombre = sc.nextLine();

        if (estudiantes.containsKey(nombre)){
            System.out.println("Ingresar la nota: ");
            int nota = sc.nextInt();

            //Calcular Promedio general
            double sumaTotal = 0;
            int totalNotas = 0;
            for (ArrayList<Integer> notas : estudiantes.values()) {
                for (int n : notas) {
                    sumaTotal += n;
                    totalNotas++;
                }
            }
            double promedioCurso = sumaTotal / totalNotas;

            if (nota > promedioCurso) {
                System.out.println("Nota por sobre el promedio del curso.");
            } else if (nota < promedioCurso) {
                System.out.println("Nota por debajo el promedio del curso.");
            } else {
                System.out.println("La nota es la promedio del curso.");
            }
        } else {
            System.out.println("Estudiante no encontrado.");

        }
    }

    // Crear interfaz de opciones
    public void menu() {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\nMenu de opciones:");
            System.out.println("1. Mostrar el promedio de notas por estudiante.");
            System.out.println("2. Mostrar si la nota Aprueba o Reprueba.");
            System.out.println("3. Mostrar si la nota esta sobre el promedio o no.");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            option = sc.nextInt();

            sc.close();

            switch (option) {
                case 1:
                    mostrarPromedio();
                    break;
                case 2:
                    verificarAprobatoria();
                    break;
                case 3:
                    verificarPorEncimaDelPromedio();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;

            }
        }while (option != 0);
    }

    public static void main(String[] args) {
        LibretaDeNotas libreta = new LibretaDeNotas();

        //Ingresar datos
        libreta.IngresarDatos();

        //Ejecutar menu
        libreta.menu();


    }
}
