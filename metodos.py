def f(x):
    return x**2 - 4.5*x + 4.16

def df(x):
    return 2*x - 4.5

def biseccion(a, b, tol=0.001, max_iter=10):
    print("\n--- MÉTODO DE BISECCIÓN ---")
    if f(a) * f(b) >= 0:
        print("El intervalo no es válido. f(a) y f(b) deben tener signos opuestos.")
        return
    
    print(f"{'i':<3} | {'a':<8} | {'xr':<8} | {'b':<8} | {'f(a)':<8} | {'f(xr)':<8} | {'f(b)':<8} | {'Error %':<8}")
    print("-" * 75)
    
    xr = 0
    for i in range(1, max_iter + 1):
        xr_ant = xr
        xr = (a + b) / 2
        
        if i == 1:
            error_str = "---"
        else:
            error = abs((xr - xr_ant) / xr) * 100
            error_str = f"{error:.4f}"
            
        print(f"{i:<3} | {a:<8.4f} | {xr:<8.4f} | {b:<8.4f} | {f(a):<8.4f} | {f(xr):<8.4f} | {f(b):<8.4f} | {error_str:<8}")
        
        if f(a) * f(xr) < 0:
            b = xr
        else:
            a = xr
            
        if i > 1 and error < tol:
            break

def regla_falsa(a, b, tol=0.001, max_iter=10):
    print("\n--- MÉTODO DE LA REGLA FALSA ---")
    if f(a) * f(b) >= 0:
        print("El intervalo no es válido. f(a) y f(b) deben tener signos opuestos.")
        return
        
    print(f"{'i':<3} | {'a':<8} | {'xr':<8} | {'b':<8} | {'f(a)':<8} | {'f(xr)':<8} | {'f(b)':<8} | {'Error %':<8}")
    print("-" * 75)
    
    xr = 0
    for i in range(1, max_iter + 1):
        xr_ant = xr
        fa = f(a)
        fb = f(b)
        xr = b - (fb * (a - b)) / (fa - fb)
        fxr = f(xr)
        
        if i == 1:
            error_str = "---"
        else:
            error = abs((xr - xr_ant) / xr) * 100
            error_str = f"{error:.4f}"
            
        print(f"{i:<3} | {a:<8.4f} | {xr:<8.4f} | {b:<8.4f} | {fa:<8.4f} | {fxr:<8.4f} | {fb:<8.4f} | {error_str:<8}")
        
        if fa * fxr < 0:
            b = xr
        else:
            a = xr
            
        if i > 1 and error < tol:
            break

def newton_raphson(x0, tol=0.001, max_iter=10):
    print("\n--- MÉTODO DE NEWTON-RAPHSON ---")
    print(f"{'i':<3} | {'xi':<8} | {'f(xi)':<8} | {'f\'(xi)':<8} | {'xi+1':<8} | {'Error %':<8}")
    print("-" * 65)
    
    xi = x0
    for i in range(1, max_iter + 1):
        f_xi = f(xi)
        df_xi = df(xi)
        
        if df_xi == 0:
            print("Error: La derivada es cero.")
            break
            
        xi_next = xi - (f_xi / df_xi)
        error = abs((xi_next - xi) / xi_next) * 100
        
        print(f"{i:<3} | {xi:<8.4f} | {f_xi:<8.4f} | {df_xi:<8.4f} | {xi_next:<8.4f} | {error:<8.4f}")
        
        xi = xi_next
        if error < tol:
            break

def secante(x0, x1, tol=0.001, max_iter=10):
    print("\n--- MÉTODO DE LA SECANTE ---")
    print(f"{'i':<3} | {'x_i-1':<8} | {'x_i':<8} | {'f(x_i-1)':<8} | {'f(x_i)':<8} | {'x_i+1':<8} | {'Error %':<8}")
    print("-" * 80)
    
    for i in range(1, max_iter + 1):
        f0 = f(x0)
        f1 = f(x1)
        
        if f0 - f1 == 0:
            print("Error: División por cero.")
            break
            
        x2 = x1 - (f1 * (x0 - x1)) / (f0 - f1)
        error = abs((x2 - x1) / x2) * 100
        
        print(f"{i:<3} | {x0:<8.4f} | {x1:<8.4f} | {f0:<8.4f} | {f1:<8.4f} | {x2:<8.4f} | {error:<8.4f}")
        
        x0 = x1
        x1 = x2
        if error < tol:
            break

# Ejecución de prueba con los valores de tus tablas anteriores
biseccion(a=1, b=2)
regla_falsa(a=1, b=2)
newton_raphson(x0=1)
secante(x0=2, x1=3)

print("===== PROGRAMA METODOS NUMERICOS 1 =====")
texto = """
Este programa permite calcular las raíces de la función 
f(x) = x^2 - 4.5x + 4.16 
utilizando diferentes métodos numéricos: 
Bisección, Regla Falsa, Newton-Raphson y Secante.
Para cada método, se requiere ingresar los valores iniciales, 
la tolerancia y el número máximo de iteraciones. 
El programa mostrará los resultados de cada iteración y el error relativo."""
print(texto)
texto = """
Eliga alguna de las siguientes opciones para calcular la raíz de la función:
1. Método de Bisección      
2. Método de Regla Falsa
3. Método de Newton-Raphson
4. Método de la Secante
5. Salir del programa"""
print(texto)
option = int(input("Ingrese el número de la opción deseada: "))
switch(option):
    case 1:
        a = float(input("Ingrese el valor de a: "))
        b = float(input("Ingrese el valor de b: "))
        tol = float(input("Ingrese la tolerancia: "))
        max_iter = int(input("Ingrese el número máximo de iteraciones: "))
        biseccion(a, b, tol, max_iter)
    case 2:
        a = float(input("Ingrese el valor de a: "))
        b = float(input("Ingrese el valor de b: "))
        tol = float(input("Ingrese la tolerancia: "))
        max_iter = int(input("Ingrese el número máximo de iteraciones: "))
        regla_falsa(a, b, tol, max_iter)
    case 3:
        x0 = float(input("Ingrese el valor inicial x0: "))
        tol = float(input("Ingrese la tolerancia: "))
        max_iter = int(input("Ingrese el número máximo de iteraciones: "))
        newton_raphson(x0, tol, max_iter)
    case 4:
        x0 = float(input("Ingrese el valor inicial x0: "))
        x1 = float(input("Ingrese el valor inicial x1: "))
        tol = float(input("Ingrese la tolerancia: "))
        max_iter = int(input("Ingrese el número máximo de iteraciones: "))
        secante(x0, x1, tol, max_iter)
    case 5:
        print("Saliendo del programa.")
    case _:
        print("Opción no válida. Por favor, seleccione una opción del 1 al 5.") 