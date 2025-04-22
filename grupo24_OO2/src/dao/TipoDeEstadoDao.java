package dao;

import datos.TipoDeEstado;
import java.util.Arrays;
import java.util.List;

public class TipoDeEstadoDao {

    // Método para obtener todos los valores del enum TipoDeEstado
    public List<TipoDeEstado> traerTodos() {
        return Arrays.asList(TipoDeEstado.values());
    }

    // Método para obtener un TipoDeEstado por su nombre
    public TipoDeEstado traerPorNombre(String nombre) {
        for (TipoDeEstado estado : TipoDeEstado.values()) {
            if (estado.name().equalsIgnoreCase(nombre)) {
                return estado;
            }
        }
        return null; // Si no se encuentra, retorna null
    }

    // Método para obtener un TipoDeEstado por su índice (si es necesario)
    public TipoDeEstado traerPorIndice(int indice) {
        if (indice >= 0 && indice < TipoDeEstado.values().length) {
            return TipoDeEstado.values()[indice];
        }
        return null; // Si el índice es inválido, retorna null
    }
}
