package com.example.jaccount.view;

import com.example.jaccount.AccountingApp;
import com.example.jaccount.JAccountUtil;
import com.example.jaccount.Transaction;
import com.example.jaccount.control.IController;
import com.example.jaccount.view.viewcontrollers.MainView;
import com.example.jaccount.view.viewcontrollers.TransactionItemView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AppView implements IAppView {
  private final Stage stage;
  Scene scene = null;

  public AppView(Stage stageIn) {
    stage = stageIn;
  }


  public void initialize(IController controlIn) {
    FXMLLoader fxmlLoader = new FXMLLoader(AccountingApp.class.getResource("main-transactions-view.fxml"));
    fxmlLoader.setController(makeTransactionsViewController(controlIn));
    try {
      scene = new Scene(fxmlLoader.load(), 1280, 720);
    } catch (IOException e) {
      e.printStackTrace();
    }
    stage.setTitle("JAccount");
    stage.setScene(scene);
    stage.show();
  }

  private MainView makeTransactionsViewController(IController controlIn) {
    MainView transactionsViewController = new MainView();
    transactionsViewController.setListView(makeTransactionsList(controlIn.getTransactions()));
    transactionsViewController.setTotal(JAccountUtil.formatAsMoney(controlIn.getTotal()));
    transactionsViewController.setCategories(controlIn.getCategoriesandAmounts());
    return transactionsViewController;
  }

  private ListView<HBox> makeTransactionsList(ArrayList<Transaction> transactions) {
    ListView<HBox> transactionsList = new ListView<>();
    transactionsList.setOrientation(Orientation.VERTICAL);
    transactionsList.getStyleClass().add("test");
    transactionsList.setItems(getTransactionItemViews(transactions));
    return transactionsList;
  }


  private ObservableList<HBox> getTransactionItemViews(ArrayList<Transaction> transactions) {
    ArrayList<HBox> boxArray = new ArrayList<>();

    for (Transaction trans : transactions) {
      FXMLLoader loader = new FXMLLoader(AccountingApp.class.getResource("transaction-item.fxml"));
      HBox box = new HBox();
      loader.setController(new TransactionItemView(trans));
      try {
        box = loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }
      boxArray.add(box);
    }
    return FXCollections.observableList(boxArray);
  }

}
