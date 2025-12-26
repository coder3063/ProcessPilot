package ui;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import models.Process;
import models.SchedulingResult;
import algorithms.*;
import visualization.GanttChart;
import utils.ProcessGenerator;
import utils.SchedulingComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Controller for ProcessPilot GUI
 */
public class Controller {

    // ====== FXML Components ======
    @FXML
    private TabPane mainTabPane;

    // Input Tab
    @FXML
    private TableView<ProcessInput> processTableView;
    @FXML
    private TableColumn<ProcessInput, String> processIdColumn;
    @FXML
    private TableColumn<ProcessInput, String> arrivalColumn;
    @FXML
    private TableColumn<ProcessInput, String> burstColumn;
    @FXML
    private TableColumn<ProcessInput, String> priorityColumn;
    @FXML
    private Button addProcessButton;
    @FXML
    private Button clearTableButton;
    @FXML
    private ComboBox<String> sampleDataCombo;
    @FXML
    private Button loadSampleButton;

    // Scheduler Tab
    @FXML
    private ComboBox<String> algorithmCombo;
    @FXML
    private Spinner<Integer> timeQuantumSpinner;
    @FXML
    private Button runSchedulerButton;
    @FXML
    private ProgressBar schedulingProgress;

    // Results Tab
    @FXML
    private TextArea ganttChartArea;
    @FXML
    private Label avgWaitingTimeLabel;
    @FXML
    private Label avgTurnaroundLabel;
    @FXML
    private Label cpuUtilLabel;
    @FXML
    private TableView<ProcessResult> resultsTableView;
    @FXML
    private TableColumn<ProcessResult, String> resultPidColumn;
    @FXML
    private TableColumn<ProcessResult, String> resultArrivalColumn;
    @FXML
    private TableColumn<ProcessResult, String> resultBurstColumn;
    @FXML
    private TableColumn<ProcessResult, String> resultCompletionColumn;
    @FXML
    private TableColumn<ProcessResult, String> resultWaitingColumn;
    @FXML
    private TableColumn<ProcessResult, String> resultTurnaroundColumn;

    // Comparison Tab
    @FXML
    private TableView<ComparisonResult> comparisonTableView;
    @FXML
    private TableColumn<ComparisonResult, String> algNameColumn;
    @FXML
    private TableColumn<ComparisonResult, String> compWaitingColumn;
    @FXML
    private TableColumn<ComparisonResult, String> compTurnaroundColumn;
    @FXML
    private TableColumn<ComparisonResult, String> compUtilColumn;
    @FXML
    private Button runComparisonButton;
    @FXML
    private TextArea comparisonDetailsArea;

    // ====== Data Storage ======
    private List<Process> currentProcesses = new ArrayList<>();
    private List<SchedulingResult> currentResults = new ArrayList<>();
    private ObservableList<ProcessInput> processInputs = FXCollections.observableArrayList();
    private ObservableList<ProcessResult> processResults = FXCollections.observableArrayList();
    private ObservableList<ComparisonResult> comparisonResults = FXCollections.observableArrayList();

    // ====== Initialization ======
    @FXML
    public void initialize() {
        System.out.println("=== Controller Initialization Starting ===");
        try {
            // Add null checks to ensure FXML injection is complete
            if (processTableView != null) {
                System.out.println("✓ Setting up Input Tab...");
                setupInputTab();
            } else {
                System.err.println("✗ ERROR: processTableView is null!");
            }
            
            if (algorithmCombo != null) {
                System.out.println("✓ Setting up Scheduler Tab...");
                setupSchedulerTab();
            } else {
                System.err.println("✗ ERROR: algorithmCombo is null!");
            }
            
            if (resultsTableView != null) {
                System.out.println("✓ Setting up Results Tab...");
                setupResultsTab();
            } else {
                System.err.println("✗ ERROR: resultsTableView is null!");
            }
            
            if (comparisonTableView != null) {
                System.out.println("✓ Setting up Comparison Tab...");
                setupComparisonTab();
            } else {
                System.err.println("✗ ERROR: comparisonTableView is null!");
            }
            
            System.out.println("=== Controller Initialization Complete ===");
        } catch (Exception e) {
            System.err.println("ERROR during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setupInputTab() {
        try {
            // Setup table columns with cell value factories
            if (processIdColumn != null) {
                processIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().pid));
                processIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                processIdColumn.setOnEditCommit(event -> {
                    event.getRowValue().pid = event.getNewValue();
                    System.out.println("  ✓ Updated Process ID to: " + event.getNewValue());
                });
                System.out.println("  ✓ Process ID column configured");
            } else {
                System.err.println("  ✗ processIdColumn is null");
            }
            
            if (arrivalColumn != null) {
                arrivalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().arrival));
                System.out.println("  ✓ Arrival column value factory set");
            } else {
                System.err.println("  ✗ arrivalColumn is null");
            }
            
