import java.util.Arrays;

class Empleado {
	int id;
	String nombre;
	float salarioBase;

	public Empleado(int id, String nombre, float salarioBase) {
		this.id = id;
		this.nombre = nombre;
		this.salarioBase = salarioBase;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", salarioBase=" + salarioBase + "]";
	}
}

public class Empleados {

	// MC)todo para ordenar usando InsertSort
	public static void insertSort(Empleado[] empleados) {
		for (int i = 1; i < empleados.length; i++) {
			Empleado key = empleados[i];
			int j = i - 1;
			while (j >= 0 && empleados[j].id > key.id) {
				empleados[j + 1] = empleados[j];
				j = j - 1;
			}
			empleados[j + 1] = key;
		}
	}

	// MC)todo para realizar el MergeSort
	public static void mergeSort(Empleado[] empleados, int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			mergeSort(empleados, left, middle);
			mergeSort(empleados, middle + 1, right);
			merge(empleados, left, middle, right);
		}
	}

	private static void merge(Empleado[] empleados, int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;

		Empleado[] L = new Empleado[n1];
		Empleado[] R = new Empleado[n2];

		System.arraycopy(empleados, left, L, 0, n1);
		System.arraycopy(empleados, middle + 1, R, 0, n2);

		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (L[i].id <= R[j].id) {
				empleados[k] = L[i];
				i++;
			} else {
				empleados[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			empleados[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			empleados[k] = R[j];
			j++;
			k++;
		}
	}

	// MC)todo para realizar QuickSort
	public static void quickSort(Empleado[] empleados, int low, int high) {
		if (low < high) {
			int pi = partition(empleados, low, high);
			quickSort(empleados, low, pi - 1);
			quickSort(empleados, pi + 1, high);
		}
	}

	private static int partition(Empleado[] empleados, int low, int high) {
		Empleado pivot = empleados[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (empleados[j].id < pivot.id) {
				i++;
				Empleado temp = empleados[i];
				empleados[i] = empleados[j];
				empleados[j] = temp;
			}
		}
		Empleado temp = empleados[i + 1];
		empleados[i + 1] = empleados[high];
		empleados[high] = temp;
		return i + 1;
	}

	// BC:squeda binaria por ID
	public static int busquedaBinaria(Empleado[] empleados, int id, int low, int high) {
		if (high >= low) {
			int mid = low + (high - low) / 2;

			if (empleados[mid].id == id) {
				return mid;
			}
			if (empleados[mid].id > id) {
				return busquedaBinaria(empleados, id, low, mid - 1);
			}
			return busquedaBinaria(empleados, id, mid + 1, high);
		}
		return -1;
	}

	public static void main(String[] args) {
		Empleado[] empleados = new Empleado[5];
		empleados[0] = new Empleado(5, "Ana", 3000.0f);
		empleados[1] = new Empleado(3, "Luis", 2500.0f);
		empleados[2] = new Empleado(1, "Carlos", 4000.0f);
		empleados[3] = new Empleado(4, "Pedro", 3500.0f);
		empleados[4] = new Empleado(2, "Sofia", 2800.0f);

		System.out.println("Empleados originales:");
		System.out.println(Arrays.toString(empleados));

		// Ordenar usando MergeSort
		mergeSort(empleados, 0, empleados.length - 1);
		System.out.println("\nEmpleados ordenados por MergeSort:");
		System.out.println(Arrays.toString(empleados));

		// Ordenar usando QuickSort
		quickSort(empleados, 0, empleados.length - 1);
		System.out.println("\nEmpleados ordenados por QuickSort:");
		System.out.println(Arrays.toString(empleados));

		// Ordenar usando InsertSort
		insertSort(empleados);
		System.out.println("\nEmpleados ordenados por InsertSort:");
		System.out.println(Arrays.toString(empleados));

		// BC:squeda binaria
		int idBusqueda = 3;
		int resultado = busquedaBinaria(empleados, idBusqueda, 0, empleados.length - 1);
		if (resultado != -1) {
			System.out.println("\nEmpleado encontrado: " + empleados[resultado]);
		} else {
			System.out.println("\nEmpleado con ID " + idBusqueda + " no encontrado.");
		}
	}
}
