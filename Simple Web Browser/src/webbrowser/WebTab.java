package webbrowser;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

public class WebTab extends Tab {

	private final WebView view;
	
	public WebTab(String title) {
		super(title);
		
		view = new WebView();
		
		view.getEngine().load("https://google.com");
		
		/* URL Text field */
		
		TextField url = new TextField();
		
		url.setOnAction(e -> view.getEngine().load(url.getText()));
		
		view.getEngine().locationProperty().addListener((observable, old, neww) -> {
			
			url.setText(neww);
			
		});
		
		url.setPrefWidth(App.getInstance().width()*0.45);
		
		
		/* Search with Google Text field */
		
		TextField search = new TextField();
		search.setPromptText("Search with Google");
		search.setPrefWidth(App.getInstance().width()*0.2);
		
		search.setOnAction(e -> {
			
			view.getEngine().load("https://google.com/search?q="+search.getText());
			
		});
		
		/* ToolBar and buttons */
		
		Button reload = new Button("reload");
		
		reload.setOnAction(e -> view.getEngine().reload());
		
		Button add = new Button("+");
		
		add.setOnAction(e -> App.getInstance().addTab());
		
		ToolBar toolbar = new ToolBar();
		
		toolbar.getItems().addAll(
				
				new Button("<-"),
				new Button("->"),
				reload,
				add,
				url,
				search
				
				);
		
		/* Progress Bar */
		
		ProgressBar progress = new ProgressBar();
		
		progress.setPrefWidth(App.getInstance().width());
		
		progress.progressProperty().addListener((observable, old, neww) -> {
			
			progress.setVisible(neww.doubleValue() < 1.0);
			
		});
		
		progress.progressProperty().bind(view.getEngine().getLoadWorker().progressProperty());
		
		/* Panes & Parent nodes */
		
		BorderPane pane = new BorderPane();
		
		VBox header = new VBox();
		
		header.getChildren().addAll(toolbar, progress);
		
		pane.setTop(header);
		
		pane.setCenter(view);
		
		setContent(pane);
		
	}

}
