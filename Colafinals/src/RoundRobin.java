import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class RoundRobin {
    static Scanner sc = new Scanner(System.in);
    static Queue<Proceso> colaProcesos = new LinkedList<Proceso>();
    static Stack<Proceso> historialProcesos = new Stack<Proceso>();
    static int quantum = 35;

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("========================================");
            System.out.println("         SIMULADOR DE PLANIFICACIÓN      ");
            System.out.println("========================================");
            System.out.println("1. Imprimir datos personales");
            System.out.println("2. Definir procesos predefinidos");
            System.out.println("3. Insertar nuevo proceso a la cola");
            System.out.println("4. Imprimir cola");
            System.out.println("5. Establecer valor del quantum");
            System.out.println("6. Ejecutar Round Robin");
            System.out.println("7. Ver historial de procesos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    imprimirDatosPersonales();
                    break;
                case 2:
                    definirProcesosPredefinidos();
                    break;
                case 3:
                    insertarNuevoProceso();
                    break;
                case 4:
                    imprimirCola();
                    break;
                case 5:
                    establecerQuantum();
                    break;
                case 6:
                    ejecutarRoundRobin();
                    break;
                case 7:
                    verHistorial();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 8);
    }

    public static void imprimirDatosPersonales() {
        System.out.println("========================================");
        System.out.println("           DATOS PERSONALES              ");
        System.out.println("========================================");
        System.out.println("Estudiante 1: Juan Pérez, 123456789, 001234567");
        System.out.println("Estudiante 2: María Gómez, 987654321, 009876543");
        System.out.println("Estudiante 3: Carlos Martínez, 567891234, 005678912");
        System.out.println("Estudiante 4: Ana García, 345678912, 003456789");
    }

    public static void definirProcesosPredefinidos() {
        Proceso p1 = new Proceso("P1", 1002856059, 100);
        Proceso p2 = new Proceso("P2", 1714196743, 15);
        Proceso p3 = new Proceso("P3", 1456756888, 40);
        Proceso p4 = new Proceso("P4", 1134658790, 50);
        Proceso p5 = new Proceso("P5", 234567890, 25);
        Proceso p6 = new Proceso("P6", 987654321, 30);
        colaProcesos.add(p1);
        colaProcesos.add(p2);
        colaProcesos.add(p3);
        colaProcesos.add(p4);
        colaProcesos.add(p5);
        colaProcesos.add(p6);
        System.out.println(" Se ha agregado 6 procesos a la cola");


    }

    public static void insertarNuevoProceso(){
        System.out.println("Ingrese los datos del nuevo proceso:");
        System.out.print("ID: ");
        String id = sc.next();
        System.out.print("Cédula: ");
        long cedula = sc.nextLong();
        System.out.print("Tiempo de CPU (ms): ");
        int tiempo = sc.nextInt();
        colaProcesos.add(new Proceso(id, cedula, tiempo));
        System.out.println("Proceso agregado a la cola.");


    }
    public static void imprimirCola(){
        for (Proceso proceso : colaProcesos){
            System.out.println(proceso);
        }
        System.out.println();
    }

    public static void establecerQuantum(){
        System.out.print("Ingrese el valor del quantum (ms): ");
        quantum = sc.nextInt();
        System.out.println("Quantum actualizado a " + quantum + " ms.");

    }
    public static void ejecutarRoundRobin(){
        int tiempoTotal = 0;
        int conmutaciones = 0;
        Stack<Proceso> historial = new Stack<>();

        while (!colaProcesos.isEmpty()) {
            Proceso proceso = colaProcesos.poll();
            int tiempoEjecucion = Math.min(quantum, proceso.getTiempoCPU());
            tiempoTotal += tiempoEjecucion;
            proceso.setTiempoCPU(proceso.getTiempoCPU() - tiempoEjecucion);
            historial.push(proceso);

            System.out.println("Tiempo " + tiempoTotal + ": " + proceso.getIdProceso() + " entra a ejecución.");
            if (proceso.getTiempoCPU() > 0) {
                colaProcesos.add(proceso);
                System.out.println("Tiempo " + tiempoTotal + ": " + proceso.getIdProceso() + " se conmuta. Pendiente por ejecutar " + proceso.getTiempoCPU() + " ms.");
                conmutaciones++;
            } else {
                historialProcesos.push(proceso);
                System.out.println("Tiempo " + tiempoTotal + ": " + proceso.getIdProceso() + " termina su ejecución.");
            }
        }

        System.out.println("\nESTADÍSTICAS GENERADAS:");
        System.out.println("Total tiempo de ejecución de todos los procesos: " + tiempoTotal + " ms.");
        System.out.println("Total de conmutaciones: " + conmutaciones);


    }

    public static void verHistorial() {
        System.out.println("========================================");
        System.out.println("         HISTORIAL DE PROCESOS           ");
        System.out.println("========================================");
        if (historialProcesos.isEmpty()) {
            System.out.println("No se han ejecutado procesos aún.");
        } else {
            System.out.println("Procesos ejecutados:");
            while (!historialProcesos.isEmpty()) {
                System.out.println(historialProcesos.pop());
            }
        }
    }
}

