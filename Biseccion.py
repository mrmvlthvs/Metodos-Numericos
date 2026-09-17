# inicia con variables a y b
# f(x) igual a fx
# f(a) igual a fa
# f(b) igual a fb

def biseccion(a, b, tol, max_iter):
    fa = a**2 - (4.5 * a) + 4.16
    fb = b**2 - (4.5 * b) + 4.16

    if fa * fb >= 0:
        print("El método de bisección no puede aplicarse en este intervalo.")
        return None

    for i in range(max_iter):
        c = (a + b) / 2
        fc = c**2 - (4.5 * c) + 4.16

        if abs(fc) < tol or (b - a) / 2 < tol:
            return c

        if fa * fc < 0:
            b = c
            fb = fc
        else:
            a = c
            fa = fc

    print("Se alcanzó el número máximo de iteraciones.")
    return (a + b) / 2


print("El programa de biseccion requiene dos valores a y b,")
print("una tolerancia y un número máximo de iteraciones.")
print()
a = float(input("Ingrese el valor de a:"))
b = float(input("Ingrese el valor de b:"))
tol = float(input("Ingrese la tolerancia:"))
max_iter = int(input("Ingrese el número máximo de iteraciones:"))       

biseccion(a, b, tol, max_iter)
