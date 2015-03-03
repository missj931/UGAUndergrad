/*
 * code inspired from this tutorial
 * http://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 * 
 */
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

//main class
public class Bonus extends Application {

	//class variables
	//initialize tableview
	public TableView theTable = new TableView();
	final ObservableList<Course> theData = FXCollections.observableArrayList();
	final HBox theHitBox = new HBox();

	//main method
	public static void main(String[] args) {
		//launch the javafx application
		launch(args);
	}

	//runs the starting javafx applications
	@Override
	public void start(Stage stage) {

		//set the scene
		Scene scene = new Scene(new Group());

		//set the stage
		stage.setTitle("Grade Calculator");
		stage.setWidth(800);
		stage.setHeight(800);

		//create label
		final Label label = new Label("Enter Course Information Here");
		label.setFont(new Font("Arial", 18));

		//make the table editable
		theTable.setEditable(true);

		Callback<TableColumn, TableCell> cellFactory =
				new Callback<TableColumn, TableCell>() {
			public TableCell call(TableColumn p) {
				return new TableCell();
			}
		};

		//set the columns in the table
		TableColumn crsID = new TableColumn("CrsID");	
		crsID.setMinWidth(100);
		crsID.setCellValueFactory(
				new PropertyValueFactory<Course, String>("CrsID")
				);
		crsID.setCellFactory(TextFieldTableCell.forTableColumn());
		crsID.setOnEditCommit(
				new EventHandler<CellEditEvent<Course, String>>() {
					@Override
					public void handle(CellEditEvent<Course, String> t) {
						((Course) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
								).setCrsID(t.getNewValue());
					}
				}
				);

		TableColumn crsTitle = new TableColumn("CrsTitle");
		crsTitle.setMinWidth(100);
		crsTitle.setCellValueFactory(
				new PropertyValueFactory<Course, String>("CrsTitle")
				);
		crsTitle.setCellFactory(TextFieldTableCell.forTableColumn());
		crsTitle.setOnEditCommit(
				new EventHandler<CellEditEvent<Course, String>>() {
					@Override
					public void handle(CellEditEvent<Course, String> t) {
						((Course) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
								).setCrsTitle(t.getNewValue());
					}
				}
				);

		TableColumn gradeEarned = new TableColumn("Grade");
		gradeEarned.setMinWidth(80);
		gradeEarned.setCellValueFactory(
				new PropertyValueFactory<Course, String>("Grade")
				);
		gradeEarned.setCellFactory(TextFieldTableCell.forTableColumn());
		gradeEarned.setOnEditCommit(
				new EventHandler<CellEditEvent<Course, String>>() {
					@Override
					public void handle(CellEditEvent<Course, String> t) {
						((Course) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
								).setGradeAwarded(t.getNewValue());
					}
				}
				);

		TableColumn creditsEarned = new TableColumn("CreditsAwarded");
		creditsEarned.setMinWidth(80);
		creditsEarned.setCellValueFactory(
				new PropertyValueFactory<Course, String>("CreditsAwarded")
				);
		creditsEarned.setCellFactory(TextFieldTableCell.forTableColumn());
		creditsEarned.setOnEditCommit(
				new EventHandler<CellEditEvent<Course, String>>() {
					@Override
					public void handle(CellEditEvent<Course, String> t) {
						((Course) t.getTableView().getItems().get(
								t.getTablePosition().getRow())
								).setCreditsAwarded(t.getNewValue());
					}
				}
				);


		theTable.setItems(theData);
		theTable.getColumns().addAll(crsID, crsTitle, creditsEarned, gradeEarned);

		//add text fields

		final TextField addCrsID = new TextField();
		addCrsID.setPromptText("Crs ID");
		addCrsID.setMaxWidth(crsID.getPrefWidth());

		final TextField addCrsName = new TextField();
		addCrsName.setMaxWidth(crsTitle.getPrefWidth());
		addCrsName.setPromptText("Crs Name");

		final TextField addCredits = new TextField();
		addCredits.setMaxWidth(creditsEarned.getPrefWidth());
		addCredits.setPromptText("Credits");

		final TextField addGrade = new TextField();
		addGrade.setMaxWidth(gradeEarned.getPrefWidth());
		addGrade.setPromptText("Grade");






		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			//handle the addition of information
			@Override
			public void handle(ActionEvent e) {
				theData.add(new Course(
						addCrsID.getText(),
						addCrsName.getText(),
						addCredits.getText(),
						addGrade.getText()
						) );
				addCrsID.clear();
				addCrsName.clear();
				addCredits.clear();
				addGrade.clear();

			}
		});


		//set up the hit box
		theHitBox.getChildren().addAll(addCrsID, addCrsName, addCredits, addGrade, addButton);
		theHitBox.setSpacing(4);

		//set up the v box
		final VBox theVbox = new VBox();
		theVbox.setSpacing(5);
		theVbox.setPadding(new Insets(10, 0, 0, 10));
		theVbox.getChildren().addAll(label, theTable, theHitBox);

		//ad it to the scene
		((Group) scene.getRoot()).getChildren().addAll(theVbox);

		//set the scene on the stage
		stage.setScene(scene);

		//show the stage
		stage.show();
	}





	//class Course
	public static class Course {


		//class variables
		private final SimpleStringProperty crsID;
		private final SimpleStringProperty crsTitle;
		private final SimpleStringProperty creditsAwarded;
		private final SimpleStringProperty gradeAwarded;

		//constructor
		Course(String pCrsID, String pCrsTitle, String pCredits, String pGrade) {
			crsID = new SimpleStringProperty(pCrsID);
			crsTitle = new SimpleStringProperty(pCrsTitle);
			creditsAwarded = new SimpleStringProperty(pCredits);
			gradeAwarded = new SimpleStringProperty(pGrade);


		}

		//***********************************************
		//Getters/Accessors

		public String getCrsID(){

			return crsID.get();
		}

		public String getCrsTitle(){
			return crsTitle.get();
		}

		public String getCreditsAwarded(){
			return creditsAwarded.get();
		}

		public String getGrade(){

			return gradeAwarded.get();

		}



		//***********************************************
		//Setters / Mutators
		public void setCrsID(String pCrsID){
			crsID.set(pCrsID);
		}

		public void setCrsTitle(String pCrsTitle){
			crsTitle.set(pCrsTitle);
		}

		public void setCreditsAwarded(String pCredits){
			creditsAwarded.set(pCredits);

		}

		public void setGradeAwarded(String pGrade){
			gradeAwarded.set(pGrade);


		}
	}

class EditingCell extends TableCell<Course, String> {
	 
    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText((String) getItem());
        setGraphic(null);
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, 
                Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
}