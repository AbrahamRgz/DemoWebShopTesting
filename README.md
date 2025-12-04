Este es un proyecto o practica bastante completo con mis estudios sobre automatizacion de software utilazando selenium con java y TestNg
El archivo principal es el que se llama DemoWebTestNg, o tambien se puede ejecutar la prueba directo desde el archivo testng.xml,
A continuacion dejo las intrucciones con las que realize la practica:


Pre condiciones.- Registrarse en el sitio DemoWebShop(ESto no se automatiza)

Inicio de Ejercicio Final
1.- Acceder al sitio https://demowebshop.tricentis.com/electronics y dar clic al link de Log in
2.-Ingresar el email, el Password y dar clic en el botón Log in
3.- Dar clic al menú APPAREL & SHOES
4.- Seleccionar el producto Blue Jeans
5.- Agregar la cantidad de 5 pantalones y dr clic en el botón "Add to cart"
6.- Validar que apareza el mensaje "The product has been added to your shopping cart", (revisar el video de Assetions)
7.- Dar clic al Link de Shopping cart
8.- Marcar la opción de acuerdo con los terminos y condiciones y dar clic en el botón "checkout"
9.- Billing Address
	CApturar la información de dirección y dar clic en el botón continuar
10.- Shipping Address
	dar clic en el botón continiuar
11.-Shipping Method
	Seleccionar la opción de "Ground (10.00)" y dar clic en el botón continuar
12.-Payment Method
	Seleccionar la opción de Tarjeta de Crédito y dar clic en el botón continuar
13.-Payment Information
	Capturar todos los datos solicitados y dar clic en el botón continuar 
	(TDC: 5482340924944396)
14.-Confirm Order
	Validar que los valores que aparecen en el apartado de Paymment method y Shipping methos 
	sean los mismos que seleccionamos en las secciones anteriores
	Validar que la suma del sub-Total + Shipping+ Tax = Total
	Dar clic al botón "confirm"
15.-Validar que aparezca el mensaje Your order has been successfully processed!"" y dar clic al botón continuar
16.- Dar clic en Log out


NOTA: es necesario tomar un screenshot, por cada uno de los pasos descritos anteriormente, es decir 16 screenshots.

