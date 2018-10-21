package webbrowser;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;


/*
 * Pequeña practica de la clase sobre JavaFX
 * 
 * Consiste en hacer un pequeño navegador web utilizando
 * los conceptos vistos en la clase
 * 
 * Este proyecto es lo que deberiamos poder hacer en clase,
 * si tenemos el tiempo suficiente
 * 
 */
public class App extends Application {
	
	public static void main(String[] args) {
		launch();
	}
	
	private static App instance;
	
	public static App getInstance() {
		
		if(instance == null) {
			throw new IllegalStateException("JavaFX is not initialized!");
		}
		
		return instance;
		
	}
	
	private long tabID = 0;
	private Stage stage;
	private TabPane tabs;
	
	public App() {
		
		if(instance != null) {
			throw new IllegalStateException("App is already initialized!");
		}
		
		instance = this;
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		this.stage = stage;
		
		tabs = new TabPane();
		
		Scene scene = new Scene(tabs);
		
		stage.setScene(scene);
		
		stage.setMaximized(true);
	
		stage.show();
		
		addTab();
		
	}
	
	public void addTab() {
		WebTab tab = new WebTab("New Tab "+tabID++);
		tabs.getTabs().add(tab);
		tabs.getSelectionModel().select(tab);
	}
	
	public double width() {
		return stage.getWidth();
	}
	
	public double height() {
		return stage.getHeight();
	}
	

}