            if (burstColumn != null) {
                burstColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().burst));
                System.out.println("  ✓ Burst column value factory set");
            } else {
                System.err.println("  ✗ burstColumn is null");
            }
            
            if (priorityColumn != null) {
                priorityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().priority));
                System.out.println("  ✓ Priority column value factory set");
            } else {
                System.err.println("  ✗ priorityColumn is null");
            }

            // Make table editable
            if (processTableView != null) {
                processTableView.setEditable(true);
                System.out.println("  ✓ Table set to editable");
            } else {
                System.err.println("  ✗ processTableView is null");
            }
            
            // Make arrival, burst, and priority columns editable with TextFieldTableCell
            if (arrivalColumn != null) {
                arrivalColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                arrivalColumn.setOnEditCommit(event -> {
                    ProcessInput process = event.getRowValue();
                    try {
                        Integer.parseInt(event.getNewValue()); // Validate it's a number
                        process.arrival = event.getNewValue();
                        System.out.println("  ✓ Updated " + process.pid + " arrival to: " + process.arrival);
                    } catch (NumberFormatException e) {
                        System.err.println("  ✗ Invalid arrival value: " + event.getNewValue());
                        showWarning("Invalid value! Please enter a valid number.");
                        if (processTableView != null) processTableView.refresh();
                    }
                });
                System.out.println("  ✓ Arrival column configured for editing");
            }
            
            if (burstColumn != null) {
                burstColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                burstColumn.setOnEditCommit(event -> {
                    ProcessInput process = event.getRowValue();
                    try {
                        int value = Integer.parseInt(event.getNewValue());
                        if (value <= 0) {
                            System.err.println("  ✗ Burst time must be > 0: " + value);
                            showWarning("Burst time must be greater than 0!");
                            if (processTableView != null) processTableView.refresh();
                        } else {
                            process.burst = event.getNewValue();
                            System.out.println("  ✓ Updated " + process.pid + " burst to: " + process.burst);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("  ✗ Invalid burst value: " + event.getNewValue());
                        showWarning("Invalid value! Please enter a valid number.");
                        if (processTableView != null) processTableView.refresh();
                    }
                });
                System.out.println("  ✓ Burst column configured for editing");
            }
            
            if (priorityColumn != null) {
                priorityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                priorityColumn.setOnEditCommit(event -> {
                    ProcessInput process = event.getRowValue();
                    try {
                        Integer.parseInt(event.getNewValue()); // Validate it's a number
                        process.priority = event.getNewValue();
                        System.out.println("  ✓ Updated " + process.pid + " priority to: " + process.priority);
                    } catch (NumberFormatException e) {
                        System.err.println("  ✗ Invalid priority value: " + event.getNewValue());
                        showWarning("Invalid value! Please enter a valid number.");
                        if (processTableView != null) processTableView.refresh();
                    }
                });
                System.out.println("  ✓ Priority column configured for editing");
            }

            if (processTableView != null) {
                processTableView.setItems(processInputs);
                System.out.println("  ✓ Table items set to processInputs observable list");
            }

            // Setup sample data combo
            if (sampleDataCombo != null) {
                sampleDataCombo.setItems(FXCollections.observableArrayList(
                        "Dataset 1 (5 processes)",
                        "Dataset 2 (4 processes)",
                        "Dataset 3 (5 priority-based)",
                        "Random (10 processes)"
                ));
                sampleDataCombo.getSelectionModel().selectFirst();
                System.out.println("  ✓ Sample data dropdown configured");
            } else {
                System.err.println("  ✗ sampleDataCombo is null");
            }

            // Add button listeners
            if (addProcessButton != null) {
                addProcessButton.setOnAction(e -> {
                    System.out.println("  → 'Add Process' button clicked");
                    addNewProcess();
                });
                System.out.println("  ✓ Add Process button listener set");
            } else {
                System.err.println("  ✗ addProcessButton is null");
            }
            
            if (clearTableButton != null) {
                clearTableButton.setOnAction(e -> {
                    System.out.println("  → 'Clear Table' button clicked");
                    clearProcessTable();
                });
                System.out.println("  ✓ Clear Table button listener set");
            } else {
                System.err.println("  ✗ clearTableButton is null");
            }
            
            if (loadSampleButton != null) {
                loadSampleButton.setOnAction(e -> {
                    System.out.println("  → 'Load Sample' button clicked");
                    loadSampleData();
                });
                System.out.println("  ✓ Load Sample button listener set");
            } else {
                System.err.println("  ✗ loadSampleButton is null");
            }
            
            // Add default process to demonstrate table works
            System.out.println("  Adding default processes for demonstration...");
            processInputs.add(new ProcessInput("P1", "0", "8", "1"));
            processInputs.add(new ProcessInput("P2", "1", "4", "2"));
            processInputs.add(new ProcessInput("P3", "2", "2", "1"));
            System.out.println("  ✓ Added 3 default processes for testing");
            
            System.out.println("  ✓ Input Tab setup completed");
        } catch (Exception e) {
            System.err.println("ERROR in setupInputTab: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setupSchedulerTab() {
        // Setup algorithm combo
        if (algorithmCombo != null) {
            algorithmCombo.setItems(FXCollections.observableArrayList(
                    "FCFS",
                    "SJF (Non-preemptive)",
                    "SJF (Preemptive - SRTF)",
                    "Priority (Non-preemptive)",
                    "Priority (Preemptive)",
                    "Round Robin",
                    "Multilevel Queue"
            ));
            algorithmCombo.getSelectionModel().selectFirst();
        }

        // Setup time quantum spinner
        if (timeQuantumSpinner != null) {
            timeQuantumSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 4));
        }

        // Run button
        if (runSchedulerButton != null) {
            runSchedulerButton.setOnAction(e -> runSelectedScheduler());
        }
    }

    private void setupResultsTab() {
        // Setup results table columns
        if (resultPidColumn != null) {
            resultPidColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().pid));
        }
        if (resultArrivalColumn != null) {
            resultArrivalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().arrival));
        }
        if (resultBurstColumn != null) {
            resultBurstColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().burst));
        }
        if (resultCompletionColumn != null) {
            resultCompletionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().completion));
        }
        if (resultWaitingColumn != null) {
            resultWaitingColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().waiting));
        }
        if (resultTurnaroundColumn != null) {
            resultTurnaroundColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().turnaround));
        }

        if (resultsTableView != null) {
            resultsTableView.setItems(processResults);
        }
    }

    private void setupComparisonTab() {
        // Setup comparison table columns
        if (algNameColumn != null) {
            algNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().algorithm));
        }
        if (compWaitingColumn != null) {
            compWaitingColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().avgWaiting));
        }
        if (compTurnaroundColumn != null) {
            compTurnaroundColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().avgTurnaround));
        }
        if (compUtilColumn != null) {
            compUtilColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cpuUtil));
        }

        if (comparisonTableView != null) {
            comparisonTableView.setItems(comparisonResults);
        }

        // Run comparison button
        if (runComparisonButton != null) {
            runComparisonButton.setOnAction(e -> runAllComparison());
        }
    }

    // ====== Input Tab Methods ======
    private void addNewProcess() {
        try {
            String pid = "P" + (processInputs.size() + 1);
            ProcessInput newProcess = new ProcessInput(pid, "0", "5", "1");
            processInputs.add(newProcess);
            System.out.println("✓ Added new process: " + pid + " with default values (Arrival=0, Burst=5, Priority=1)");
            System.out.println("  Total processes in table: " + processInputs.size());
            if (processTableView != null) {
                processTableView.refresh();
                System.out.println("  Table refreshed - should now show " + processInputs.size() + " rows");
            }
        } catch (Exception e) {
            System.err.println("✗ Error adding process: " + e.getMessage());
            e.printStackTrace();
            showError("Error adding process: " + e.getMessage());
        }
    }

    private void clearProcessTable() {
        int count = processInputs.size();
        processInputs.clear();
        System.out.println("✓ Cleared " + count + " processes from table");
        if (processTableView != null) {
            processTableView.refresh();
        }
    }

    private void loadSampleData() {
        try {
            int selectedIndex = sampleDataCombo.getSelectionModel().getSelectedIndex();
            processInputs.clear();
            List<Process> processes = null;

            switch (selectedIndex) {
                case 0:
                    processes = ProcessGenerator.generateSampleProcesses();
                    break;
                case 1:
                    processes = ProcessGenerator.generateExample2();
                    break;
                case 2:
                    processes = ProcessGenerator.generateExample3();
                    break;
                case 3:
                    processes = ProcessGenerator.generateRandomProcesses(10, 10, 10, 4);
                    break;
            }

            if (processes != null) {
                for (Process p : processes) {
                    ProcessInput input = new ProcessInput(
                            "P" + p.getProcessId(),
                            String.valueOf(p.getArrivalTime()),
                            String.valueOf(p.getBurstTime()),
                            String.valueOf(p.getPriority())
                    );
                    processInputs.add(input);
                    System.out.println("Loaded: " + input.pid + " | Arrival=" + input.arrival + " | Burst=" + input.burst + " | Priority=" + input.priority);
                }
                if (processTableView != null) {
                    processTableView.refresh();
                }
                showInfo("Sample data loaded successfully! (" + processes.size() + " processes)\n\nYou can now:\n1. Click '⚙️ Scheduler' tab\n2. Select an algorithm\n3. Click 'Run Selected Scheduler' to see results");
            }
        } catch (Exception e) {
            showError("Error loading sample data: " + e.getMessage());
        }
    }

    // ====== Scheduler Tab Methods ======
    private void runSelectedScheduler() {
        try {
            if (processInputs.isEmpty()) {
                showWarning("Please add processes first!");
                return;
            }

            // Convert input to Process objects
            currentProcesses = convertInputToProcesses();

            if (currentProcesses.isEmpty()) {
                showError("No valid processes to schedule!");
                return;
            }

            // Get selected algorithm
            int algIndex = algorithmCombo.getSelectionModel().getSelectedIndex();
            Scheduler scheduler = getScheduler(algIndex);

            if (scheduler == null) {
                showError("Invalid algorithm selected!");
                return;
            }

            // Show progress
            schedulingProgress.setVisible(true);
            schedulingProgress.setProgress(-1); // Indeterminate

            // Run in background
            new Thread(() -> {
                try {
                    List<Process> processCopies = new ArrayList<>();
                    for (Process p : currentProcesses) {
                        processCopies.add(p.copy());
                    }

                    SchedulingResult result = scheduler.schedule(processCopies);

                    Platform.runLater(() -> {
                        displayResults(result);
                        schedulingProgress.setProgress(1.0);
                        schedulingProgress.setVisible(false);
                        mainTabPane.getSelectionModel().select(2); // Go to results tab
                    });
                } catch (Exception e) {
                    Platform.runLater(() -> {
                        showError("Scheduling error: " + e.getMessage());
                        schedulingProgress.setProgress(0);
                        schedulingProgress.setVisible(false);
                    });
                }
            }).start();

        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }

    private Scheduler getScheduler(int algIndex) {
        switch (algIndex) {
            case 0:
                return new FCFSScheduler();
            case 1:
                return new SJFScheduler(false);
            case 2:
                return new SJFScheduler(true);
            case 3:
                return new PriorityScheduler(false);
            case 4:
                return new PriorityScheduler(true);
            case 5:
                return new RoundRobinScheduler(timeQuantumSpinner.getValue());
            case 6:
                return new MultilevelQueueScheduler();
            default:
                return null;
        }
    }

    // ====== Results Display ======
    private void displayResults(SchedulingResult result) {
        // Display metrics
        avgWaitingTimeLabel.setText(String.format("%.2f", result.getAverageWaitingTime()));
        avgTurnaroundLabel.setText(String.format("%.2f", result.getAverageTurnaroundTime()));
        cpuUtilLabel.setText(String.format("%.2f%%", result.getCpuUtilization()));

        // Display Gantt chart
        GanttChart ganttChart = new GanttChart(result);
        ganttChartArea.setText(ganttChart.exportAsASCII());

        // Display process results
        processResults.clear();
        for (Process p : result.getProcesses()) {
            processResults.add(new ProcessResult(
                    "P" + p.getProcessId(),
                    String.valueOf(p.getArrivalTime()),
                    String.valueOf(p.getBurstTime()),
                    String.valueOf(p.getCompletionTime()),
                    String.valueOf(p.getWaitingTime()),
                    String.valueOf(p.getTurnaroundTime())
            ));
        }

        showInfo("Scheduling completed successfully!");
    }

    // ====== Comparison Methods ======
    private void runAllComparison() {
        try {
            if (processInputs.isEmpty()) {
                showWarning("Please add processes first!");
                return;
            }

            currentProcesses = convertInputToProcesses();
            if (currentProcesses.isEmpty()) {
                showError("No valid processes!");
                return;
            }

            runComparisonButton.setDisable(true);

            new Thread(() -> {
                try {
                    List<SchedulingResult> results = new ArrayList<>();

                    // Run all algorithms
                    Scheduler[] schedulers = {
                            new FCFSScheduler(),
                            new SJFScheduler(false),
                            new SJFScheduler(true),
                            new PriorityScheduler(false),
                            new PriorityScheduler(true),
                            new RoundRobinScheduler(4),
                            new MultilevelQueueScheduler()
                    };

                    for (Scheduler scheduler : schedulers) {
                        List<Process> copies = new ArrayList<>();
                        for (Process p : currentProcesses) {
                            copies.add(p.copy());
                        }
                        results.add(scheduler.schedule(copies));
                    }

                    Platform.runLater(() -> {
                        displayComparison(results);
                        runComparisonButton.setDisable(false);
                        mainTabPane.getSelectionModel().select(3); // Go to comparison tab
                    });

                } catch (Exception e) {
                    Platform.runLater(() -> {
                        showError("Comparison error: " + e.getMessage());
                        runComparisonButton.setDisable(false);
                    });
                }
            }).start();

        } catch (Exception e) {
            showError("Error: " + e.getMessage());
        }
    }

    private void displayComparison(List<SchedulingResult> results) {
        comparisonResults.clear();

        for (SchedulingResult result : results) {
            comparisonResults.add(new ComparisonResult(
                    result.getAlgorithmName(),
                    String.format("%.2f", result.getAverageWaitingTime()),
                    String.format("%.2f", result.getAverageTurnaroundTime()),
                    String.format("%.2f%%", result.getCpuUtilization())
            ));
        }

        // Build detailed comparison
        StringBuilder details = new StringBuilder();
        details.append("ALGORITHM COMPARISON DETAILS\n");
        details.append("═".repeat(80)).append("\n\n");

        for (SchedulingResult result : results) {
            details.append("Algorithm: ").append(result.getAlgorithmName()).append("\n");
            details.append("  Avg Waiting Time: ").append(String.format("%.2f", result.getAverageWaitingTime())).append("\n");
            details.append("  Avg Turnaround Time: ").append(String.format("%.2f", result.getAverageTurnaroundTime())).append("\n");
            details.append("  CPU Utilization: ").append(String.format("%.2f%%", result.getCpuUtilization())).append("\n\n");
        }

        comparisonDetailsArea.setText(details.toString());
        showInfo("Comparison completed successfully!");
    }

    // ====== Utility Methods ======
    private List<Process> convertInputToProcesses() {
        List<Process> processes = new ArrayList<>();
        try {
            for (int i = 0; i < processInputs.size(); i++) {
                ProcessInput input = processInputs.get(i);
                Process p = new Process(
                        i + 1,
                        Integer.parseInt(input.arrival),
                        Integer.parseInt(input.burst),
                        Integer.parseInt(input.priority)
                );
                processes.add(p);
            }
        } catch (NumberFormatException e) {
            showError("Invalid process data! Please check all values are numbers.");
            return new ArrayList<>();
        }
        return processes;
    }

    private void showInfo(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Info", message);
    }

    private void showWarning(String message) {
        showAlert(Alert.AlertType.WARNING, "Warning", message);
    }

    private void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ====== Inner Classes for TableView Data ======
    public static class ProcessInput {
        public String pid;
        public String arrival;
        public String burst;
        public String priority;

        public ProcessInput(String pid, String arrival, String burst, String priority) {
            this.pid = pid;
            this.arrival = arrival;
            this.burst = burst;
            this.priority = priority;
        }
    }

    public static class ProcessResult {
        public String pid;
        public String arrival;
        public String burst;
        public String completion;
        public String waiting;
        public String turnaround;

        public ProcessResult(String pid, String arrival, String burst, String completion, String waiting, String turnaround) {
            this.pid = pid;
            this.arrival = arrival;
            this.burst = burst;
            this.completion = completion;
            this.waiting = waiting;
            this.turnaround = turnaround;
        }
    }

    public static class ComparisonResult {
        public String algorithm;
        public String avgWaiting;
        public String avgTurnaround;
        public String cpuUtil;

        public ComparisonResult(String algorithm, String avgWaiting, String avgTurnaround, String cpuUtil) {
            this.algorithm = algorithm;
            this.avgWaiting = avgWaiting;
            this.avgTurnaround = avgTurnaround;
            this.cpuUtil = cpuUtil;
        }
    }
}
