package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX Application Entry Point for ProcessPilot GUI
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML with proper resource handling
            FXMLLoader loader = new FXMLLoader();
            
            // Set the resources to the classpath
            java.net.URL fxmlUrl = getClass().getClassLoader().getResource("main.fxml");
            
            if (fxmlUrl == null) {
                System.err.println("ERROR: Cannot find main.fxml. Attempting from file system...");
                // Fallback: try to load directly from file
                java.nio.file.Path fxmlPath = java.nio.file.Paths.get("resources/main.fxml");
                if (java.nio.file.Files.exists(fxmlPath)) {
                    fxmlUrl = fxmlPath.toUri().toURL();
                } else {
                    throw new IOException("Cannot find main.fxml in resources folder or classpath");
                }
            }
            
            loader.setLocation(fxmlUrl);
            javafx.scene.Parent root = loader.load();

            // Create scene
            Scene scene = new Scene(root, 1400, 900);

            // Style with CSS if available
            java.net.URL cssUrl = getClass().getClassLoader().getResource("style.css");
            if (cssUrl == null) {
                // Try file system fallback
                java.nio.file.Path cssPath = java.nio.file.Paths.get("resources/style.css");
                if (java.nio.file.Files.exists(cssPath)) {
                    cssUrl = cssPath.toUri().toURL();
                }
            }
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            // Set stage
            primaryStage.setTitle("ProcessPilot - Advanced CPU Scheduling Simulator");
            primaryStage.setScene(scene);
            
            // Set icon if available (optional)
            try {
                java.net.URL iconUrl = getClass().getClassLoader().getResource("icon.png");
                if (iconUrl != null) {
                    primaryStage.getIcons().add(new Image(iconUrl.toExternalForm()));
                }
            } catch (Exception e) {
                // Icon not found, continue without it
                System.out.println("Icon not found, continuing without it");
            }

            primaryStage.setMinWidth(1200);
            primaryStage.setMinHeight(700);
            
            // Set window position and size for visibility
            primaryStage.setX(100);
            primaryStage.setY(100);
            primaryStage.setWidth(1400);
            primaryStage.setHeight(900);

            System.out.println("ProcessPilot GUI is starting...");
            primaryStage.show();
            System.out.println("ProcessPilot GUI window displayed successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
