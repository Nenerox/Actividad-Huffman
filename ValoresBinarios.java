import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ValoresBinarios {
    String simbolos;
    int[] frecuencia;

    PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.data));

    private void preOrder(Nodo root, ArrayList<String> codigos, String binario) {
		if (root == null) return;

		if (root.left == null && root.right == null) {
			codigos.add(binario);
			return;
		}

		preOrder(root.left, codigos, binario + "0");
		preOrder(root.right, codigos, binario + "1");
	}

    public ArrayList<String> valores(String simbolos, int[] frecuencias) {
        int n = simbolos.length();

        for (int i = 0; i < n; i++) {
            Nodo nodo = new Nodo(frecuencias[i]);
            pq.add(nodo);
        }

        while (pq.size() >= 2) {
            Nodo L = pq.poll();
            Nodo R = pq.poll();

            Nodo nuevo = new Nodo(L.data + R.data);
			nuevo.left = L;
			nuevo.right = R;

			pq.add(nuevo);
        }

        Nodo raiz = pq.poll();
        ArrayList<String> valores = new ArrayList<>();
        preOrder(raiz, valores, "");
        return valores;
    }
    
}
