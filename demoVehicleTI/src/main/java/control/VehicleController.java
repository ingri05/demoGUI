package control;


import model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleController {

    @FXML private TextField idField;
    @FXML private TextField incidentField;
    @FXML private TextField searchField;

    @FXML private ComboBox<String> typeCombo;
    @FXML private ComboBox<String> statusCombo;

    @FXML private TableView<Vehicle> vehicleTable;
    @FXML private TableColumn<Vehicle, String> idColumn;
    @FXML private TableColumn<Vehicle, String> typeColumn;
    @FXML private TableColumn<Vehicle, String> statusColumn;
    @FXML private TableColumn<Vehicle, String> incidentColumn;

    private final ObservableList<Vehicle> vehicles =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        typeCombo.setItems(FXCollections.observableArrayList(
                "PATROL",
                "AMBULANCE",
                "FIRE_TRUCK"
        ));

        statusCombo.setItems(FXCollections.observableArrayList(
                "AVAILABLE",
                "EN_ROUTE",
                "ATTENDING",
                "OUT_OF_SERVICE"
        ));

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("vehicleType"));

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status"));

        incidentColumn.setCellValueFactory(
                new PropertyValueFactory<>("assignedIncident"));

        FilteredList<Vehicle> filteredVehicles =
                new FilteredList<>(vehicles, vehicle -> true);

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    String filter = newValue.toLowerCase().trim();

                    filteredVehicles.setPredicate(vehicle -> {
                        if (filter.isEmpty()) {
                            return true;
                        }

                        return vehicle.getId().toLowerCase()
                                .contains(filter)
                                || vehicle.getVehicleType().toLowerCase()
                                .contains(filter)
                                || vehicle.getAssignedIncident().toLowerCase()
                                .contains(filter);
                    });
                });

        vehicleTable.setItems(filteredVehicles);

        vehicleTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, selected) -> {
                    if (selected != null) {
                        idField.setText(selected.getId());
                        typeCombo.setValue(selected.getVehicleType());
                        statusCombo.setValue(selected.getStatus());
                        incidentField.setText(
                                selected.getAssignedIncident());
                    }
                });
    }

    @FXML
    private void registerVehicle() {

        String id = idField.getText().trim();
        String type = typeCombo.getValue();
        String status = statusCombo.getValue();
        String incident = incidentField.getText().trim();

        if (id.isEmpty() || type == null || status == null) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please complete all required fields."
            );
            return;
        }

        boolean exists = vehicles.stream()
                .anyMatch(vehicle ->
                        vehicle.getId().equalsIgnoreCase(id));

        if (exists) {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Duplicate Vehicle",
                    "A vehicle with this ID already exists."
            );
            return;
        }

        Vehicle vehicle = new Vehicle(
                id, type, status, incident
        );

        vehicles.add(vehicle);

        clearFields();

        showAlert(
                Alert.AlertType.INFORMATION,
                "Success",
                "Vehicle registered successfully."
        );
    }

    @FXML
    private void deleteVehicle() {

        Vehicle selected = vehicleTable
                .getSelectionModel()
                .getSelectedItem();

        if (selected == null) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "No Selection",
                    "Please select a vehicle to delete."
            );
            return;
        }

        vehicles.remove(selected);
        clearFields();
    }

    @FXML
    private void clearFields() {
        vehicleTable.getSelectionModel().clearSelection();
        idField.clear();
        typeCombo.setValue(null);
        statusCombo.setValue(null);
        incidentField.clear();
    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
