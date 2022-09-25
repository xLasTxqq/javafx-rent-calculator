package com.company.rest;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField flatSize;

    @FXML
    private TextField monthSave;

    @FXML
    private TextField yearSave;

    @FXML
    private Button buttonCalculate;

    @FXML
    private Button buttonSave;

    @FXML
    private Label calculateText;

    @FXML
    private RadioButton radioLeft;

    @FXML
    private ToggleGroup da;

    @FXML
    private RadioButton radioRight;

    @FXML
    private TextField monthSearch;

    @FXML
    private TextField yearSearch;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextArea areaSearch;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;

    @FXML
    void initialize() {
//        buttonSave.setOnAction(event -> {
//            String FlatSize = flatSize.getText().trim();
//            String MonthSave = monthSave.getText().trim();
//            String YearSave = yearSave.getText().trim();
//            if(!FlatSize.equals("")&&MonthSave.equals("")&&YearSave.equals("")){
//                BDSave(FlatSize,MonthSave,YearSave);
//            }
//        });
        buttonSave.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            boolean month=false;
            boolean year=false;
            boolean size=false;
            try {Integer.parseInt(monthSave.getText().trim());month=true;}catch (NumberFormatException e){Animation Anim=new Animation(monthSave);Anim.playAnim();}
            try {Integer.parseInt(yearSave.getText().trim());year=true;}catch (NumberFormatException e){Animation Anim=new Animation(yearSave);Anim.playAnim();}
            try {Integer.parseInt(flatSize.getText().trim());size=true;}catch (NumberFormatException e){Animation Anim=new Animation(flatSize);Anim.playAnim();}
            try {
                if(!monthSave.getText().trim().equals("") && !yearSave.getText().trim().equals("") && !flatSize.getText().trim().equals("")) {
                    if(!month||!year||!size){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText(null);
                        alert.setContentText("Заполните поля числовыми символами");
                        alert.showAndWait();
                    }
                    else {
                        dbHandler.SignUpData(monthSave.getText().trim(), yearSave.getText().trim(), flatSize.getText().trim());
                        monthSave.setText("");
                        yearSave.setText("");
                        flatSize.setText("");
                        calculateText.setText("Расчетная кварплата:");
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Заполните поля");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        buttonCalculate.setOnAction(event -> {
            boolean month=false;
            boolean year=false;
            boolean size=false;
            try {Integer.parseInt(monthSave.getText().trim());month=true;}catch (NumberFormatException e){Animation Anim=new Animation(monthSave);Anim.playAnim();}
            try {Integer.parseInt(yearSave.getText().trim());year=true;}catch (NumberFormatException e){Animation Anim=new Animation(yearSave);Anim.playAnim();}
            try {Integer.parseInt(flatSize.getText().trim());size=true;}catch (NumberFormatException e){Animation Anim=new Animation(flatSize);Anim.playAnim();}

            if(!monthSave.getText().trim().equals("") && !yearSave.getText().trim().equals("") && !flatSize.getText().trim().equals("")) {
                if(!month||!year||!size){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Заполните поля числовыми символами");
                    alert.showAndWait();
                }
                else {
                    DatabaseHandler dbHandler = new DatabaseHandler();
                    calculateText.setText(dbHandler.Calcul(monthSave.getText().trim(), yearSave.getText().trim(), flatSize.getText().trim()));
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Заполните поля");
                alert.showAndWait();
            }
        });
        buttonSearch.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            String Sort;
            boolean month=false;
            boolean year=false;
            if(!monthSearch.getText().trim().equals(""))try {Integer.parseInt(monthSearch.getText().trim());month=true;}
            catch (NumberFormatException e){Animation Anim=new Animation(monthSearch);Anim.playAnim();}
            else month=true;
            if(!yearSearch.getText().trim().equals(""))try {Integer.parseInt(yearSearch.getText().trim());year=true;}
            catch (NumberFormatException e){Animation Anim=new Animation(yearSearch);Anim.playAnim();}
            else year=true;
            if(radioLeft.isSelected())
                Sort="ASC";
            else Sort="DESC";
            try {
                areaSearch.setText("");
                if(!month||!year){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText(null);
                    alert.setContentText("Заполните поля числовыми символами");
                    alert.showAndWait();
                }
                else {
                    ResultSet RS = dbHandler.getData(monthSearch.getText().trim(), yearSearch.getText().trim(), Sort);
                    while (RS.next()) {
                        for (int i = 1; i < RS.getMetaData().getColumnCount() + 1; i++)
                            areaSearch.appendText(RS.getInt(i) + " ");
                        areaSearch.appendText("\n");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        buttonDelete.setOnAction(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            try {
                dbHandler.deleteData();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
