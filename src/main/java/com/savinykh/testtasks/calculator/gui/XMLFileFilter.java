package com.savinykh.testtasks.calculator.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Класс файлового-фильтра
 */
public class XMLFileFilter extends FileFilter {

    private String ext, description;            //суффикс и описание

    /**
     * Конструктор класса с использование суффикса
     * @param ext - суффикс
     */
    public XMLFileFilter(String ext){
        this.ext = ext;
        this.description = ext;
    }

    /**
     * При принятии выбора
     * @param f - обрабатываемый (выбранный файл)
     * @return - выполнена ли операция
     */
    @Override
    public boolean accept(File f) {
        if(f != null) {
            if(f.isDirectory()) {
                return true;
            }

            return f.toString().endsWith(ext);
        }
        return false;
    }

    /**
     * Геттер описания
     * @return - описание
     */
    public String getDescription() {
        return description;
    }

}
