package com.mycompany.system.livraison;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Semaphore;

public class Main extends Application {

    private final ObservableList<Colis> colisData = FXCollections.observableArrayList(); // Liste des colis
    private final Semaphore semaphore = new Semaphore(1); // Semaphore pour la gestion concurrentielle

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Titre de l'application
        Label titleLabel = new Label("Gestion de Livraison de Colis");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Champs pour ajouter un nouveau colis
        TextField expediteurField = new TextField();
        expediteurField.setPromptText("Nom de l'expéditeur");

        TextField destinataireField = new TextField();
        destinataireField.setPromptText("Nom du destinataire");

        ComboBox<String> statutComboBox = new ComboBox<>();
        statutComboBox.setItems(FXCollections.observableArrayList("En attente", "Livré", "Échec"));
        statutComboBox.setPromptText("Statut initial");

        Button ajouterColisButton = new Button("Ajouter un colis");
        ajouterColisButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        // Table pour afficher les colis
        TableView<Colis> colisTableView = new TableView<>();
        colisTableView.setItems(colisData);

        TableColumn<Colis, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));

        TableColumn<Colis, String> expediteurCol = new TableColumn<>("Expéditeur");
        expediteurCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExpediteur()));

        TableColumn<Colis, String> destinataireCol = new TableColumn<>("Destinataire");
        destinataireCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestinataire()));

        TableColumn<Colis, String> statutCol = new TableColumn<>("Statut");
        statutCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEtat()));

        colisTableView.getColumns().addAll(idCol, expediteurCol, destinataireCol, statutCol);

        // Champs pour mettre à jour le statut
        ComboBox<String> updateStatutComboBox = new ComboBox<>();
        updateStatutComboBox.setItems(FXCollections.observableArrayList("En attente", "Livré", "Échec"));
        updateStatutComboBox.setPromptText("Modifier le statut");

        Button mettreAJourButton = new Button("Mettre à jour le statut");
        mettreAJourButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        // Action pour ajouter un colis
        ajouterColisButton.setOnAction(e -> {
            try {
                semaphore.acquire(); // Acquérir le Semaphore
                String expediteur = expediteurField.getText().trim();
                String destinataire = destinataireField.getText().trim();
                String statut = statutComboBox.getValue();

                if (!expediteur.isEmpty() && !destinataire.isEmpty() && statut != null) {
                    // Générer un ID unique
                    String id = "COLIS-" + (colisData.size() + 1);

                    // Ajouter un nouveau colis
                    Colis colis = new Colis(id, expediteur, destinataire, statut);
                    colisData.add(colis);

                    // Réinitialiser les champs
                    expediteurField.clear();
                    destinataireField.clear();
                    statutComboBox.setValue(null);
                } else {
                    // Alerte en cas de champs vides
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs pour ajouter un colis.", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                semaphore.release(); // Libérer le Semaphore
            }
        });

        // Action pour mettre à jour le statut
        mettreAJourButton.setOnAction(e -> {
            try {
                semaphore.acquire(); // Acquérir le Semaphore
                Colis colisSelectionne = colisTableView.getSelectionModel().getSelectedItem();
                if (colisSelectionne != null) {
                    String nouveauStatut = updateStatutComboBox.getValue();
                    if (nouveauStatut != null) {
                        colisSelectionne.setEtat(nouveauStatut); // Mettre à jour le statut
                        colisTableView.refresh(); // Rafraîchir la table
                        updateStatutComboBox.setValue(null); // Réinitialiser le ComboBox
                    } else {
                        // Alerte si aucun statut n'est sélectionné
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un nouveau statut.", ButtonType.OK);
                        alert.showAndWait();
                    }
                } else {
                    // Alerte si aucun colis n'est sélectionné
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un colis à mettre à jour.", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                semaphore.release(); // Libérer le Semaphore
            }
        });

        // Mise en page
        VBox vbox = new VBox(10, titleLabel, expediteurField, destinataireField, statutComboBox, ajouterColisButton, colisTableView, updateStatutComboBox, mettreAJourButton);
        vbox.setStyle("-fx-padding: 10;");

        // Création de la scène
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setTitle("Gestion de Livraison de Colis avec Semaphore");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}