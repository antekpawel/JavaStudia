module MVC_1 {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
