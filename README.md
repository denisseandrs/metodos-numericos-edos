**Materia:** Métodos Numéricos — Unidad 4
**Unidad 6**: Resolución numérica de EDOs 
**Docente:** Víctor Hugo Vásquez Herrera
**Alumna:** Denisse Itzel Andres Garrido


## Contenido
- `MetodoEuler.java`: Euler para y' = x − y, y(0) = 1. Exacta: y = x − 1 + 2e^(−x)
- `MetodoRungeKutta.java`: RK4 para y' = x + y, y(0) = 1. Exacta: y = 2e^x − x − 1

Ambos con x_final = 0.3, criterio de paro por número de pasos N y error absoluto |y_exacta − y_n|.

## Requisitos
JDK 8 o superior (`java -version`, `javac -version`).

## Clonar
```bash
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_REPOSITORIO>
```

## Compilar
```bash
javac MetodoEuler.java MetodoRungeKutta.java
```

## Ejecutar
```bash
java MetodoEuler            # corre h = 0.1, 0.05 y 0.01
java MetodoEuler 0.05       # un h específico
java MetodoRungeKutta       # h = 0.1 por defecto
java MetodoRungeKutta 0.05  # un h específico
```
