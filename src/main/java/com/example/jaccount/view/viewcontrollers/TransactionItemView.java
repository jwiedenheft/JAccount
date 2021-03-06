package com.example.jaccount.view.viewcontrollers;

import com.example.jaccount.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionItemView extends HBox implements Initializable {
  private final String name;
  private final String amount;
  private final String date;
  private String category = "";
  @FXML
  private Label nameLabel;
  @FXML
  private Label amountLabel;
  @FXML
  private Label dateLabel;
  @FXML
  private Label categoryLabel;

  public TransactionItemView(String nameIn, String amountIn, String dateIn) {
    name = nameIn;
    amount = amountIn;
    date = dateIn;
  }

  public TransactionItemView(Transaction transaction){
    name = transaction.getName();
    amount = transaction.getAmountString();
    date = transaction.getDate().toString();
    category = transaction.getCategory();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    nameLabel.setText(name);
    amountLabel.setText(amount);
    dateLabel.setText(date);
    categoryLabel.setText(category);
  }

}
