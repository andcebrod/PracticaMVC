package es.studium.PracticaMVC;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modelo Model = new Modelo();
		MenuPrincipal MenuPrinci = new MenuPrincipal();
		new Controlador(Model, MenuPrinci);
	}

}