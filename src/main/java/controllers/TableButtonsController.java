package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TableButtonsController {
    String className;
    Object controller;

    public TableButtonsController(String className) {
        this.className = className;
        controller = createController(className);
    }

    private Object createController(String className) {
        // Criando objeto dinâmico através da className
        try {
            Class<?> clazz = Class.forName("controllers.views." + this.className);
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("ERRO [BUTTONS - on create controller ]: " + e);
        }
        return new Object();
    }

    @FXML
    public void onDelete() {
       this.callMethod("onDelete");
    }

    @FXML
    public void onCreate() {
        this.callMethod("onCreate");
    }

    @FXML
    private void onEdit(ActionEvent event) throws IOException {
        this.callMethod("onEdit");
    }

    private void callMethod(String methodName) {
        // Criando método e chamando
        try {
            Method method = this.controller.getClass().getMethod(methodName);
            method.invoke(this.controller);
        } catch ( NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
